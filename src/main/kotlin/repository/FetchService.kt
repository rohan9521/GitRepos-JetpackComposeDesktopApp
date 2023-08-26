package repository

import model.Repository

interface FetchService {
    suspend fun getAllRepos():List<Repository>
}