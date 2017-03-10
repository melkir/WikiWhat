package com.melkir.wikiwhat.categories;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.melkir.wikiwhat.R;
import com.melkir.wikiwhat.data.model.Category;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class CategoriesFragment extends Fragment implements CategoriesContract.View {
    private static final String TAG = CategoriesFragment.class.getSimpleName();

    private CategoriesContract.Presenter mPresenter;
    private CategoriesAdapter mListAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.noCategories)
    View mNoCategoriesView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.totalPageCount)
    TextView mViewPageCount;

    public CategoriesFragment() {
        // Requires empty public constructor
    }

    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new CategoriesAdapter(new ArrayList<>(0), mItemListener);
    }

    @Override
    public void refreshCategories(List<Category> categories) {
        mListAdapter.addAll(categories);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void refreshCategory(int position, Category newCategory) {
        mListAdapter.replace(position, newCategory);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void displayToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTotalCategoriesMembers(int total) {
        mViewPageCount.setText(Integer.toString(total));
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.stop();
    }

    @Override
    public void setPresenter(@NonNull CategoriesContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.categories_fragment, container, false);
        ButterKnife.bind(this, root);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mListAdapter);
        mRecyclerView.setHasFixedSize(true);

        mSwipeRefreshLayout.setOnRefreshListener(() -> mPresenter.refreshCategoriesAsync());

        return root;
    }

    @Override
    public void showCategories(List<Category> categories) {
        mListAdapter.addAll(categories);

        mRecyclerView.setVisibility(View.VISIBLE);
        mNoCategoriesView.setVisibility(View.GONE);
    }

    @Override
    public void showNoCategories() {
        mRecyclerView.setVisibility(View.GONE);
        mNoCategoriesView.setVisibility(View.VISIBLE);
    }

    private final CategoryItemListener mItemListener =
            clickedCategory -> mPresenter.refreshCategoryAsync(clickedCategory.getListIndex());

    interface CategoryItemListener {
        void onCategoryClick(Category clickedCategory);
    }
}
