package com.melkir.wikiwhat.categories;

import com.melkir.wikiwhat.BasePresenter;
import com.melkir.wikiwhat.BaseView;
import com.melkir.wikiwhat.data.model.Category;

import java.util.List;

public interface CategoriesContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showCategories(List<Category> categories);

        void showNoCategories();

        void clearCategory(int position);

        void clearAllCategories();

        void refreshCategories(List<Category> categories);

        void refreshCategory(int position, Category newCategory);

        void displayToast(String message);
    }

    interface Presenter extends BasePresenter {

        void loadCategories();

        void refreshCategoriesAsync();

        void refreshCategoryAsync(int position);
    }
}
