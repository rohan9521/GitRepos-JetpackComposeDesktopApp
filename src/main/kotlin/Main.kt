import GraphQL.ApolloRepoClient
import Utils.TITLE
import Utils.TOKEN
import Utils.networkutils.ResponseState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.label
import com.apollographql.apollo3.network.okHttpClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import model.*
import okhttp3.OkHttpClient
import okhttp3.Response
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
import view.githubrepo.splashscreen.SplashScreen
import viewmodel.MainViewModel


class MyApp : KoinComponent {
    private val mainViewModel: MainViewModel by inject()

    // Composable function using Jetpack Compose
    @Composable
    @Preview
    fun App(mainViewModel: MainViewModel = this.mainViewModel) {
        var outputState = remember { mutableStateOf<ResponseState<List<Repository>>>(ResponseState.Default()) }
        var userInput by remember { mutableStateOf("") }
        var showSplashScreen by remember { mutableStateOf(true) }

        LaunchedEffect(mainViewModel.repoFlow){
            mainViewModel.repoFlow.collect{
                outputState.value = it
            }
        }
        LaunchedEffect(true){
            delay(10000)
            showSplashScreen = false
        }


        MaterialTheme {
            if (showSplashScreen) {
                SplashScreen()
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .padding(10.dp)
                        .background(Color.White)
                        .fillMaxSize(),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = userInput,

                            shape = RoundedCornerShape(16.dp),
                            onValueChange = {
                                userInput = it
                            },
                            label = {
                                Text("Repository Name")
                            }
                        )
                        Spacer(
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .height(25.dp)
                        )
                        Button(
                            onClick = {
                                mainViewModel.getAllRepos(userInput)
                            }
                        ) {
                            Text("Fetch")
                        }

                    }

                    outputState.value.let {
                        when (it) {
                            is ResponseState.Success -> DisplayRepositories(it.successData)
                            is ResponseState.Error -> {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    Text(
                                        text = "${it.errorData?.localizedMessage}",
                                        color = Color.Red
                                    )
                                }
                            }

                            is ResponseState.Loading -> {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    CircularProgressIndicator()
                                }
                            }

                            else -> {

                            }
                        }
                    }

                }
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
    Window(
        title = TITLE,
        onCloseRequest = ::exitApplication
    ) {
        MyApp().App()
    }
}
