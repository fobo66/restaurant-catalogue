package com.example.testapplication.util.adapters;

/**
 * Interface for creating type-safe ViewHolders
 */
import android.view.ViewGroup;

public interface ViewHolderType<T> {
    boolean isOfItem(Object item);
    BaseViewHolder<T> create(ViewGroup parent);
}