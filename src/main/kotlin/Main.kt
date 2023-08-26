import GraphQL.ApolloRepoClient
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.apollographql.apollo3.ApolloClient
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import repository.FetchService
import repository.MainRepository
import view.githubrepo.DisplayRepositories
import viewmodel.MainViewModel

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
//        Button(onClick = {
//            text = "Hello, Desktop!"
//        }) {
//            Text(text)
//        }
        DisplayRepositories(listOf("asd","asda","qweoq"))
    }
}

fun main() = application {


    val mainModule = module {

        single<ApolloClient> {
            ApolloClient.Builder()
                .serverUrl("https://api.github.com/graphql")
                .build()
        }
        single { MainViewModel(get()) }

        single { ApolloRepoClient(get()) } bind FetchService::class

        single {MainRepository (get())}
    }

    startKoin {
        modules(mainModule)
    }
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
