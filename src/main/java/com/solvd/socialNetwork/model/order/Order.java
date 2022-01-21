package com.solvd.socialNetwork.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "userId", "productId"})

public class Order {

    @JsonProperty
    private Long id;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("productId")
    private Long productId;

    public Order() {
    }

    public Order(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }

    @XmlElement(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name="userId")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @XmlElement(name="productId")
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
