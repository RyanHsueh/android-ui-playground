package com.example.ryanhsueh.uiplayground.data;

public class Message {

    public enum Type {
        RECEIVED, SENT
    }

    private String content;
    private Type type;

    public Message(String content, Type type) {
        this.content = content;
        this.type = type;

    }

    public String getContent() {
        return content;
    }

    public Type getType() {
        return type;
    }

}
