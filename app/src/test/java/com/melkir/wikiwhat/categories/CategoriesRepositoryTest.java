package com.melkir.wikiwhat.categories;

import com.melkir.wikiwhat.data.CategoriesRepository;
import com.melkir.wikiwhat.data.wikipedia.Random;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

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
            List<Random> randoms = res.getQuery().getRandom();
            System.out.println(randoms);
        }, err -> {
            System.out.println(err);
        });
    }
}
