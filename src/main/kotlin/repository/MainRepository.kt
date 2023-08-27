package repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import model.Repository

class MainRepository(
    private val fetchService: FetchService
) {

    private var _repoFlow = MutableStateFlow(listOf<Repository>())
    val repoFlow : StateFlow<List<Repository>> = _repoFlow.asStateFlow()

    suspend fun getAllRepos(searchRepoName:String) {
        val repoList = fetchService.getAllRepos(searchRepoName)
        _repoFlow.value = repoList
    }


}