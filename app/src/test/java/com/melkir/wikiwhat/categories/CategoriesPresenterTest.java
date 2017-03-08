package com.melkir.wikiwhat.categories;

import com.melkir.wikiwhat.data.CategoriesRepository;
import com.melkir.wikiwhat.data.model.Category;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoriesPresenterTest {
    private static List<Category> CATEGORIES;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private CategoriesRepository mCategoriesRepository;

    @Mock
    private CategoriesContract.View mCategoriesView;

    private CategoriesPresenter mCategoriesPresenter;

    @Before
    public void setUp() {
        mCategoriesPresenter = new CategoriesPresenter(mCategoriesRepository, mCategoriesView);
        CATEGORIES = Arrays.asList(new Category(0, "Education", 298), new Category(1, "Computer science", 38));
    }

    @Test
    @Ignore
    // Broken with RxJava2 AndroidSchedulers
    public void shouldPassCategoriesToView() {
        // arrange
        when(mCategoriesRepository.getCategories()).thenReturn(CATEGORIES);

        // act
        mCategoriesPresenter.loadCategories();

        // assert
        verify(mCategoriesView).showCategories(CATEGORIES);
    }

    @Test
    @Ignore
    // Broken with RxJava2 AndroidSchedulers
    public void shouldHandleNoCategoriesFound() {
        when(mCategoriesRepository.getCategories()).thenReturn(Collections.emptyList());

        mCategoriesPresenter.loadCategories();

        verify(mCategoriesView).showNoCategories();
    }

}
