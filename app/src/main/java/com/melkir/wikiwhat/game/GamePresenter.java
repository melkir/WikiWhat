package com.melkir.wikiwhat.game;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.melkir.wikiwhat.data.CategoriesDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public class GamePresenter implements GameContract.Presenter {
    @Nullable
    private final String mPageId;

    @NonNull
    private final CategoriesDataSource mCategoriesRepository;

    @Nullable
    private final GameContract.View mGameView;

    public GamePresenter(@Nullable String pageId,
                         @NonNull CategoriesDataSource categoriesRepository,
                         @NonNull GameContract.View gameView) {
        mPageId = pageId;
        mCategoriesRepository = checkNotNull(categoriesRepository);
        mGameView = checkNotNull(gameView);
        mGameView.setPresenter(this);
    }

    @Override
    public void start() {
        populatePage();
    }

    private void populatePage() {
    }

    @Override
    public void stop() {
    }
}
