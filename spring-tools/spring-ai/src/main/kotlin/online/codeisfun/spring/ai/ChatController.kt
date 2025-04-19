package online.codeisfun.spring.ai

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ai")
class ChatController(val openAiService: OpenAiService) {

    @PostMapping("chat")
    fun chat(@RequestBody chatRequest: ChatRequest): ChatResponse {
        return openAiService.chat(chatRequest)
    }
}