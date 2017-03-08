package com.melkir.wikiwhat.categories;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.melkir.wikiwhat.R;
import com.melkir.wikiwhat.data.model.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.melkir.wikiwhat.categories.CategoriesFragment.CategoryItemListener;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private static final String TAG = CategoriesAdapter.class.getSimpleName();

    private List<Category> mCategories;
    private CategoryItemListener mItemListener;

    public CategoriesAdapter(List<Category> mCategories, CategoryItemListener mItemListener) {
        this.mCategories = mCategories;
        this.mItemListener = mItemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    public void addAll(List<Category> categories) {
        setList(categories);
        notifyDataSetChanged();
    }

    public void replace(int position, Category newCategory) {
        mCategories.set(position, newCategory);
        notifyDataSetChanged();
    }

    private void setList(List<Category> categories) {
        mCategories = checkNotNull(categories);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category category = mCategories.get(position);
        category.setListIndex(position);
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.category_title) TextView mTitle;
        @BindView(R.id.category_count) TextView mCount;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.category_item, parent, false));
            ButterKnife.bind(this, itemView);
        }

        public void bind(Category category) {
            mTitle.setText(category.getTitle());
            mCount.setText("(" + category.getCount() + " members)");
            itemView.setOnClickListener(v -> mItemListener.onCategoryClick(category));
        }
    }
}
