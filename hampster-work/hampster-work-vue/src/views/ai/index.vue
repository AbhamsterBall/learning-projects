<script setup lang="ts">
import { ref, computed } from "vue";
import { processInfo } from "../../api/ai/ai.js";

const prompt = ref("");
const file = ref<File | null>(null);
const fileInput = ref<HTMLInputElement | null>(null);
const result = ref("");
const loading = ref(false);
const dragOver = ref(false);

const quickPrompts = [
  {
    label: "生成单据",
    value: '你是单据生成助手。下面是一张“工程物资领用计划单”经过 OCR 识别后的结果，包含标题、表格 HTML、正文文本三部分。\n请根据这些内容，生成结构化的申请单 JSON。\n【处理规则】\n1. 表格 HTML 中，rowspan 表示该单元格跨多行，你需要把合并单元格的值补全到它覆盖的每一行。例如某个“材料名称”带有 rowspan="5"，那么它下面连续 5 行的材料名称都应该填这个值，不能留空。\n2. 表格中的每一行材料都必须输出，一行都不能遗漏，序号跳跃（如从 4 直接到 6）也要按实际行输出。\n3. 工程名称、工程类别、合同号、领用单位从表格首部或正文中提取。合同号如果 OCR 结果里为空，就留空。\n4. 规格型号不能为空。如果 OCR 结果中该行没有明显的规格型号，优先从“材料名称”列拆分或从同行其他列推断；确实无法判断时，填“无”。\n5. 如果同一行里“材质”“备注”等内容混在相邻列，按语义归位，不要错列。\n6. 只输出 JSON，不要任何解释文字，不要在 JSON 里加入换行符号之外的额外说明。\n【OCR 识别结果】\n{OCR_RESULT}\n【输出格式】\n{"application":[{"useEnter":"领用单位","applyType":"工程类别","proName":"工程名称","items":[{"fname":"材料名称","model":"规格型号/材料名称","unit":"单位","quantity":"数量","fremark":"备注","orderCode":"合同号"}]}]}'
  },
  {
    label: "数据分析",
    value: "请分析以下数据，给出关键结论和建议。\n【数据】\n{OCR_RESULT}"
  },
  {
    label: "总结内容",
    value: "请总结以下内容的要点，用简洁的语言输出。\n【内容】\n{OCR_RESULT}"
  }
];

const canSubmit = computed(() => !loading.value && (prompt.value.trim() || file.value));

function handleFileChange(e: Event) {
  const target = e.target as HTMLInputElement;
  if (target.files && target.files.length > 0) {
    file.value = target.files[0];
  }
}

function handleDrop(e: DragEvent) {
  dragOver.value = false;
  const files = e.dataTransfer?.files;
  if (files && files.length > 0) {
    file.value = files[0];
  }
}

function clearFile() {
  file.value = null;
  if (fileInput.value) fileInput.value.value = "";
}

function applyQuickPrompt(value: string) {
  prompt.value = value;
}

async function handleSubmit() {
  if (!canSubmit.value) return;
  loading.value = true;
  result.value = "";

  try {
    const formData = new FormData();
    formData.append("prompt", prompt.value);
    if (file.value) formData.append("file", file.value);

    const data = await processInfo(formData);
    result.value = data?.data ?? data;
  } catch (e) {
    result.value = "请求失败：" + e;
  } finally {
    loading.value = false;
  }
}

async function copyResult() {
  if (!result.value) return;
  await navigator.clipboard.writeText(result.value);
}
</script>

<template>
  <div class="page">
    <div class="card">
      <header class="card-header">
        <h2>AI 单据处理</h2>
        <p class="subtitle">上传图片或直接输入提示词，快速生成结构化单据</p>
      </header>

      <!-- 快捷提示词 -->
      <section class="section">
        <div class="section-title">快捷提示词</div>
        <div class="chips">
          <button
            v-for="qp in quickPrompts"
            :key="qp.label"
            class="chip"
            @click="applyQuickPrompt(qp.value)"
          >
            {{ qp.label }}
          </button>
        </div>
      </section>

      <!-- 提示词 -->
      <section class="section">
        <div class="section-title">
          提示词
          <span class="hint" v-if="prompt.includes('{OCR_RESULT}')">
            含占位符，将自动替换为 OCR 结果
          </span>
        </div>
        <textarea
          v-model="prompt"
          rows="6"
          placeholder="输入提示词，或点击上方快捷提示词…"
        ></textarea>
      </section>

      <!-- 文件上传 -->
      <section class="section">
        <div class="section-title">上传图片（可选）</div>
        <div
          class="upload-area"
          :class="{ 'drag-over': dragOver, 'has-file': !!file }"
          @dragover.prevent="dragOver = true"
          @dragleave.prevent="dragOver = false"
          @drop.prevent="handleDrop"
          @click="fileInput?.click()"
        >
          <input
            ref="fileInput"
            type="file"
            accept="image/*"
            hidden
            @change="handleFileChange"
          />
          <template v-if="!file">
            <div class="upload-icon">+</div>
            <div class="upload-text">点击或拖拽图片到此处</div>
            <div class="upload-hint">支持 JPG / PNG 等格式</div>
          </template>
          <template v-else>
            <div class="file-row">
              <span class="file-name">{{ file.name }}</span>
              <button class="clear-btn" @click.stop="clearFile">移除</button>
            </div>
          </template>
        </div>
      </section>

      <!-- 提交 -->
      <button class="submit-btn" :disabled="!canSubmit" @click="handleSubmit">
        <span v-if="loading" class="spinner"></span>
        {{ loading ? "处理中…" : "提交" }}
      </button>

      <!-- 结果 -->
      <transition name="fade">
        <section v-if="result" class="section result-section">
          <div class="section-title">
            处理结果
            <button class="copy-btn" @click="copyResult">复制</button>
          </div>
          <pre class="result-box">{{ result }}</pre>
        </section>
      </transition>
    </div>
  </div>
