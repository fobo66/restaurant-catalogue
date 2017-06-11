package com.example.testapplication.util;

import com.example.testapplication.pojo.Categories;
import com.example.testapplication.pojo.Category;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter for SimpleXML to properly parse Categories class from YML
 */

public class CategoriesConverter implements Converter<Categories> {

    @Override
    public Categories read(InputNode node) throws Exception {
        Categories categories = new Categories();
        InputNode childNode = node.getNext();
        List<Category> categoriesList = new ArrayList<>();
        while( childNode != null ) {
            if (childNode.getName().equals("categories")) {
                InputNode innerChild = childNode.getNext();

                while (innerChild != null) {
                    Category category = new Category();
                    if (innerChild.getName().equals("category")) {
                        category.setId(Integer.parseInt(innerChild.getAttribute("id").getValue()));
                        category.setText(innerChild.getValue());
                        categoriesList.add(category);
                    }

                    innerChild = childNode.getNext();
                }
            }

            childNode = node.getNext();
        }

        categories.setCategories(categoriesList);
        return categories;
    }

    @Override
    public void write(OutputNode node, Categories value) throws Exception {
        //we don't need to serialize objects now, so it does nothing
    }
}

