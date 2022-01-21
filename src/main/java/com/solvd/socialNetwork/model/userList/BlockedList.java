package com.solvd.socialNetwork.model.userList;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "blockedList")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "profileId", "blockedProfileId"})

public class BlockedList {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("profileId")
    private Long profileId;

    @JsonProperty("blockedProfileId")
    private Long blockedProfileId;

    public BlockedList() {
    }

    public BlockedList(Long profileId, Long blockedProfileId) {
        this.profileId = profileId;
        this.blockedProfileId = blockedProfileId;
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

    @XmlElement(name="blockedProfileId")
    public Long getBlockedProfileId() {
        return blockedProfileId;
    }

    public void setBlockedProfileId(Long blockedProfileId) {
        this.blockedProfileId = blockedProfileId;
    }
}
