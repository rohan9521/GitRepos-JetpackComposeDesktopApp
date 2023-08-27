package view.githubrepo

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.StateFlow
import model.Repository
import view.githubrepo.repoview.RepoView

@Composable
fun DisplayRepositories(repoFlow:StateFlow<List<Repository>>){

    val repoListMutableState = remember{ mutableStateOf(listOf<Repository>()) }

    LaunchedEffect(true){
        repoFlow.collect{
            repoListMutableState.value = it
        }
    }

    LazyColumn(
    ) {
        items(repoListMutableState.value.size){repo->
          RepoView(repoListMutableState.value[repo])
        }
    }

}