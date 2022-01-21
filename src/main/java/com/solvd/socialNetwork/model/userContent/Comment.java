package com.solvd.socialNetwork.model.userContent;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "comment")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "contents", "postId"})

public class Comment {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("contents")
    private String contents;

    @JsonProperty("postId")
    private Long postId;

    public Comment() {
    }

    public Comment(String contents, Long postId) {
        this.contents = contents;
        this.postId = postId;
    }

    @XmlElement(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name="contents")
    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @XmlElement(name="postId")
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
