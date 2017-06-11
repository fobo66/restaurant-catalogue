package com.example.testapplication.util.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testapplication.R;
import com.example.testapplication.app.App;
import com.example.testapplication.pojo.Offer;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class MainViewHolder extends BaseViewHolder<Offer> {

    @BindView(R.id.dish_name) TextView dishName;
    @BindView(R.id.weight_card) TextView dishWeight;
    @BindView(R.id.price_card) TextView dishPrice;
    @BindView(R.id.dishpic) ImageView dishPic;

    private Offer item;
    private Context context;

    public MainViewHolder(View view, Context context, Action1<Offer> onClick) {
        super(view);

        ButterKnife.bind(this, view);
        this.context = context;
        view.setOnClickListener(v -> onClick.call(item));
    }

    @Override
    public void bind(Offer item) {
        this.item = item;
        dishName.setText(item.getName());
        Picasso.with(context).load(item.getPicture()).into(dishPic);
        dishWeight.setText(item.getParams().get("Вес"));
        dishPrice.setText(item.getPrice());
    }
}
