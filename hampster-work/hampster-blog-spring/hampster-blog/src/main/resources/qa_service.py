from transformers import AutoModelForQuestionAnswering, AutoTokenizer, pipeline
from flask import Flask, request, jsonify
import sys
import json

app = Flask(__name__)

def download_model():
    # add proxy
    import os
    os.environ['HTTP_PROXY'] = 'http://127.0.0.1:7890'
    os.environ['HTTPS_PROXY'] = 'http://127.0.0.1:7890'

    import logging
    logging.basicConfig(level=logging.INFO)

    print("正在下载模型...")
    from transformers import AutoModelForQuestionAnswering, AutoTokenizer
    # model_name = "deepset/tinyroberta-squad2"
    model_name = "sshleifer/distilbart-cnn-12-6"
    save_directory = "./distilbart-cnn-12-6"

    # 下载时会显示进度条
    model = AutoModelForQuestionAnswering.from_pretrained(model_name)
    print("模型下载完成！")
    model.save_pretrained(save_directory)
    print(f"模型已保存到 {save_directory}")

    tokenizer = AutoTokenizer.from_pretrained(model_name)
    print("分词器下载完成！")
    tokenizer.save_pretrained(save_directory)
    print("分词器已保存完成！")
    print("模型初始化完成！")

download_model()
# 全局加载模型（只加载一次）
print("正在加载模型...")
local_directory = "D:\\HAMPSTER-FOLDER\\hampster-work\\hampster-blog-spring\\hampster-blog\\src\\main\\resources\\distilbart-cnn-12-6"
model = AutoModelForQuestionAnswering.from_pretrained(local_directory)
tokenizer = AutoTokenizer.from_pretrained(local_directory)
nlp = pipeline('question-answering', model=model, tokenizer=tokenizer)
print("模型加载完成！")

@app.route('/qa', methods=['POST'])
def answer_question():
    try:
        data = request.json
        question = data.get('question', '主要内容是什么?')
        context = data.get('context', '')

        if not context:
            return jsonify({'error': 'context is required'}), 400

        QA_input = {
            'question': question,
            'context': context
        }

        res = nlp(QA_input)
        return jsonify(res)

    except Exception as e:
        return jsonify({'error': str(e)}), 500


@app.route('/health', methods=['GET'])
def health():
    return jsonify({'status': 'ok'})


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=False, threaded=True)


# // 使用 RestTemplate 或 WebClient
# String response = restTemplate.postForObject(
#     "http://localhost:5000/qa",
#     Map.of("context", text, "question", "主要内容是什么?"),
#     String.class
# );