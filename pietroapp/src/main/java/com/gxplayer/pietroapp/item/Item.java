package com.gxplayer.pietroapp.item;


import com.gxplayer.pietroapp.order.Order;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    private String name;
    private String shortDescription;
    @Column(length=1024)
    private String description;
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @ManyToMany(mappedBy = "items")
    private Collection<Order> client_order;


    public Collection<Order> getClient_order() {
        return client_order;
    }

    public void setClient_order(Collection<Order> client_order) {
        this.client_order = client_order;
    }
}
