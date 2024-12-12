package io.quarkiverse.langchain4j.sample.hello;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.SessionScoped;

@RegisterAiService
@SessionScoped
public interface HelloAI {

    @SystemMessage("""
            You are an AI just answering questions.
            """)
    String chat(@UserMessage String question);
}
