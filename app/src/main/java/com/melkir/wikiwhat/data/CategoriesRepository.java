package com.melkir.wikiwhat.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.melkir.wikiwhat.data.wikipedia.Query;
import com.melkir.wikiwhat.data.wikipedia.Response;
import com.melkir.wikiwhat.data.wikipedia.ResponseDeserializer;
import com.melkir.wikiwhat.data.wikipedia.WikipediaService;

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
//        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory
//                .createWithScheduler(Schedulers.io());
        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.create();

        Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Query.class, new ResponseDeserializer<>(Query.class, "query"))
                        .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WikipediaService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();

        mWikipediaService = retrofit.create(WikipediaService.class);
    }

    public CategoriesRepository(Context context) {
        super();
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

    public Observable<Response> getRandomCategory() {
        return mWikipediaService.getRandomCategory();
    }
}
