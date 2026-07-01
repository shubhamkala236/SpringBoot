package org.shub.spingai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class AIController {
    private final ChatClient chatClient;

    public AIController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping("/chat")
    public ChatResponseDto chat(@RequestBody ChatRequestDto request) {
        String reply = chatClient.prompt()
                .user(request.message())
                .call()
                .content();

        return new ChatResponseDto(reply);
    }

    @PostMapping(value = "/chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatStream(@RequestBody ChatRequestDto request) {
        return chatClient.prompt()
                .user(request.message())
                .stream()
                .content();
    }

    public record ChatRequestDto(String message) {}
    public record ChatResponseDto(String reply ) {}
}
