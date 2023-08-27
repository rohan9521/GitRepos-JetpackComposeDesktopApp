package view.githubrepo

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.StateFlow
import model.Repository
import view.githubrepo.repoview.RepoView

@Composable
fun DisplayRepositories(repoFlow: StateFlow<List<Repository>>) {

  Row(
      modifier = Modifier
          .fillMaxSize(1.0f)
  ) {
      val repoListMutableState = remember { mutableStateOf(listOf<Repository>()) }
      val lazyList = rememberLazyListState(0)
      LaunchedEffect(true) {
          repoFlow.collect {
              repoListMutableState.value = it
          }
      }
      LazyColumn(
          state = lazyList,
          userScrollEnabled = true,
          modifier = Modifier
              .fillMaxHeight(1.0f)
              .fillMaxWidth(0.98f),
      ) {
          items(repoListMutableState.value.size) { repo ->
              RepoView(repoListMutableState.value[repo])
          }
      }
      VerticalScrollbar(
          adapter = rememberScrollbarAdapter(lazyList)
      )
  }
}