package viewmodel

import Utils.networkutils.ResponseState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import model.Repository
import repository.MainRepository

class MainViewModel (
    private val repository: MainRepository
) {
    private val job = Job()
    private val scope = CoroutineScope(job+Dispatchers.IO)

    val repoFlow : StateFlow<ResponseState<List<Repository>>> = repository.repoFlow

    fun getAllRepos(searchRepoName:String){
        scope.launch {
            repository.getAllRepos(searchRepoName)
        }
    }
    fun onViewModelClear(){
        job.cancel()
    }

}