package com.melkir.wikiwhat.categories;

import android.support.annotation.NonNull;

import com.melkir.wikiwhat.data.CategoriesRepository;
import com.melkir.wikiwhat.data.model.Category;

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
    // TODO Use Wikipedia service
    public void loadCategories() {
        List<Category> categories = mCategoriesRepository.getCategories();
        if (categories.isEmpty()) mCategoriesView.showNoCategories();
        else mCategoriesView.showCategories(categories);
    }

    @Override
    // TODO Use Wikipedia service
    public void refreshCategoriesAsync() {
//        Observable<List<Category>> obsCategories = mCategoriesRepository.getRandomCategories(3);
//
//        mCategoriesView.clearAllCategories();
//        obsCategories
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                data -> {
//                    mCategoriesView.refreshCategories(data);
//                    mCategoriesView.setLoadingIndicator(false);
//                },
//                err -> {
//                    mCategoriesView.displayToast("Unable to load data");
//                    mCategoriesView.setLoadingIndicator(false);
//                }
//        );

        // onStart
//        mCategoriesView.clearAllCategories();
//        mCategoriesView.setLoadingIndicator(true); // already implemented by the widget

        // onSuccess
        // TODO Refresh asynchronously the list of categories by calling the API
//        mCategoriesView.refreshCategories(Collections.emptyList());

        // onFail
        mCategoriesView.displayToast("Unable to load data");

        // onFinish
        mCategoriesView.setLoadingIndicator(false);
    }

    @Override
    // TODO Use Wikipedia service
    public void refreshCategoryAsync(int position) {
        // onStart
//        mCategoriesView.clearCategory(position);
        mCategoriesView.setLoadingIndicator(true);

        // onSuccess
        // TODO Refresh asynchronously one item of the list by calling the API
//        mCategoriesView.refreshCategory(position, null);

        // onFail
        mCategoriesView.displayToast("Unable to load data");

        // onFinish
        mCategoriesView.setLoadingIndicator(false);
    }
}
