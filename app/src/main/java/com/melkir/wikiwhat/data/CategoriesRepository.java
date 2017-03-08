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
import com.melkir.wikiwhat.data.wikipedia.util.ListDeserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoriesRepository implements CategoriesDataSource {
    private List<Category> mCategories;
    private WikipediaService mWikipediaService;

    public CategoriesRepository() {
        mWikipediaService = newWikipediaServiceInstance();
    }

    public CategoriesRepository(Context context) {
        mWikipediaService = newWikipediaServiceInstance();
    }

    private WikipediaService newWikipediaServiceInstance() {
        // Asynchronous retrofit (for production)
//         RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
        // Synchronous retrofit (for testing)
        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.create();

        Type randomList = new TypeToken<List<Category>>() {}.getType();
        Type categoryMemberList = new TypeToken<List<CategoryMember>>() {}.getType();

        // Gson parsers for Wikipedia API
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(randomList, new ListDeserializer<>(randomList, "random"))
                .registerTypeAdapter(Category.class, new ItemDeserializer<>(Category.class, "random"))
                .registerTypeAdapter(categoryMemberList, new ListDeserializer<>(categoryMemberList, "categorymembers"))
                .registerTypeAdapter(Page.class, new ItemDeserializer<>(Page.class, "pages"))
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
    // TODO Change this method to be asynchronous and return data
    public List<Category> getCategories() {
        mCategories = new ArrayList<>();
//        return Collections.emptyList();
        mCategories.addAll(Arrays.asList(
                new Category(0, "Education", 298),
                new Category(1, "Computer science", 38),
                new Category(2, "French novelists", 40)
        ));

        return mCategories;
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
}
