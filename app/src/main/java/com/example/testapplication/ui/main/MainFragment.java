package com.example.testapplication.ui.main;

/**
 * Created by fobo66 on 17.09.2016.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import com.example.testapplication.R;
import com.example.testapplication.network.ServerAPI;
import com.example.testapplication.pojo.Offer;
import com.example.testapplication.ui.base.BaseFragment;
import com.example.testapplication.ui.item.ItemFragment;
import com.example.testapplication.util.Delayed;
import com.example.testapplication.util.OnScrollPaging;
import com.example.testapplication.util.PageBundle;
import com.example.testapplication.util.RxPager;
import com.example.testapplication.util.adapters.ClassViewHolderType;
import com.example.testapplication.util.adapters.MainViewHolder;
import com.example.testapplication.util.adapters.SimpleListAdapter;
import com.example.testapplication.util.adapters.CategoryViewHolder;

import java.util.List;

import nucleus.factory.RequiresPresenter;

import static java.util.Arrays.asList;

@RequiresPresenter(MainPresenter.class)
public class MainFragment extends BaseFragment<MainPresenter> {

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    private int category;
    private SimpleListAdapter<Offer> adapter;
    private RxPager pager;

    private Delayed<Integer> categoryId = new Delayed<>(() -> getArguments().getInt("id"));

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        if (bundle == null) {
            getPresenter().request(categoryId.get());
        }
    }

    public static MainFragment create(int categoryId) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", categoryId);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pager = new RxPager(10, page -> {
            adapter.showProgress();
            getPresenter().requestNext(page);
        });

        adapter = new SimpleListAdapter<>(R.layout.recycler_view_progress,
                new ClassViewHolderType<>(Offer.class, R.layout.item, v -> new MainViewHolder(v, getContext(), this::onItemClick)));
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new OnScrollPaging(layoutManager, adapter, pager::next));
    }

    void onItems(PageBundle<List<Offer>> page, int category) {
        this.category = category;

        List<Offer> items = page.data;
        pager.received(items.size());

        adapter.hideProgress();
        if (page.page != 0)
            adapter.add(items);
        else {
            recyclerView.scrollToPosition(0);
            adapter.set(items);
        }
    }

    void onNetworkError(Throwable throwable) {
        adapter.hideProgress();
        throwable.printStackTrace();
        Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void onItemClick(Offer item) {
        ((MainActivity) getActivity()).push(ItemFragment.create(item.getId()));
    }
}