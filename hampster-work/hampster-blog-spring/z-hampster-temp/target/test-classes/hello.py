from transformers import AutoModelForQuestionAnswering, AutoTokenizer, pipeline
import sys
import json

sys.stdout.reconfigure(encoding='utf-8')
model_name = "deepset/tinyroberta-squad2"

# a) Get predictions
nlp = pipeline('question-answering', model=model_name, tokenizer=model_name)
QA_input = {
    'question': '主要内容是什么?',
    'context': sys.argv[1]
}
res = nlp(QA_input)
print(json.dumps(res, ensure_ascii=False))

# b) Load model & tokenizer
model = AutoModelForQuestionAnswering.from_pretrained(model_name)
tokenizer = AutoTokenizer.from_pretrained(model_name)
