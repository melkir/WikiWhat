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
    public void shouldFetchPageFromWipedia() {
        // TODO Fix this method
        mCategoriesRepo.getPageContent(370096).subscribe(System.out::println, System.out::println);
    }

    @Test
    public void shouldFetchCategoryMembers() {
        mCategoriesRepo.getCategoryMembers(40924578, 3).subscribe(System.out::println, System.out::println);
    }

    @Test
    public void shouldFetchRandomCategoriesFromWikipedia() {
        mCategoriesRepo.getRandomCategories(3).subscribe(System.out::println, System.out::println);
    }

    @Test
    public void shouldFetchRandomCategoryFromWikipedia() {
        mCategoriesRepo.getRandomCategory().subscribe(System.out::println, System.out::println);
    }
}
