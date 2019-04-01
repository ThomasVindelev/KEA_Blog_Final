package com.keablog.demo.Model;

public class Chat {

    private int id;
    private String text;
    private String sent;
    private String received;
    private int sent_to;
    private int sent_from;

    public Chat() {

    }

    public Chat(int id, String text, String sent, String received, int sent_to, int sent_from) {
        this.id = id;
        this.text = text;
        this.sent = sent;
        this.received = received;
        this.sent_to = sent_to;
        this.sent_from = sent_from;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    public int getSent_to() {
        return sent_to;
    }

    public void setSent_to(int sent_to) {
        this.sent_to = sent_to;
    }

    public int getSent_from() {
        return sent_from;
    }

    public void setSent_from(int sent_from) {
        this.sent_from = sent_from;
    }
}
