package com.melkir.wikiwhat.categories;

import android.support.annotation.NonNull;

import com.melkir.wikiwhat.data.CategoriesDataSource;
import com.melkir.wikiwhat.data.model.Category;
import com.melkir.wikiwhat.data.model.CategoryMember;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CategoriesPresenter implements CategoriesContract.Presenter {
    private static final String TAG = CategoriesPresenter.class.getSimpleName();

    private CategoriesDataSource mCategoriesRepository;
    private CategoriesContract.View mCategoriesView;
    private CompositeDisposable mCompositeDisposable;
    private int mTotalPoints;

    public CategoriesPresenter(@NonNull CategoriesDataSource categoriesRepository,
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

    private Observer<Category> categoryMemberObserver = new Observer<Category>() {
        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(Category category) {
            mCompositeDisposable.add(mCategoriesRepository.getCategoryMembers(category.getId(), 500)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(
                            data -> {
                                if (0 == data.size()) refreshCategoryAsync(category.getListIndex());
                                else {
                                    category.setCategoryMembers(data);
                                    mCategoriesView.refreshCategory(category.getListIndex(), category);
                                    mTotalPoints += data.size();
                                    mCategoriesView.showTotalCategoriesMembers(mTotalPoints);
                                }
                            },
                            err -> mCategoriesView.displayToast("Unable to retrieve members for " + category.getTitle())
                    ));
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    };

    @Override
    public void loadCategories() {
        mCategoriesView.setLoadingIndicator(true);
        mCompositeDisposable
                .add(mCategoriesRepository.getRandomCategories(3)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(
                                data -> {
                                    mCategoriesView.setLoadingIndicator(false);
                                    mCategoriesView.showCategories(data);
                                    mCategoriesRepository.setCachedCategories(data);
                                    Observable.fromIterable(data).subscribe(categoryMemberObserver);
                                },
                                err -> mCategoriesView.showNoCategories()
                        )
                );
    }

    @Override
    public void refreshCategoriesAsync() {
        mTotalPoints = 0;

        mCompositeDisposable
                .add(mCategoriesRepository.getRandomCategories(3)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(
                                data -> {
                                    mCategoriesView.refreshCategories(data);
                                    mCategoriesRepository.setCachedCategories(data);
                                    Observable.fromIterable(data).subscribe(categoryMemberObserver);
                                },
                                err -> mCategoriesView.displayToast("Unable to load data")
                        ));
    }

    @Override
    public void refreshCategoryAsync(int position) {
        List<CategoryMember> cachedCategoryMembers =mCategoriesRepository.getCachedCategories()
                .get(position).getCategoryMembers();
        if (cachedCategoryMembers != null) mTotalPoints -= cachedCategoryMembers.size();

        mCompositeDisposable
                .add(mCategoriesRepository.getRandomCategory()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(
                                data -> {
                                    mCategoriesView.refreshCategory(position, data);
                                    mCategoriesRepository.setCachedCategory(position, data);
                                    Observable.just(data).subscribe(categoryMemberObserver);
                                },
                                err -> mCategoriesView.displayToast("Unable to load data")
                        ));
    }

    @Override
    public int getRandomPageId() {
        return mCategoriesRepository.getRandomPageId();
    }

    @Override
    public int getTotalPoints() {
        return mTotalPoints;
    }

}
