package com.solvd.socialNetwork.model.userContent;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "location", "caption", "isPicture", "userId"})

public class Post {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("location")
    private String location;

    @JsonProperty("caption")
    private String caption;

    @JsonProperty("isPicture")
    private Boolean isPicture;

    @JsonProperty("userId")
    private Long userId;

    public Post() {
    }

    public Post(String location, String caption, Boolean isPicture, Long userId) {
        this.location = location;
        this.caption = caption;
        this.isPicture = isPicture;
        this.userId = userId;
    }

    @XmlElement(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name="location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlElement(name="caption")
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @XmlElement(name="isPicture")
    public Boolean getIsPicture() {
        return isPicture;
    }

    public void setIsPicture(Boolean isPicture) {
        this.isPicture = isPicture;
    }

    @XmlElement(name="userId")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
