package com.melkir.wikiwhat.data;

import android.support.annotation.Nullable;

public final class Category {
    private final int mId;

    @Nullable
    private final String mTitle;

    private final int mCount;

    public Category(int id, @Nullable String title, int count) {
        mId = id;
        mTitle = title;
        mCount = count;
    }

    public int getId() {
        return mId;
    }

    @Nullable
    public String getTitle() {
        return mTitle;
    }

    public int getCount() {
        return mCount;
    }
}
