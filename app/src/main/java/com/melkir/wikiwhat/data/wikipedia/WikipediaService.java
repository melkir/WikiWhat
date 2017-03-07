package com.melkir.wikiwhat.data.wikipedia;

import com.melkir.wikiwhat.data.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Path;

public interface WikipediaService {
    final String WIKIPEDIA_BASE_URL = "https://en.wikipedia.org/w/api.php&action=query";

    // Response format (XML, JSON, TXT...)
    final String FORMAT_PARAM = "format"; // ex: json

    // https://www.mediawiki.org/wiki/API:Lists
    // Submodule param (random, categorymembers...)
    final String LIST_PARAM = "list"; // ex: random

    /**
     * API List Random params
     **/
    // https://www.mediawiki.org/wiki/API:Random
    // Limit how many random pages will be returned.
    final String RNLIMIT_PARAM = "rnlimit"; // ex: 3

    // https://en.wikipedia.org/wiki/Help:Category
    // Return pages in these namespaces only.
    final String RNNAMESPACE_PARAM = "rnnamespace"; // ex: 14

    /**
     * API List Category Members params
     **/
    // Maximum number of categorymembers returned.
    final String CMLIMIT_PARAM = "cmlimit"; // 500

    // The category to enumerate (required unless cmpageid is used).
    // Must include "Category:" prefix. Cannot be used together with cmpageid.
    final String CMTITLE_PARAM = "cmtitle"; // ex: Category:Education

    // Page ID of the category to enumerate. Cannot be used together with cmtitle.
    final String CMPAGEID_PARAM = "cmpageid"; // ex: 16892841

    /**
     * API Text Extract params
     **/
    // https://www.mediawiki.org/wiki/Extension:TextExtracts#API
    // Returns plain-text or limited HTML extracts of the given pages.
    final String PROP_PARAM = "prop"; // ex: extracts

    // https://www.mediawiki.org/wiki/API:Query
    // Extract one or more pages by ids separated by "|"
    final String PAGEIDS_PARAM = "pageids"; // ex: 16892841|16892842

    // Extract one page or more pages by titles separated by "|"
    final String PAGETITLE_PARAM = "titles"; // ex: Hamburger

    Call<List<Category>> getRandomCategories();

    Call<Category> getRandomCategory();

    Call<Category> getCategoryContent(@Path("id") int ...id);

    Call<Category> getCategoryContent(@Path("title") String ...title);


    // pour les catégories
//    "https://en.wikipedia.org/w/api.php?action=query&list=random&rnlimit=3&rnnamespace=14&format=json"
    // pour les membres d'une categorie
//    "https://en.wikipedia.org/w/api.php?action=query&list=categorymembers&format=json&cmlimit=500&cmtitle=Category:" + escapedCategoryTitle
    // pour récupérer le texte d'une page
//    "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&explaintext=&pageids=16892841"

}
