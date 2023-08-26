package view.githubrepo

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun DisplayRepositories(repoList:List<String>){
    val repoListMutableState = remember{ mutableStateOf(repoList) }
    LazyColumn(
    ) {
        items(repoListMutableState.value.size){repo->
           Row{
                Text(repoListMutableState.value[repo])
           }
        }
    }

}