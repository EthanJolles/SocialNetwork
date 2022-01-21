package com.solvd.socialNetwork.model.billing;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "billingAddress")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "zip", "street", "userId", "cityId"})

public class BillingAddress {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("zip")
    private Integer zip;

    @JsonProperty("street")
    private String street;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("cityId")
    private Long cityId;

    public BillingAddress() {
    }

    public BillingAddress(Integer zip, String street, Long userId, Long cityId) {
        this.zip = zip;
        this.street = street;
        this.userId = userId;
        this.cityId = cityId;
    }

    @XmlElement(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name="zip")
    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    @XmlElement(name="street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @XmlElement(name="userId")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @XmlElement(name="cityId")
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
