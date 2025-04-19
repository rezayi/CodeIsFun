package online.codeisfun.spring.ai

import online.codeisfun.spring.ai.ChatRequest
import online.codeisfun.spring.ai.ChatResponse
import org.apache.commons.lang3.StringUtils
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor
import org.springframework.ai.chat.memory.ChatMemory
import org.springframework.stereotype.Service
import java.util.*

@Service
class OpenAiService(
    builder: ChatClient.Builder, chatMemory: ChatMemory
) {
    private val chatClient: ChatClient = builder
        .defaultAdvisors(
            SimpleLoggerAdvisor(),
            SafeGuardAdvisor(listOf("hate", "violence")),
            MessageChatMemoryAdvisor(chatMemory)
        )
        .build()

    fun chat(chatRequest: ChatRequest): ChatResponse {
        val chatId: String = StringUtils.defaultIfBlank(chatRequest.chatId, UUID.randomUUID().toString())
        val response = chatClient
            .prompt()
            .advisors { advisorSpec ->
                advisorSpec.param(
                    AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY,
                    chatId
                )
            }
            .user(chatRequest.question)
            .call()
        return ChatResponse(
            chatId = chatId,
            response = response.content(),
            meta = response.chatResponse()?.metadata
        )
    }
}