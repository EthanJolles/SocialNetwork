package com.solvd.socialNetwork.model.userList;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "friendList")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "profileId", "friendProfileId"})

public class FriendList {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("profileId")
    private Long profileId;

    @JsonProperty("friendProfileId")
    private Long friendProfileId;

    public FriendList() {
    }

    public FriendList(Long userId, Long friendProfileId) {
        this.profileId = userId;
        this.friendProfileId = friendProfileId;
    }

    @XmlElement(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name="profileId")
    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    @XmlElement(name="friendProfileId")
    public Long getFriendProfileId() {
        return friendProfileId;
    }

    public void setFriendProfileId(Long friendProfileId) {
        this.friendProfileId = friendProfileId;
    }
}
