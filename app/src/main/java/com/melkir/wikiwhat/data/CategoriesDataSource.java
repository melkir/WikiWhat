package com.melkir.wikiwhat.data;

import com.melkir.wikiwhat.data.model.Category;
import com.melkir.wikiwhat.data.model.CategoryMember;
import com.melkir.wikiwhat.data.model.Page;

import java.util.List;

import io.reactivex.Observable;

public interface CategoriesDataSource {
    List<Category> getCategories();

    Observable<List<Category>> getRandomCategories(int limit);

    Observable<Category> getRandomCategory();

    Observable<List<CategoryMember>> getCategoryMembers(int id, int limit);

    Observable<List<CategoryMember>> getCategoryMembers(String title, int limit);

    Observable<Page> getPageContent(int id);

    Observable<Page> getPageContent(String title);
}
