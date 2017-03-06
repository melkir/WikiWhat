package com.melkir.wikiwhat.categories;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.melkir.wikiwhat.R;
import com.melkir.wikiwhat.data.Category;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class CategoriesFragment extends Fragment implements CategoriesContract.View {
    private static final String TAG = CategoriesFragment.class.getSimpleName();

    private CategoriesContract.Presenter mPresenter;
    private CategoriesAdapter mListAdapter;

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
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull CategoriesContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    CategoryItemListener mItemListener = clickedCategory -> {
        // TODO Do something on category click like refreshing this category ?
    };

    @Override
    public void setLoadingIndicator(boolean active) {
        // TODO Indicate that we are currently fetching categories
    }

    @Override
    public void showCategories(List<Category> categories) {
        // TODO Display categories
    }

    @Override
    public void showNoCategories() {
        // TODO Display that no categories is available
    }

    private class CategoriesAdapter extends BaseAdapter {
        private List<Category> mCategories;
        private CategoryItemListener mItemListener;

        public CategoriesAdapter(List<Category> categories, CategoryItemListener itemListener) {
            setList(categories);
            mItemListener = itemListener;
        }


        public void replaceData(List<Category> categories) {
            setList(categories);
            notifyDataSetChanged();
        }

        private void setList(List<Category> categories) {
            mCategories = checkNotNull(categories);
        }


        @Override
        public int getCount() {
            return mCategories.size();
        }

        @Override
        public Category getItem(int i) {
            return mCategories.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                view = inflater.inflate(R.layout.category_item, parent, false);
            }
            final Category category = getItem(i);
            TextView title = (TextView) view.findViewById(R.id.category_title);
            title.setText(category.getTitle());
            TextView count = (TextView) view.findViewById(R.id.category_count);
            count.setText(category.getCount());
            // TODO Set a refresh button for each categories
            return view;
        }
    }

    public interface CategoryItemListener {
        void onCategoryClick(Category clickedCategory);
    }
}
