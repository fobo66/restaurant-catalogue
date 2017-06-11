package com.example.testapplication.pojo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;


/**
 * Model for representing category element in YML
 */
@Root(strict = false)
public class Category {
    @Text(required = false)
    private String text;

    @Attribute(name = "id", required = false)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Category(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Category() {
    }
}
