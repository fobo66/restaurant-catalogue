package com.example.testapplication.pojo;
/**
 * Folding class for convenient converting and transformation
 */

import com.example.testapplication.util.OffersConverter;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

import java.util.ArrayList;
import java.util.List;
@Convert(OffersConverter.class)
@Root(strict = false, name = "offers")
public class Offers {

    @Path("yml_catalog/shop/offers")
    @ElementList(required = false, inline=true, entry = "offer", type = Offer.class)
    private List<Offer> offers;

    public Offers(List<Offer> offers) {
        this.offers = offers;
    }

    public Offers() {
        this.offers = new ArrayList<>();
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
