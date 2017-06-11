package com.example.testapplication.util;

import com.example.testapplication.pojo.Offer;
import com.example.testapplication.pojo.Offers;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see com.example.testapplication.util.CategoriesConverter
 *
 * Almost the same converter, but for Offers
 */

public class OffersConverter implements Converter<Offers> {

    @Override
    public Offers read(InputNode node) throws Exception {
        Offers offers = new Offers();
        InputNode childNode = node.getNext();
        List<Offer> offersList = new ArrayList<>();
        while( childNode != null ) {
            if (childNode.getName().equals("offers")) {
                InputNode innerChild = childNode.getNext();

                while (innerChild != null) {
                    Offer offer = new Offer();
                    Map<String , String> paramMap = new HashMap<>();
                    if (innerChild.getName().equals("offer")) {
                        offer.setId(Integer.parseInt(innerChild.getAttribute("id").getValue()));
                        offer.setUrl(innerChild.getNext("url").getValue());
                        offer.setName(innerChild.getNext("name").getValue());
                        offer.setPrice(innerChild.getNext("price").getValue());
                        offer.setDescription(innerChild.getNext("description").getValue());
                        offer.setPicture(innerChild.getNext("picture").getValue());
                        offer.setCategoryId(Integer.parseInt(innerChild.getNext("categoryId").getValue()));

                        InputNode paramNode = innerChild.getNext("param");
                        while (paramNode != null) {
                            paramMap.put(paramNode.getAttribute("name").getValue(), paramNode.getValue());
                            paramNode = innerChild.getNext("param");
                        }
                        offer.setParams(paramMap);
                        offersList.add(offer);
                    }

                    innerChild = childNode.getNext();
                }
            }

            childNode = node.getNext();
        }

        offers.setOffers(offersList);
        return offers;
    }

    @Override
    public void write(OutputNode node, Offers value) throws Exception {
        //we don't need to serialize objects now, so it does nothing
    }
}

