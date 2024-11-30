import os

delete_str = "messages"

for root, dirs, files in os.walk("."):
    for file in files:
        if delete_str.lower() in file.lower():
            file_path = os.path.join(root, file)
            print(f"正在删除文件: {file_path}")
            os.remove(file_path)

print("删除完成!")