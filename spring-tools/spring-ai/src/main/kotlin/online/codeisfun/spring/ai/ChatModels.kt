package online.codeisfun.spring.ai

import org.springframework.ai.chat.metadata.ChatResponseMetadata

data class ChatRequest(
    val chatId: String?,
    val question: String
)

data class ChatResponse(
    val chatId: String,
    val response: String?,
    val meta: ChatResponseMetadata?
)