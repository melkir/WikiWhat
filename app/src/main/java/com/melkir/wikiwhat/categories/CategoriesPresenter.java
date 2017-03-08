package com.melkir.wikiwhat.categories;

import android.support.annotation.NonNull;

import com.melkir.wikiwhat.data.CategoriesRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CategoriesPresenter implements CategoriesContract.Presenter {
    private static final String TAG = CategoriesPresenter.class.getSimpleName();

    private CategoriesRepository mCategoriesRepository;
    private CategoriesContract.View mCategoriesView;
    private CompositeDisposable mCompositeDisposable;

    public CategoriesPresenter(@NonNull CategoriesRepository categoriesRepository,
                               @NonNull CategoriesContract.View categoriesView) {
        mCategoriesRepository = categoriesRepository;
        mCategoriesView = categoriesView;
        mCategoriesView.setPresenter(this);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void start() {
        loadCategories();
    }

    @Override
    public void stop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void loadCategories() {
        mCompositeDisposable
                .add(mCategoriesRepository.getRandomCategories(3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        data -> mCategoriesView.showCategories(data),
                        err -> mCategoriesView.showNoCategories()
                )
        );
    }

    @Override
    public void refreshCategoriesAsync() {
        mCompositeDisposable
                .add(mCategoriesRepository.getRandomCategories(3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        data -> mCategoriesView.refreshCategories(data),
                        err -> mCategoriesView.displayToast("Unable to load data")
                ));
    }

    @Override
    // TODO Use Wikipedia service
    public void refreshCategoryAsync(int position) {
        mCompositeDisposable
                .add(mCategoriesRepository.getRandomCategory()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        data -> mCategoriesView.refreshCategory(position, data),
                        err -> mCategoriesView.displayToast("Unable to load data")
                ));
    }
}
