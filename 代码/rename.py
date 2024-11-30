import os

old_str = "caixuedengji"
new_str = "HealthyManagement"

for root, dirs, files in os.walk("."):
    for file in files:
        if old_str.lower() in file.lower():
            old_name = os.path.join(root, file)
            new_name = os.path.join(root, file.replace(old_str, new_str))
            print(f"重命名文件: {old_name} -> {new_name}")
            os.rename(old_name, new_name)

print("重命名完成!")