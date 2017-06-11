package com.example.testapplication.pojo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.Map;

/**
 * Model for representing offer element in YML
 */
@Root(strict = false, name = "offer")
public class Offer {

    @Path("yml_catalog/shop/offers/offer")
    @Attribute(required = false)
    private int id;

    @Path("yml_catalog/shop/offers/offer")
    @Element(name = "url", required = false)
    private String url;

    @Path("yml_catalog/shop/offers/offer")
    @Element(name = "name", required = false)
    private String name;

    @Path("yml_catalog/shop/offers/offer")
    @Element(name = "price", required = false)
    private String price;

    @Path("yml_catalog/shop/offers/offer")
    @Element(name = "description", required = false)
    private String description;

    @Path("yml_catalog/shop/offers/offer")
    @Element(name = "picture", required = false)
    private String picture;

    @Path("yml_catalog/shop/offers/offer")
    @Element(name = "categoryId", required = false)
    private int categoryId;

    @Path("yml_catalog/shop/offers")
    @ElementMap(entry = "param", key = "name", attribute = true, inline = true, required = false)
    private Map<String, String> params;

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}

