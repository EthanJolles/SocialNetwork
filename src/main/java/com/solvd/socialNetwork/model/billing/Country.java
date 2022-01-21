package com.solvd.socialNetwork.model.billing;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "country")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "country"})

public class Country {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("country")
    private String country;

    public Country() {
    }

    public Country(String country) {
        this.country = country;
    }

    @XmlElement(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name="country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
