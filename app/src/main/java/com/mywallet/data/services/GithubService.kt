package com.mywallet.data.services

import com.mywallet.data.models.RepoSearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ankitsharma on 16/01/18.
 */
interface GithubService {

    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String): Observable<RepoSearchResponse>
}