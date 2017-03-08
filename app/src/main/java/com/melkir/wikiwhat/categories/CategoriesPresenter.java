package com.melkir.wikiwhat.categories;

import android.support.annotation.NonNull;

import com.melkir.wikiwhat.data.CategoriesRepository;
import com.melkir.wikiwhat.data.model.Category;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
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
                                category.setCategoryMembers(data);
                                mCategoriesView.refreshCategory(category.getListIndex(), category);
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
        mCompositeDisposable
                .add(mCategoriesRepository.getRandomCategories(3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        data -> {
                            mCategoriesView.showCategories(data);
                            Observable.fromIterable(data).subscribe(categoryMemberObserver);
                        },
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
                        data -> {
                            mCategoriesView.refreshCategories(data);
                            Observable.fromIterable(data).subscribe(categoryMemberObserver);
                        },
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
                        data -> {
                            mCategoriesView.refreshCategory(position, data);
                            Observable.just(data).subscribe(categoryMemberObserver);
                        },
                        err -> mCategoriesView.displayToast("Unable to load data")
                ));
    }
}
