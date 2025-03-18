import os
from transformers import AutoModelForCausalLM, AutoTokenizer
import torch
from accelerate import Accelerator
import deepspeed

# Initialize the accelerator
accelerator = Accelerator()

# Load a small model and tokenizer from Hugging Face
model_name = "sshleifer/tiny-gpt2"  # A very small model for testing purposes
tokenizer = AutoTokenizer.from_pretrained(model_name)
model = AutoModelForCausalLM.from_pretrained(model_name)

# Configure DeepSpeed
ds_config = {
    "zero_optimization": {
        "stage": 2,
        "offload_optimizer": {
            "device": "cpu",
            "pin_memory": True
        },
        "allgather_partitions": True,
        "allgather_bucket_size": 2e8,
        "overlap_comm": True,
        "reduce_scatter": True,
        "reduce_bucket_size": 2e8,
        "contiguous_gradients": True
    },
    "gradient_accumulation_steps": 1,
    "gradient_clipping": 1.0,
    "steps_per_print": 2000,
    "train_batch_size": 1,
    "train_micro_batch_size_per_gpu": 1,
    "optimizer": {
        "type": "Adam",
        "params": {
            "lr": 0.00015,
            "betas": [0.9, 0.999],
            "eps": 1e-8
        }
    },
    "fp16": {
        "enabled": True,
        "loss_scale": 0,
        "initial_scale_power": 32,
        "hysteresis": 2,
        "min_loss_scale": 1
    }
}

# Prepare the model with DeepSpeed
# model, _, _, _ = deepspeed.initialize(model=model, config=ds_config)

# Define the source and test directories
source_dir = "/workspaces/copilot-eval/src/com/example/demo"
test_dir = "/workspaces/copilot-eval/src/test/java/com/example/demo"

# Function to generate unit tests
def generate_unit_tests(class_code):
    prompt = f"Generate a unit test suite for the following Java class:\n\n{class_code}\n\n"
    inputs = tokenizer(prompt, return_tensors="pt")
    inputs = {k: v.to(accelerator.device) for k, v in inputs.items()}
    outputs = model.generate(**inputs, max_length=1024)
    generated_code = tokenizer.decode(outputs[0], skip_special_tokens=True)
    return generated_code

# Iterate over source files and generate tests
for root, _, files in os.walk(source_dir):
    for file in files:
        if file.endswith(".java"):
            source_file_path = os.path.join(root, file)
            with open(source_file_path, "r") as source_file:
                class_code = source_file.read()
                test_code = generate_unit_tests(class_code)
                
                # Determine the test file path
                relative_path = os.path.relpath(source_file_path, source_dir)
                test_file_path = os.path.join(test_dir, relative_path).replace(".java", "Test.java")
                
                # Ensure the test directory exists
                os.makedirs(os.path.dirname(test_file_path), exist_ok=True)
                
                # Write the generated test code to the test file
                with open(test_file_path, "w") as test_file:
                    test_file.write(test_code)

print("Unit test generation complete.")
