package com.melkir.wikiwhat.game;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.melkir.wikiwhat.data.CategoriesDataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

public class GamePresenter implements GameContract.Presenter {
    private static final String TAG = GamePresenter.class.getSimpleName();

    @Nullable
    private final int mPageId;

    @NonNull
    private final CategoriesDataSource mCategoriesRepository;

    @Nullable
    private final GameContract.View mGameView;

    private CompositeDisposable mCompositeDisposable;

    public GamePresenter(int pageId,
                         @NonNull CategoriesDataSource categoriesRepository,
                         @NonNull GameContract.View gameView) {
        mPageId = pageId;
        mCategoriesRepository = checkNotNull(categoriesRepository);
        mGameView = checkNotNull(gameView);
        mGameView.setPresenter(this);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void start() {
        populatePage();
    }

    private void populatePage() {
        mCompositeDisposable.add(mCategoriesRepository.getPageContent(mPageId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(data -> {
                            mGameView.showPageContent(data);
                        }, err -> {
                            Log.d(TAG, "Error while retrieving page content " + err);
                            mGameView.showNoPageContent();
                        }
                )
        );
    }

    @Override
    public void stop() {
        mCompositeDisposable.clear();
    }
}
