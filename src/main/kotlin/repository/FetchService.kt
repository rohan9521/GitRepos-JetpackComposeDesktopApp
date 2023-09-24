package repository

import Utils.networkutils.ResponseState
import model.Repository

interface FetchService {
    suspend fun getAllRepos(searchRepoName:String):ResponseState<List<Repository>>
}