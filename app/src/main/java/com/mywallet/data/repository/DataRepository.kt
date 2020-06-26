package com.mywallet.data.repository

import com.mywallet.data.models.RepoSearchResponse
import com.mywallet.data.services.GithubService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ankitsharma on 11/01/18.
 */
class DataRepository @Inject constructor(private val githubService: GithubService) {

    fun getMessage(): String {
        return "hello my message"
    }

    fun searchRepositories(query: String): Observable<RepoSearchResponse> = githubService.searchRepos(query)
}