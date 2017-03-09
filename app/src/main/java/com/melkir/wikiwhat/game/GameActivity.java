package com.melkir.wikiwhat.game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.melkir.wikiwhat.R;
import com.melkir.wikiwhat.data.CategoriesRepository;
import com.melkir.wikiwhat.util.ActivityUtils;

public class GameActivity extends AppCompatActivity {
    private GamePresenter mGamePresenter;
    public static final String EXTRA_PAGE_ID = "PAGE_ID";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        GameFragment gameFragment = (GameFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (gameFragment == null) {
            // Create the fragment
            gameFragment = GameFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), gameFragment, R.id.contentFrame);
        }

        String pageId = getIntent().getStringExtra(EXTRA_PAGE_ID);

        // Create the presenter
        mGamePresenter = new GamePresenter(pageId, new CategoriesRepository(this), gameFragment);
    }

}
