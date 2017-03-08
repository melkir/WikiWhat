package com.melkir.wikiwhat.data.wikipedia;

import com.melkir.wikiwhat.data.wikipedia.model.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Parameters for Wikipedia
 *
 * format: Response format (XML, JSON, TXT...)
 * example -> format={json}
 *
 * ------------------- Sub module -------------------------------------------
 * list: Sub module param (random, categorymembers...)
 * @see <a href="https://www.mediawiki.org/wiki/API:Lists" />
 * example -> list={random}
 *
 * ------------------- Sub module - Random ----------------------------------
 * @see <a href="https://www.mediawiki.org/wiki/API:Random" />
 * rnlimit: Limit how many random pages will be returned
 * rnnamespace: Return pages in these namespaces only
 * example -> list=random&rnlimit={3}&rnnamespace={14}
 *
 * ------------------- Sub module - Category Members ------------------------
 * @see <a href="https://en.wikipedia.org/wiki/Help:Category" />
 * cmlimit: Maximum number of category members returned
 * cmpageid: Page ID of the category to enumerate. Cannot be used together with cmtitle
 * cmtitle: The category to enumerate (required unless cmpageid is used)
 * example -> list=categorymembers&cmlimit={500}&cmtitle=Category:{title}
 * or      -> list=categorymembers&cmlimit={500}&cmpageid={id}
 *
 * ------------------- Extract operation -------------------------------------
 * @see <a href="https://www.mediawiki.org/wiki/Extension:TextExtracts#API" />
 * @see <a href="https://www.mediawiki.org/wiki/API:Query" />
 * prop: Returns plain-text or limited HTML extracts of the given pages
 * pagesids: Extract one or more pages by ids separated by "|"
 * titles: Extract one page or more pages by titles separated by "|"
 * example -> prop={extracts&explaintext}&pageids={id1|id2}
 * or      -> prop={extracts&explaintext}&titles={title1|title2}
 */
public interface WikipediaService {
    final String BASE_URL = "https://en.wikipedia.org/w/";
    final String BASE_API = "api.php?action=query&format=json";

    @GET(BASE_API + "&list=random&rnnamespace=14")
    Observable<Response> getRandomCategories(@Query("rnlimit") int limit);

    @GET(BASE_API + "&list=random&rnnamespace=14&rnlimit=1")
    Observable<Response> getRandomCategory();

    @GET(BASE_API + "&list=categorymembers")
    Observable<Response> getCategoryMembers(
            @Query("cmpageid") int id,
            @Query("cmlimit") int limit
    );

    @GET(BASE_API + "&list=categorymembers")
    Observable<Response> getCategoryMembers(
            @Query("cmtitle=Category:") String title,
            @Query("cmlimit") int limit
    );

    @GET(BASE_API + "&prop=extracts")
    Observable<Response> getPagesContent(@Query("pagesids") int... ids);

    @GET(BASE_API + "&prop=extracts")
    Observable<Response> getPagesContent(@Query("titles") String... titles);
}
