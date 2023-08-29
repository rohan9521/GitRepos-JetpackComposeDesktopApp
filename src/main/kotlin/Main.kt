import GraphQL.ApolloRepoClient
import Utils.TOKEN
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.label
import com.apollographql.apollo3.network.okHttpClient
import model.*
import okhttp3.OkHttpClient
import org.koin.core.Koin
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext.get
import org.koin.core.context.startKoin
import org.koin.core.inject
import org.koin.dsl.bind
import org.koin.dsl.module
import repository.FetchService
import repository.MainRepository
import view.githubrepo.DisplayRepositories
import viewmodel.MainViewModel


class MyApp : KoinComponent {
    private val mainViewModel: MainViewModel by inject()

    // Composable function using Jetpack Compose
    @Composable
    @Preview
    fun App(mainViewModel: MainViewModel = this.mainViewModel) {

        var userInput by remember { mutableStateOf("") }
        MaterialTheme {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .padding(10.dp),
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    OutlinedTextField(
                        value = userInput,

                        shape = RoundedCornerShape(16.dp),
                        onValueChange = {
                            userInput = it
                        },
                        label={
                            Text("Repository Name")
                        }
                    )
                    Spacer(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .height(25.dp )
                    )
                    Button(
                        onClick = {
                            mainViewModel.getAllRepos(userInput)
                        }
                    ) {
                        Text("Fetch")
                    }

                }
                DisplayRepositories(mainViewModel.repoFlow)

            }
        }
    }
}

fun main() = application {


    val mainModule = module {

        single<ApolloClient> {
            ApolloClient.Builder()
                .serverUrl("https://api.github.com/graphql")
                .okHttpClient(
                    OkHttpClient.Builder()
                        .addInterceptor { chain ->
                            val original = chain.request()
                            val request = original.newBuilder()
                                .header(
                                    "Authorization",
                                    "Bearer $TOKEN"
                                )
                                .method(original.method, original.body)
                                .build()
                            chain.proceed(request)
                        }
                        .build())
                .build()
        }
        single { MainViewModel(get()) }

        single { ApolloRepoClient(get()) } bind FetchService::class

        single { MainRepository(get()) }
    }

    startKoin {
        modules(mainModule)
    }
    Window(onCloseRequest = ::exitApplication) {
        MyApp().App()
    }
}
