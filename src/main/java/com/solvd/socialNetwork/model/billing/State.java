package com.solvd.socialNetwork.model.billing;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "state")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "state", "countryId"})

public class State {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("state")
    private String state;

    @JsonProperty("countryId")
    private Long countryId;

    public State() {
    }

    public State( String state, Long countryId) {
        this.state = state;
        this.countryId = countryId;
    }

    @XmlElement(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name="state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @XmlElement(name="countryId")
    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}
