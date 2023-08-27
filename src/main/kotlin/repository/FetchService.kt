package repository

import model.Repository

interface FetchService {
    suspend fun getAllRepos(searchRepoName:String):List<Repository>
}