package com.example.testapplication.ui.item;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import com.example.testapplication.R;
import com.example.testapplication.pojo.Offer;
import com.example.testapplication.ui.base.BaseFragment;
import com.example.testapplication.util.Delayed;
import com.squareup.picasso.Picasso;

import nucleus.factory.RequiresPresenter;

@RequiresPresenter(ItemPresenter.class)
public class ItemFragment extends BaseFragment<ItemPresenter> {

    @BindView(R.id.dishName) TextView dishName;
    @BindView(R.id.dishPic) ImageView dishPicture;
    @BindView(R.id.dish_price) TextView dishPrice;
    @BindView(R.id.description) TextView dishDescription;
    @BindView(R.id.weight_card) TextView dishWeight;

    private Delayed<Integer> id = new Delayed<>(() -> getArguments().getInt("id"));

    public static ItemFragment create(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
            getPresenter().requestItem(id.get());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    void onItem(Offer item) {
        dishName.setText(item.getName());
        dishDescription.setText(item.getDescription());
        dishWeight.setText(item.getParams().get("Вес"));
        dishPrice.setText(item.getPrice());
        Picasso.with(getContext()).load(item.getPicture()).into(dishPicture);

    }

    void onNetworkError(Throwable throwable) {
        Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();
    }
}