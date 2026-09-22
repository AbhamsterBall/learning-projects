from fastapi import FastAPI, UploadFile
from paddleocr import PPStructureV3
import shutil, os
from PIL import Image

app = FastAPI()
pipeline = PPStructureV3(
    device="gpu:0",
    use_formula_recognition=False,  # 关掉公式识别
)

@app.post("/ocr")
async def ocr_image(file: UploadFile):
    path = f"_tmp_{file.filename}"
    with open(path, "wb") as f:
        shutil.copyfileobj(file.file, f)

    # ---------- 压缩图片 ----------
    img = Image.open(path)
    max_side = 2000
    if max(img.size) > max_side:
        ratio = max_side / max(img.size)
        new_size = (int(img.width * ratio), int(img.height * ratio))
        img = img.resize(new_size, Image.LANCZOS)
        img.save(path)
    # ------------------------------

    output = pipeline.predict(path)
    os.remove(path)

    resList = []

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
        resList.append(extracted)
        print(extracted)

    # 返回结构化结果，你可以在这里提取需要的内容
    return {"res": resList}
    # return {"result": output[0].json if hasattr(output[0], 'json') else str(output[0])}

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=9003)