from paddleocr import PPStructureV3

pipeline = PPStructureV3(device="gpu:0")  # 或 device="cpu"
output = pipeline.predict("./0.jpg")

for res in output:
    # res.print()  # 打印结构化结果
    res.save_to_json(save_path="output")  # 保存 JSON

    data = res.json["res"]  # 关键：多取一层 "res"

    # 提取标题
    titles = [
        block["block_content"]
        for block in data["parsing_res_list"]
        if block["block_label"] == "figure_title"
    ]

    # 提取表格 HTML
    tables = [
        block["block_content"]
        for block in data["parsing_res_list"]
        if block["block_label"] == "table"
    ]

    # 提取正文
    texts = [
        block["block_content"]
        for block in data["parsing_res_list"]
        if block["block_label"] == "text"
    ]

    extracted = {"title": titles, "tables": tables, "texts": texts}
    print(extracted)