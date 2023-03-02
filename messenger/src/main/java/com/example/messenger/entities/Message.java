package com.example.messenger.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "message")
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "rq_id")
    private long rqId;

    @Column(name = "from1")
    private String from;

    @Column(name = "to1")
    private String to;

    @Column(name = "payload")
    private String payLoad;

    public Message() {
    }

    public long getId() {
        return id;
    }

    public Message setId(long id) {
        this.id = id;
        return this;
    }

    public long getRqId() {
        return rqId;
    }

    public Message setRqId(long rqId) {
        this.rqId = rqId;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public Message setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public Message setTo(String to) {
        this.to = to;
        return this;
    }

    public String getPayLoad() {
        return payLoad;
    }

    public Message setPayLoad(String payLoad) {
        this.payLoad = payLoad;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message message)) return false;
        return getId() == message.getId() && getRqId() == message.getRqId() && Objects.equals(getFrom(), message.getFrom()) && Objects.equals(getTo(), message.getTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRqId(), getFrom(), getTo());
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", rqId=" + rqId +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }

}
