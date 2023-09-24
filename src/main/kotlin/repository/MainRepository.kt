package repository

import Utils.networkutils.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import model.Repository

class MainRepository(
    private val fetchService: FetchService
) {
    private var _repoFlow = MutableStateFlow<ResponseState<List<Repository>>>(ResponseState.Default())
    val repoFlow : StateFlow<ResponseState<List<Repository>>> = _repoFlow.asStateFlow()

    suspend fun getAllRepos(searchRepoName:String) {
        _repoFlow.value = ResponseState.Loading()
        val repoList = fetchService.getAllRepos(searchRepoName)
        _repoFlow.value = repoList
    }
}