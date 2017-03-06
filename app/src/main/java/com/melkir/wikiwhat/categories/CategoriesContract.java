package com.melkir.wikiwhat.categories;

import com.melkir.wikiwhat.BasePresenter;
import com.melkir.wikiwhat.BaseView;
import com.melkir.wikiwhat.data.Category;

import java.util.List;

public interface CategoriesContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showCategories(List<Category> categories);

        void showNoCategories();

        void refreshCategories(List<Category> success);

        void refreshCategory(int id);
    }

    interface Presenter extends BasePresenter {

        void loadCategories();

        void refreshCategoriesAsync();

        void refreshCategoryAsync(int id);
    }
}
