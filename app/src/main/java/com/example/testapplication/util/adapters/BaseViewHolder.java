package com.example.testapplication.util.adapters;

/**
 * Base class for ViewHolders, to bind view with tha data it contains
 */
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View view) {
        super(view);
    }

    public abstract void bind(T item);
}
