package com.melkir.wikiwhat.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.melkir.wikiwhat.data.model.Category;
import com.melkir.wikiwhat.data.model.CategoryMember;
import com.melkir.wikiwhat.data.model.Page;
import com.melkir.wikiwhat.data.wikipedia.WikipediaService;
import com.melkir.wikiwhat.data.wikipedia.util.ItemDeserializer;
import com.melkir.wikiwhat.data.wikipedia.util.KeyDeserializer;
import com.melkir.wikiwhat.data.wikipedia.util.ListDeserializer;
import com.melkir.wikiwhat.util.MathUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoriesRepository implements CategoriesDataSource {
    private List<Category> mCachedCategories = new ArrayList<>();
    private WikipediaService mWikipediaService;

    public CategoriesRepository() {
        mWikipediaService = newWikipediaServiceInstance();
    }

    public CategoriesRepository(Context context) {
        mWikipediaService = newWikipediaServiceInstance();
    }

    private WikipediaService newWikipediaServiceInstance() {
        // Asynchronous retrofit (for production)
        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
        // Synchronous retrofit (for testing)
//        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.create();

        Type randomList = new TypeToken<List<Category>>() {}.getType();
        Type categoryMemberList = new TypeToken<List<CategoryMember>>() {}.getType();

        // Gson parsers for Wikipedia API
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(randomList, new ListDeserializer<>(randomList, "random"))
                .registerTypeAdapter(Category.class, new ItemDeserializer<>(Category.class, "random"))
                .registerTypeAdapter(categoryMemberList, new ListDeserializer<>(categoryMemberList, "categorymembers"))
                .registerTypeAdapter(Page.class, new KeyDeserializer<>(Page.class, "pages"))
                .create();

        // Configure Retrofit to use our parsers and RXJava adapter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WikipediaService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxAdapter)
                .build();

        return retrofit.create(WikipediaService.class);
    }

    @Override
    public List<Category> getCachedCategories() {
        return mCachedCategories;
    }

    @Override
    public void setCachedCategories(List<Category> categories) {
        this.mCachedCategories = categories;
    }

    @Override
    public void setCachedCategory(int i, Category newCategory) {
        mCachedCategories.set(i, newCategory);
    }

    @Override
    public Observable<List<Category>> getRandomCategories(int limit) {
        return mWikipediaService.getRandomCategories(limit);
    }

    @Override
    public Observable<Category> getRandomCategory() {
        return mWikipediaService.getRandomCategory();
    }

    @Override
    public Observable<List<CategoryMember>> getCategoryMembers(int id, int limit) {
        return mWikipediaService.getCategoryMembers(id, limit);
    }

    @Override
    public Observable<List<CategoryMember>> getCategoryMembers(String title, int limit) {
        return mWikipediaService.getCategoryMembers(title, limit);
    }

    @Override
    public Observable<Page> getPageContent(int id) {
        return mWikipediaService.getPageContent(id);
    }

    @Override
    public Observable<Page> getPageContent(String title) {
        return mWikipediaService.getPageContent(title);
    }

    @Override
    public int getRandomPageId() {
        Category category = getRandomCachedCategory();
        return getRandomCachedCategoryMember(category).getPageid();
    }

    private Category getRandomCachedCategory() {
        int randomIndex = MathUtils.getRandomNumberInRange(0, mCachedCategories.size() - 1);
        return mCachedCategories.get(randomIndex);
    }

    private CategoryMember getRandomCachedCategoryMember(Category category) {
        List<CategoryMember> members = category.getCategoryMembers();
        if (members.size() == 1) return members.get(0);
        int randomCategoryMember = MathUtils.getRandomNumberInRange(0, members.size() - 1);
        return members.get(randomCategoryMember);
    }
}
