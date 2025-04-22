package online.codeisfun.spring.ai

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.ai.chat.metadata.ChatResponseMetadata

data class ChatRequest(
    val chatId: String?,
    val question: String
)

data class ChatResponse(
    val chatId: String,
    val response: Any?,
    val meta: ChatResponseMetadata?
)

data class MovieSuggestionRequest(
    val imdbScore: String?,
    val genres: String?,
    val startYear: Int?,
    val endYear: Int?,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieSuggestionResponse(
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("imdb_score")
    val imdbScore: String?,
    @JsonProperty("genres")
    val genres: List<String>?,
    @JsonProperty("production_year")
    val productionYear: Int?,
)