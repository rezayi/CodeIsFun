package online.codeisfun.spring.ai

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.lang3.StringUtils
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor
import org.springframework.ai.chat.memory.ChatMemory
import org.springframework.ai.chat.prompt.PromptTemplate
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

    fun chatStream(chatRequest: ChatRequest): ChatResponse {
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
            .stream()

        val contentBuilder = StringBuilder()
        var totalTokens = 0
        response.chatResponse()
            .toStream()
            .forEach { chatResponse ->
                println("Metadata: ${chatResponse.metadata}")  // To see if 'metadata' contains usage information
                chatResponse.metadata?.let { metadata ->
                    metadata.usage?.let { usage ->
                        val tokensUsed = usage.totalTokens
                        totalTokens += tokensUsed
                        println("Tokens used: $tokensUsed")
                    } ?: run {
                        println("Usage info not available")
                    }
                }
                chatResponse.result.output.text
                    ?.let { content ->
                    contentBuilder.append(content)
                    println("content: $content")
                } ?: run {
                    println(chatResponse.metadata)
                }
            }
        println("final totalTokens: $totalTokens")

        return ChatResponse(
            chatId = chatId,
            response = contentBuilder.toString(),
            meta = null
        )
    }

    fun movieSuggestion(movieSuggestionRequest: MovieSuggestionRequest): ChatResponse {
        val requestTemplate = """
            I want a list of 5 movies with the genres: {genres}
            the movies should be between years {startYear} , {endYear}
            and I expect their IMDB score be higher that {imdbScore}.
            Then provide me with a list of 5 movies with their name, IMDB score, genres, production year.
             Provide the result in json format. don't describe anything. the response should be a valid json format.
             don't add ui formatter. I need it convertable to object.
        """.trimIndent()
        val template = PromptTemplate(requestTemplate)
        val params = mapOf(
            "genres" to movieSuggestionRequest.genres,
            "startYear" to movieSuggestionRequest.startYear,
            "endYear" to movieSuggestionRequest.endYear,
            "imdbScore" to movieSuggestionRequest.imdbScore,
        )
        val prompt = template.create(params)
        val chatId: String = UUID.randomUUID().toString()
        val response = chatClient
            .prompt(prompt)
            .advisors { advisorSpec ->
                advisorSpec.param(
                    AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY,
                    chatId
                )
            }
            .call()
        val trimmedResponse = response.content()
            ?.replace("```json", "")
            ?.replace("```", "")

        val list = ObjectMapper().readValue(trimmedResponse, object : TypeReference<List<MovieSuggestionResponse>>() {})
        return ChatResponse(
            chatId = chatId,
            response = list,
            meta = response.chatResponse()?.metadata
        )
    }
}