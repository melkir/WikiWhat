package com.melkir.wikiwhat.categories;

import com.melkir.wikiwhat.data.CategoriesRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class CategoriesRepositoryTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    private CategoriesRepository mCategoriesRepo;

    @Before
    public void setUp() {
        mCategoriesRepo = new CategoriesRepository();
    }

    @Test
    public void shouldFetchRandomCategoriesFromWikipedia() {
    }

    @Test
    public void shouldFetchRandomCategoryFromWikipedia() {
        System.out.println("Test start");
        mCategoriesRepo.getRandomCategory().subscribe(res -> {
            System.out.println(res);
        }, err -> {
            System.out.println(err);
        });
    }
}
