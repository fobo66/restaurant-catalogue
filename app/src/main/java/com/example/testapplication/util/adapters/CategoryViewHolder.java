package com.example.testapplication.util.adapters;

import android.view.View;
import android.widget.TextView;

import com.example.testapplication.R;
import com.example.testapplication.pojo.Category;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class CategoryViewHolder extends BaseViewHolder<Category> {

    @BindView(R.id.category_name) TextView categoryName;

    private Category item;

    public CategoryViewHolder(View view, Action1<Category> onClick) {
        super(view);
        ButterKnife.bind(this, view);

        view.setOnClickListener(v -> onClick.call(item));
    }

    @Override
    public void bind(Category item) {
        this.item = item;
        categoryName.setText(item.getText());
    }
}