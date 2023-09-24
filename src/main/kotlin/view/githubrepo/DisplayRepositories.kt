package view.githubrepo

import Utils.networkutils.ResponseState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.StateFlow
import model.Repository
import view.githubrepo.repoview.RepoView

@Composable
fun DisplayRepositories(repoList: List<Repository>?) {

  Row(
      modifier = Modifier
          .fillMaxSize(1.0f)
  ) {
      val repoListMutableState: MutableState<List<Repository>?> = remember { mutableStateOf(repoList) }
      val lazyList = rememberLazyListState(0)

      LazyColumn(
          state = lazyList,
          userScrollEnabled = true,
          modifier = Modifier
              .fillMaxHeight(1.0f)
              .fillMaxWidth(0.98f),
      ) {
          repoListMutableState.value?.let {
              items(it.size) { repo ->
                  repoListMutableState.value?.get(repo)?.let { repoItem -> RepoView(repoItem) }
              }
          }
      }
      VerticalScrollbar(
          adapter = rememberScrollbarAdapter(lazyList)
      )
  }
}