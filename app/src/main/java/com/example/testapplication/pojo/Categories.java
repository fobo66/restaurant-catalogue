package com.example.testapplication.pojo;

import com.example.testapplication.util.CategoriesConverter;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

import java.util.ArrayList;
import java.util.List;


/**
 * Wrapper for Category class for convenient converting
 */
@Convert(CategoriesConverter.class)
@Root(strict = false, name = "categories")
public class Categories {

    @Path("yml_catalog/shop/categories")
    @ElementList(required = false, inline = true, entry="category", type = Category.class)
    public List<Category> categories;

    public Categories() {
        this.categories = new ArrayList<>();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
