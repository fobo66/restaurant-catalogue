package com.example.testapplication.ui.cat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.testapplication.R;
import com.example.testapplication.pojo.Category;
import com.example.testapplication.ui.base.BaseFragment;
import com.example.testapplication.ui.main.MainActivity;
import com.example.testapplication.ui.main.MainFragment;
import com.example.testapplication.util.adapters.ClassViewHolderType;
import com.example.testapplication.util.adapters.SimpleListAdapter;
import com.example.testapplication.util.adapters.CategoryViewHolder;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import nucleus.factory.RequiresPresenter;
import retrofit2.adapter.rxjava.HttpException;


@RequiresPresenter(CategoriesPresenter.class)
public class CategoriesFragment extends BaseFragment<CategoriesPresenter> {

    private static final String TAG = "CategoriesFragment";

    @BindView(R.id.cat_recyclerview) RecyclerView recyclerView;
    private SimpleListAdapter<Category> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getPresenter().request();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new SimpleListAdapter<>(R.layout.recycler_view_progress,
                new ClassViewHolderType<>(
                    Category.class,
                    R.layout.cat_item,
                    v -> new CategoryViewHolder(v, this::onItemClick)));

        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

    }

    private void onItemClick(Category item) {
        ((MainActivity) getActivity()).push(MainFragment.create(item.getId()));
    }

    void onItems(List<Category> items) {
        adapter.add(items);
    }

    void onNetworkError(Throwable throwable) {
        adapter.hideProgress();
        if (throwable instanceof HttpException) {
            try {
                Log.e(TAG, "Something wrong with your query: " + ((HttpException) throwable).response().errorBody().string() );
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (throwable instanceof IOException) {
            Log.e(TAG, "Network Error or bad conversion: ", throwable.getCause());
        }

        Log.e(TAG, "Something goes wrong: ", throwable.getCause());
        Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();
    }
}