</template>

<style scoped>
.page {
  min-height: 100vh;
  padding: 40px 16px;
  background: linear-gradient(135deg, #eef2ff 0%, #f8fafc 100%);
  display: flex;
  justify-content: center;
  align-items: flex-start;
}

.card {
  width: 100%;
  max-width: 720px;
  background: #fff;
  border-radius: 16px;
  padding: 32px;
  box-shadow: 0 10px 30px rgba(15, 23, 42, 0.08), 0 2px 6px rgba(15, 23, 42, 0.04);
}

.card-header h2 {
  margin: 0 0 6px;
  font-size: 22px;
  font-weight: 600;
  color: #0f172a;
}
.subtitle {
  margin: 0 0 24px;
  font-size: 14px;
  color: #64748b;
}

.section {
  margin-bottom: 20px;
}
.section-title {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 14px;
  font-weight: 600;
  color: #334155;
  margin-bottom: 10px;
}
.hint {
  font-weight: 400;
  font-size: 12px;
  color: #94a3b8;
}

/* 快捷提示词 */
.chips {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}
.chip {
  padding: 6px 14px;
  border-radius: 999px;
  border: 1px solid #c7d2fe;
  background: #eef2ff;
  color: #4338ca;
  font-size: 13px;
  cursor: pointer;
  transition: all 0.15s ease;
}
.chip:hover {
  background: #e0e7ff;
  border-color: #818cf8;
  transform: translateY(-1px);
}

/* 文本域 */
textarea {
  width: 100%;
  box-sizing: border-box;
  padding: 12px 14px;
  border: 1px solid #e2e8f0;
  border-radius: 10px;
  font-family: inherit;
  font-size: 14px;
  line-height: 1.6;
  color: #0f172a;
  resize: vertical;
  transition: border-color 0.15s, box-shadow 0.15s;
  outline: none;
}
textarea:focus {
  border-color: #818cf8;
  box-shadow: 0 0 0 3px rgba(129, 140, 248, 0.18);
}

/* 上传区域 */
.upload-area {
  border: 2px dashed #cbd5e1;
  border-radius: 12px;
  padding: 24px;
  text-align: center;
  cursor: pointer;
  transition: all 0.15s ease;
  background: #f8fafc;
}
.upload-area:hover {
  border-color: #818cf8;
  background: #eef2ff;
}
.upload-area.drag-over {
  border-color: #6366f1;
  background: #e0e7ff;
  transform: scale(1.01);
}
.upload-area.has-file {
  padding: 16px;
  border-style: solid;
  border-color: #a5b4fc;
  background: #eef2ff;
}
.upload-icon {
  font-size: 28px;
  color: #818cf8;
  line-height: 1;
  margin-bottom: 8px;
}
.upload-text {
  font-size: 14px;
  color: #475569;
}
.upload-hint {
  font-size: 12px;
  color: #94a3b8;
  margin-top: 4px;
}
.file-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
}
.file-name {
  font-size: 14px;
  color: #4338ca;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.clear-btn {
  border: none;
  background: transparent;
  color: #ef4444;
  font-size: 13px;
  cursor: pointer;
  flex-shrink: 0;
}
.clear-btn:hover {
  text-decoration: underline;
}

/* 提交按钮 */
.submit-btn {
  width: 100%;
  padding: 12px;
  border: none;
  border-radius: 10px;
  background: linear-gradient(135deg, #6366f1, #4f46e5);
  color: #fff;
  font-size: 15px;
  font-weight: 500;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  transition: all 0.15s ease;
}
.submit-btn:hover:not(:disabled) {
  box-shadow: 0 6px 18px rgba(79, 70, 229, 0.35);
  transform: translateY(-1px);
}
.submit-btn:disabled {
  background: #cbd5e1;
  cursor: not-allowed;
}

/* 加载动画 */
.spinner {
  width: 14px;
  height: 14px;
  border: 2px solid rgba(255, 255, 255, 0.4);
  border-top-color: #fff;
  border-radius: 50%;
  animation: spin 0.7s linear infinite;
}
@keyframes spin {
  to { transform: rotate(360deg); }
}

/* 结果 */
.result-section {
  margin-top: 24px;
}
.copy-btn {
  margin-left: auto;
  border: 1px solid #e2e8f0;
  background: #fff;
  color: #475569;
  font-size: 12px;
  padding: 3px 10px;
  border-radius: 6px;
  cursor: pointer;
}
.copy-btn:hover {
  background: #f1f5f9;
}
.result-box {
  margin: 0;
  padding: 16px;
  background: #0f172a;
  color: #e2e8f0;
  border-radius: 10px;
  font-size: 13px;
  line-height: 1.6;
  white-space: pre-wrap;
  word-break: break-all;
  max-height: 420px;
  overflow: auto;
}

/* 过渡 */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s ease;
}
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>