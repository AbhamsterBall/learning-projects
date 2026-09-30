<script setup lang="ts">
import { ref, computed, nextTick } from "vue";
import { processInfo } from "../../../api/ai/ai.js";
// import { Paperclip } from "@element-plus/icons-vue";

interface ChatMessage {
  id: number;
  role: "user" | "assistant";
  content: string;
  file?: { name: string; size: number } | null;
  loading?: boolean;
  error?: boolean;
}

const prompt = ref("");
const file = ref<File | null>(null);
const fileInput = ref<HTMLInputElement | null>(null);
const dragOver = ref(false);
const loading = ref(false);
const messages = ref<ChatMessage[]>([]);
const listRef = ref<HTMLElement | null>(null);
let msgId = 0;

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

const canSubmit = computed(
  () => !loading.value && (prompt.value.trim() || file.value)
);

function scrollToBottom() {
  nextTick(() => {
    const el = listRef.value;
    if (el) el.scrollTop = el.scrollHeight;
  });
}

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

  const userContent = prompt.value.trim();
  const userFile = file.value;

  // 1. 推入用户消息
  messages.value.push({
    id: ++msgId,
    role: "user",
    content: userContent,
    file: userFile
      ? { name: userFile.name, size: userFile.size }
      : null
  });

  // 2. 推入 AI 占位消息
  const aiMsg: ChatMessage = {
    id: ++msgId,
    role: "assistant",
    content: "",
    loading: true
  };
  messages.value.push(aiMsg);
  scrollToBottom();

  // 3. 清空输入区
  prompt.value = "";
  clearFile();
  loading.value = true;

  try {
    const formData = new FormData();
    formData.append("prompt", userContent);
    if (userFile) formData.append("file", userFile);

    const data = await processInfo(formData);
    const text = data?.data ?? data;
    aiMsg.content = typeof text === "string" ? text : JSON.stringify(text, null, 2);
    aiMsg.loading = false;
  } catch (e) {
    aiMsg.content = "请求失败：" + e;
    aiMsg.error = true;
    aiMsg.loading = false;
  } finally {
    loading.value = false;
    scrollToBottom();
  }
}

async function copyResult(text: string) {
  if (!text) return;
  await navigator.clipboard.writeText(text);
}

function formatSize(size: number) {
  if (size < 1024) return size + " B";
  if (size < 1024 * 1024) return (size / 1024).toFixed(1) + " KB";
  return (size / 1024 / 1024).toFixed(1) + " MB";
}
</script>

<template>
  <div class="chat-page">
    <div class="chat-card">
      <header class="chat-header">
        <h2>AI 对话</h2>
        <p class="subtitle">上传图片或直接输入提示词，快速生成结构化单据</p>
      </header>

      <!-- 消息列表 -->
      <main ref="listRef" class="chat-list">
        <!-- 空状态：显示快捷提示词 -->
        <div v-if="messages.length === 0" class="empty-state">
          <div class="empty-icon">💬</div>
          <p class="empty-title">开始一段对话</p>
          <p class="empty-desc">可以点击下方快捷提示词，或直接输入内容</p>
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
        </div>

        <!-- 消息气泡 -->
        <div
          v-for="msg in messages"
          :key="msg.id"
          class="msg-row"
          :class="msg.role"
        >
          <div class="avatar">
            {{ msg.role === "user" ? "我" : "AI" }}
          </div>
          <div class="bubble-wrap">
            <!-- 附件 -->
            <div v-if="msg.file" class="bubble-file">
              📎 {{ msg.file.name }}
              <span class="file-size">{{ formatSize(msg.file.size) }}</span>
            </div>

            <!-- 内容 -->
            <div
              class="bubble"
              :class="{ error: msg.error, loading: msg.loading }"
            >
              <template v-if="msg.loading">
                <span class="dot"></span>
                <span class="dot"></span>
                <span class="dot"></span>
              </template>
              <template v-else>
                <pre class="bubble-text">{{ msg.content }}</pre>
                <button
                  v-if="msg.role === 'assistant' && msg.content"
                  class="copy-btn"
                  @click="copyResult(msg.content)"
                >
                  复制
                </button>
              </template>
            </div>
          </div>
        </div>
      </main>

      <!-- 输入区 -->
      <footer class="chat-input">
        <!-- 快捷提示词（有消息时以小标签显示） -->
        <div v-if="messages.length > 0" class="chips chips-sm">
          <button
            v-for="qp in quickPrompts"
            :key="qp.label"
            class="chip chip-sm"
            @click="applyQuickPrompt(qp.value)"
          >
            {{ qp.label }}
          </button>
        </div>

        <!-- 已选文件 -->
        <div v-if="file" class="pending-file">
          <span class="pending-file-name">📎 {{ file.name }}</span>
          <button class="clear-btn" @click="clearFile">移除</button>
        </div>

        <div
          class="input-box"
          :class="{ 'drag-over': dragOver }"
          @dragover.prevent="dragOver = true"
          @dragleave.prevent="dragOver = false"
          @drop.prevent="handleDrop"
        >
          <textarea
            v-model="prompt"
            rows="1"
            placeholder="输入提示词，或拖拽图片到此处…"
            @keydown.enter.exact.prevent="handleSubmit"
          ></textarea>

          <div class="input-actions">
            <button
              class="icon-btn"
              title="上传附件"
              @click="fileInput?.click()"
            >
              <!--📎-->
                <svg
                class="clip-icon"
                viewBox="0 0 24 24"
                width="16"
                height="16"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
                stroke-linecap="round"
                stroke-linejoin="round"
                style="transform: rotate(135deg)"
                >
                <path d="M21.44 11.05l-9.19 9.19a6 6 0 01-8.49-8.49l9.19-9.19a4 4 0 015.66 5.66l-9.2 9.19a2 2 0 01-2.83-2.83l8.49-8.48" />
                </svg>
            </button>
            <button
              class="send-btn"
              :disabled="!canSubmit"
              @click="handleSubmit"
            >
              <span v-if="loading" class="spinner"></span>
              {{ loading ? "处理中" : "发送" }}
            </button>
          </div>
        </div>

        <input
          ref="fileInput"
          type="file"
          accept="image/*"
          hidden
          @change="handleFileChange"
        />
      </footer>
    </div>
  </div>
