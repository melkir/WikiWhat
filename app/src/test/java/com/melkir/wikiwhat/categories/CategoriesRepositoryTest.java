package com.melkir.wikiwhat.categories;

import com.melkir.wikiwhat.data.CategoriesRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.subscribers.TestSubscriber;

public class CategoriesRepositoryTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    private CategoriesRepository mCategoriesRepo;
    private TestSubscriber<String> testSubscriber;

    @Before
    public void setUp() {
        mCategoriesRepo = new CategoriesRepository();
        testSubscriber = new TestSubscriber<>();
    }

    @Test
    public void shouldFetchPageFromWipedia() {
        // TODO Fix this method
        mCategoriesRepo.getPageContent(370096).subscribe();
        testSubscriber.assertNoErrors();
    }

    @Test
    public void shouldFetchCategoryMembers() {
        mCategoriesRepo.getCategoryMembers(40924578, 3).subscribe();
        testSubscriber.assertNoErrors();
    }

    @Test
    public void shouldFetchRandomCategoriesFromWikipedia() {
        mCategoriesRepo.getRandomCategories(3).subscribe();
        testSubscriber.assertNoErrors();
    }

    @Test
    public void shouldFetchRandomCategoryFromWikipedia() {
        mCategoriesRepo.getRandomCategory().subscribe();
        testSubscriber.assertNoErrors();
    }
}
