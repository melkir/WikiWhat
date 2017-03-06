package com.melkir.wikiwhat.categories;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.melkir.wikiwhat.R;
import com.melkir.wikiwhat.data.CategoriesRepository;
import com.melkir.wikiwhat.util.ActivityUtils;

public class CategoriesActivity extends AppCompatActivity {
    private static final String TAG = CategoriesActivity.class.getSimpleName();
    private CategoriesPresenter mCategoriesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_activities);

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
}
