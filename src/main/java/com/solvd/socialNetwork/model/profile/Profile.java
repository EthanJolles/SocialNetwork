package com.solvd.socialNetwork.model.profile;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement(name = "profile")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "firstName", "lastName", "middleInitial",
        "birthday", "age", "bio", "isVerified", "userId" })

public class Profile {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("middleInitial")
    private String middleInitial;

    @JsonProperty("birthday")
    private Date birthday;

    @JsonProperty("age")
    private Long age;

    @JsonProperty("bio")
    private String bio;

    @JsonProperty("isVerified")
    private Boolean isVerified;

    @JsonProperty("userId")
    private Long userId;

    public Profile() {
    }

    public Profile(String firstName, String lastName, String middleInitial,
                   Date birthday, Long age, String bio, Boolean isVerified, Long userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleInitial = middleInitial;
        this.birthday = birthday;
        this.age = age;
        this.bio = bio;
        this.isVerified = isVerified;
        this.userId = userId;
    }

    @XmlElement(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name="firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name="lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name="middleInitial")
    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    @XmlElement(name="birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @XmlElement(name="age")
    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @XmlElement(name="bio")
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @XmlElement(name="isVerified")
    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    @XmlElement(name="userId")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
