package com.melkir.wikiwhat.data.wikipedia;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Query {

    @SerializedName("random")
    @Expose
    private List<Random> random = null;

    public List<Random> getRandom() {
        return random;
    }

    public void setRandom(List<Random> random) {
        this.random = random;
    }

}