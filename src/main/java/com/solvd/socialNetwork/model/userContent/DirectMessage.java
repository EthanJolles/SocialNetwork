package com.solvd.socialNetwork.model.userContent;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement(name = "directMessage")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "message", "dateSent", "recipientId", "userId"})

public class DirectMessage {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("message")
    private String message;

    @JsonProperty("dateSent")
    private Date dateSent;

    @JsonProperty("recipientId")
    private Long recipientId;

    @JsonProperty("userId")
    private Long userId;

    public DirectMessage() {
    }

    public DirectMessage(String message, Date dateSent, Long recipientId, Long userId) {
        this.message = message;
        this.dateSent = dateSent;
        this.recipientId = recipientId;
        this.userId = userId;
    }

    @XmlElement(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name="message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @XmlElement(name="dateSent")
    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    @XmlElement(name="recipientId")
    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    @XmlElement(name="userId")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
