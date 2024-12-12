package io.quarkiverse.langchain4j.sample.chatbot;

import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;

@WebSocket(path = "/chatbot")
public class ChatBotWebSocket {

    private final Bot bot;

    public ChatBotWebSocket(Bot bot) {
        this.bot = bot;
    }

    @OnOpen
    public String onOpen() {
        return "Hello, I'm Bob, how can I help you?";
    }

    @OnTextMessage
    // TODO does not seem to work - caused/fixed by https://github.com/quarkiverse/quarkus-langchain4j/pull/1131??
    // public Multi<String> onMessage(String message) {
    public String onMessage(String message) {
            return bot.chat(message);
    }

}
