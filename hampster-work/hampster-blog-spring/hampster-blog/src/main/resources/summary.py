# from transformers import AutoModelForQuestionAnswering, AutoTokenizer, pipeline
# import sys
# import json
#
# sys.stdout.reconfigure(encoding='utf-8')
# model_name = "deepset/tinyroberta-squad2"
#
# # a) Get predictions
# nlp = pipeline('question-answering', model=model_name, tokenizer=model_name)
# QA_input = {
#     'question': '主要内容是什么?',
#     'context': sys.argv[1]
# }
# res = nlp(QA_input)
# print(json.dumps(res, ensure_ascii=False))
#
# # b) Load model & tokenizer
# model = AutoModelForQuestionAnswering.from_pretrained(model_name)
# tokenizer = AutoTokenizer.from_pretrained(model_name)

# def download_model():
#     from transformers import AutoModelForQuestionAnswering, AutoTokenizer
#     model_name = "deepset/tinyroberta-squad2"
#     save_directory = "./local_model"
#
#     # 下载并保存模型
#     model = AutoModelForQuestionAnswering.from_pretrained(model_name)
#     model.save_pretrained(save_directory)
#
#     # 下载并保存分词器
#     tokenizer = AutoTokenizer.from_pretrained(model_name)
#     tokenizer.save_pretrained(save_directory)

from transformers import AutoModelForQuestionAnswering, AutoTokenizer, pipeline
import sys
import json

sys.stdout.reconfigure(encoding='utf-8')
local_directory = "F:\\JAVA\\20231010\\hampster-work\\hampster-blog-spring\\hampster-blog\\src\\main\\resources\\local_model"

# 加载本地模型和分词器
model = AutoModelForQuestionAnswering.from_pretrained(local_directory)
tokenizer = AutoTokenizer.from_pretrained(local_directory)

# 创建问答管道
nlp = pipeline('question-answering', model=model, tokenizer=tokenizer)

# 获取预测
QA_input = {
    'question': '主要内容是什么?',
    'context': sys.argv[1]
}
res = nlp(QA_input)
format_res = json.dumps(res, ensure_ascii=False)

# 退出程序
if len(format_res) > 0:
    print(format_res)
    sys.exit(0)
else:
    sys.exit(406)


# /usr/local/docker/build/hampster-blog/local_model