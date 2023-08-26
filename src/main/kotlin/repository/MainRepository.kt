package repository

import GraphQL.ApolloRepoClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import model.Repository

class MainRepository(
    private val fetchService: FetchService
) {

    private var _repoFlow = MutableStateFlow(listOf<Repository>())
    val repoFlow : StateFlow<List<Repository>> = _repoFlow.asStateFlow()

    suspend fun getAllRepos() {
        val repoList = fetchService.getAllRepos()
        _repoFlow.value = repoList
    }


}