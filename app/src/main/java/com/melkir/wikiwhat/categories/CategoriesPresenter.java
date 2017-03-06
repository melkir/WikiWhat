package com.melkir.wikiwhat.categories;

import android.support.annotation.NonNull;

import com.melkir.wikiwhat.data.CategoriesRepository;
import com.melkir.wikiwhat.data.Category;

import java.util.List;

/**
 * Created by melkir on 06/03/17.
 */

public class CategoriesPresenter implements CategoriesContract.Presenter {
    private static final String TAG = CategoriesPresenter.class.getSimpleName();

    private CategoriesRepository mCategoriesRepository;
    private CategoriesContract.View mCategoriesView;

    public CategoriesPresenter(@NonNull CategoriesRepository categoriesRepository,
                               @NonNull CategoriesContract.View categoriesView) {
        mCategoriesRepository = categoriesRepository;
        mCategoriesView = categoriesView;
        mCategoriesView.setPresenter(this);
    }

    @Override
    public void start() {
        loadCategories();
    }

    @Override
    public void loadCategories() {
        List<Category> categories = mCategoriesRepository.getCategories();
        if (categories.isEmpty()) mCategoriesView.showNoCategories();
        else mCategoriesView.showCategories(categories);
    }

    @Override
    public void refreshCategories() {
        // TODO Refresh the list of categories by calling the API
    }

    @Override
    public void refreshCategory(int id) {
        // TODO Refresh one item of the list by calling the API
    }
}
