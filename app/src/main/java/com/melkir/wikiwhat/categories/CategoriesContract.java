package com.melkir.wikiwhat.categories;

import com.melkir.wikiwhat.BasePresenter;
import com.melkir.wikiwhat.BaseView;
import com.melkir.wikiwhat.data.model.Category;

import java.util.List;

public interface CategoriesContract {

    interface View extends BaseView<Presenter> {
        void showCategories(List<Category> categories);

        void showNoCategories();

        void refreshCategories(List<Category> categories);

        void refreshCategory(int position, Category newCategory);

        void displayToast(String message);

        void showTotalCategoriesMembers(int total);

        void setLoadingIndicator(boolean condition);
    }

    interface Presenter extends BasePresenter {
        void stop();

        void loadCategories();

        void refreshCategoriesAsync();

        void refreshCategoryAsync(int position);

        int getRandomPageId();

        int getTotalPoints();
    }
}
