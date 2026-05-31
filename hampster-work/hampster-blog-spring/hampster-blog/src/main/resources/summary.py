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
local_directory = "D:\\HAMPSTER-FOLDER\\hampster-work\\hampster-blog-spring\\hampster-blog\\src\\main\\resources\\local_model"

# 加载本地模型和分词器
model = AutoModelForQuestionAnswering.from_pretrained(local_directory)
tokenizer = AutoTokenizer.from_pretrained(local_directory)

# 创建问答管道
nlp = pipeline('question-answering', model=model, tokenizer=tokenizer)

# 获取预测
QA_input = {
    'question': '主要内容是什么?', # 主要内容是什么?
}
if len(sys.argv) > 1:
    QA_input['context'] = sys.argv[1]
else:
    QA_input['context'] = """北京时间2023年5月30日，中国载人航天工程办公室宣布，神舟十六号载人飞船在酒泉卫星发射中心成功发射。
        此次任务由航天员景海鹏、朱杨柱和桂海潮组成乘组，其中景海鹏担任指令长。
        这是中国空间站应用与发展阶段的首个载人飞行任务，也是神舟系列飞船的第十六次飞行任务。
        神舟十六号与空间站组合体成功对接后，三名航天员将进驻天和核心舱，开展空间科学实验和技术试验。
        值得一提的是，航天员桂海潮是北京航空航天大学的教授，也是中国首位非军人出身的航天员。"""

res = nlp(QA_input)
format_res = json.dumps(res, ensure_ascii=False)

# 退出程序
if len(format_res) > 0:
    print(format_res)
    sys.exit(0)
else:
    sys.exit(406)


# /usr/local/docker/build/hampster-blog/local_model