</template>

<style scoped>
.chat-page {
  min-height: 100vh;
  padding: 32px 16px;
  background: linear-gradient(135deg, #eef2ff 0%, #f8fafc 100%);
  display: flex;
  justify-content: center;
  align-items: flex-start;
}

.chat-card {
  width: 100%;
  max-width: 760px;
  height: calc(100vh - 64px);
  display: flex;
  flex-direction: column;
  background: #fff;
  border-radius: 16px;
  box-shadow: 0 10px 30px rgba(15, 23, 42, 0.08), 0 2px 6px rgba(15, 23, 42, 0.04);
  overflow: hidden;
}

/* 头部 */
.chat-header {
  padding: 20px 24px;
  border-bottom: 1px solid #f1f5f9;
}
.chat-header h2 {
  margin: 0 0 4px;
  font-size: 18px;
  font-weight: 600;
  color: #0f172a;
}
.subtitle {
  margin: 0;
  font-size: 13px;
  color: #64748b;
}

/* 消息列表 */
.chat-list {
  flex: 1;
  overflow-y: auto;
  padding: 20px 24px;
  display: flex;
  flex-direction: column;
  gap: 16px;
}

/* 空状态 */
.empty-state {
  margin: auto;
  text-align: center;
  padding: 24px;
}
.empty-icon {
  font-size: 40px;
  margin-bottom: 8px;
}
.empty-title {
  margin: 0 0 4px;
  font-size: 16px;
  font-weight: 600;
  color: #334155;
}
.empty-desc {
  margin: 0 0 16px;
  font-size: 13px;
  color: #94a3b8;
}
.chips {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  justify-content: center;
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

/* 消息行 */
.msg-row {
  display: flex;
  gap: 10px;
  align-items: flex-start;
}
.msg-row.user {
  flex-direction: row-reverse;
}

.avatar {
  width: 34px;
  height: 34px;
  flex-shrink: 0;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 12px;
  font-weight: 600;
  color: #fff;
  background: #94a3b8;
}
.msg-row.user .avatar {
  background: linear-gradient(135deg, #6366f1, #4f46e5);
}

.bubble-wrap {
  max-width: 78%;
  display: flex;
  flex-direction: column;
  gap: 6px;
}
.msg-row.user .bubble-wrap {
  align-items: flex-end;
}

.bubble-file {
  font-size: 12px;
  color: #4338ca;
  background: #eef2ff;
  border: 1px solid #c7d2fe;
  border-radius: 8px;
  padding: 4px 10px;
  display: inline-flex;
  align-items: center;
  gap: 6px;
}
.file-size {
  color: #94a3b8;
}

.bubble {
  position: relative;
  padding: 10px 14px;
  border-radius: 12px;
  font-size: 14px;
  line-height: 1.6;
  background: #f1f5f9;
  color: #0f172a;
  word-break: break-word;
}
.msg-row.user .bubble {
  background: linear-gradient(135deg, #6366f1, #4f46e5);
  color: #fff;
}
.bubble.error {
  background: #fef2f2;
  color: #b91c1c;
}
.bubble.loading {
  display: flex;
  gap: 4px;
  align-items: center;
  padding: 14px;
}

.bubble-text {
  margin: 0;
  white-space: pre-wrap;
  word-break: break-word;
  font-family: inherit;
}

.copy-btn {
  position: absolute;
  right: 6px;
  bottom: 6px;
  border: none;
  background: rgba(15, 23, 42, 0.06);
  color: #475569;
  font-size: 11px;
  padding: 2px 8px;
  border-radius: 6px;
  cursor: pointer;
  opacity: 0;
  transition: opacity 0.15s ease;
}
.bubble:hover .copy-btn {
  opacity: 1;
}
.copy-btn:hover {
  background: rgba(15, 23, 42, 0.12);
}

/* loading 三点 */
.dot {
  width: 6px;
  height: 6px;
  border-radius: 50%;
  background: #94a3b8;
  animation: bounce 1.2s infinite ease-in-out;
}
.dot:nth-child(2) {
  animation-delay: 0.15s;
}
.dot:nth-child(3) {
  animation-delay: 0.3s;
}
@keyframes bounce {
  0%, 80%, 100% {
    transform: scale(0.7);
    opacity: 0.5;
  }
  40% {
    transform: scale(1);
    opacity: 1;
  }
}

/* 输入区 */
.chat-input {
  border-top: 1px solid #f1f5f9;
  padding: 12px 16px 16px;
  background: #fff;
}
.chips-sm {
  justify-content: flex-start;
  margin-bottom: 8px;
}
.chip-sm {
  padding: 3px 10px;
  font-size: 12px;
}

.pending-file {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 8px;
  background: #eef2ff;
  border: 1px solid #c7d2fe;
  border-radius: 8px;
  padding: 6px 10px;
  margin-bottom: 8px;
  font-size: 12px;
  color: #4338ca;
}
.pending-file-name {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.clear-btn {
  border: none;
  background: transparent;
  color: #ef4444;
  font-size: 12px;
  cursor: pointer;
  flex-shrink: 0;
}
.clear-btn:hover {
  text-decoration: underline;
}

.input-box {
  display: flex;
  align-items: flex-end;
  gap: 8px;
  border: 1px solid #e2e8f0;
  border-radius: 12px;
  padding: 8px 8px 8px 12px;
  transition: border-color 0.15s, box-shadow 0.15s, background 0.15s;
}
.input-box:focus-within {
  border-color: #818cf8;
  box-shadow: 0 0 0 3px rgba(129, 140, 248, 0.18);
}
.input-box.drag-over {
  border-color: #6366f1;
  background: #eef2ff;
}

.input-box textarea {
  flex: 1;
  border: none;
  outline: none;
  resize: none;
  font-family: inherit;
  font-size: 14px;
  line-height: 1.6;
  color: #0f172a;
  max-height: 140px;
  min-height: 24px;
  background: transparent;
  padding: 4px 0;
}

.input-actions {
  display: flex;
  align-items: center;
  gap: 6px;
  flex-shrink: 0;
}
.icon-btn {
  border: none;
  background: #f1f5f9;
  width: 34px;
  height: 34px;
  border-radius: 8px;
  cursor: pointer;
  font-size: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: background 0.15s;
}
.icon-btn:hover {
  background: #e2e8f0;
}

.send-btn {
  height: 34px;
  padding: 0 18px;
  border: none;
  border-radius: 8px;
  background: linear-gradient(135deg, #6366f1, #4f46e5);
  color: #fff;
  font-size: 14px;
  font-weight: 500;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 6px;
  transition: all 0.15s ease;
}
.send-btn:hover:not(:disabled) {
  box-shadow: 0 6px 18px rgba(79, 70, 229, 0.35);
  transform: translateY(-1px);
}
.send-btn:disabled {
  background: #cbd5e1;
  cursor: not-allowed;
}

.spinner {
  width: 12px;
  height: 12px;
  border: 2px solid rgba(255, 255, 255, 0.4);
  border-top-color: #fff;
  border-radius: 50%;
  animation: spin 0.7s linear infinite;
}
@keyframes spin {
  to { transform: rotate(360deg); }
}
</style>