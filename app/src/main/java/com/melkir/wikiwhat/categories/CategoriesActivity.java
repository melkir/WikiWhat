package com.melkir.wikiwhat.categories;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.melkir.wikiwhat.R;
import com.melkir.wikiwhat.data.CategoriesRepository;
import com.melkir.wikiwhat.game.GameActivity;
import com.melkir.wikiwhat.util.ActivityUtils;

public class CategoriesActivity extends AppCompatActivity {
    private static final String TAG = CategoriesActivity.class.getSimpleName();
    private CategoriesPresenter mCategoriesPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_activity);

        CategoriesFragment categoriesFragment =
                (CategoriesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (categoriesFragment == null) {
            // Create the fragment
            categoriesFragment = CategoriesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), categoriesFragment, R.id.contentFrame);
        }

        // Create the presenter
        mCategoriesPresenter = new CategoriesPresenter(new CategoriesRepository(this), categoriesFragment);
    }

    public void startSession(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(GameActivity.EXTRA_PAGE_ID, "0");
        startActivity(intent);
//        Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
    }
}
