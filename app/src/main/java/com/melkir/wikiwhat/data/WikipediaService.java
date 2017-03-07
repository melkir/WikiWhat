package com.melkir.wikiwhat.data;

public interface WikipediaService {
    String SERVICE_ENDPOINT = "https://en.wikipedia.org/w/api.php?action=query&format=json";

    // pour les catégories
//    "https://en.wikipedia.org/w/api.php?action=query&list=random&rnlimit=3&rnnamespace=14&format=json"
    // pour les pages d'une categorie
//    "https://en.wikipedia.org/w/api.php?action=query&list=categorymembers&format=json&cmlimit=500&cmtitle=Category:" + escapedCategoryTitle
    // pour récupérer le texte d'une page
//    "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&explaintext=&pageids=16892841"

}
