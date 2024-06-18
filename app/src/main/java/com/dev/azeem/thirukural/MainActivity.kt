package com.dev.azeem.thirukural

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.dev.azeem.thirukural.components.AthikaramListScreen
import com.dev.azeem.thirukural.components.PaalListScreen
import com.dev.azeem.thirukural.components.ThirukuralDetailsScreen
import com.dev.azeem.thirukural.components.ThirukuralListScreen
import com.dev.azeem.thirukural.components.getDataFromJson
import com.dev.azeem.thirukural.data.Details
import com.dev.azeem.thirukural.data.Kural
import com.dev.azeem.thirukural.ui.theme.Green80
import com.dev.azeem.thirukural.ui.theme.ThirukuralTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    ThirukuralTheme {
        Scaffold(
            topBar =
            {
                TopAppBar(
                    colors = topAppBarColors(Green80),
                    title = { Text(stringResource(R.string.thirukural)) }
                )
            }
        ) { innerPadding ->
            val navController = rememberNavController()
            KuralNavHost(
                navController = navController,
                modifier = Modifier
                    .padding(innerPadding)
            )
        }
    }
}

@Composable
fun KuralNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    val context = LocalContext.current
    var mDetailList: Details = getDataFromJson(context, "detail.json")
    var thirukuralList: Kural = getDataFromJson(context, "thirukkural.json")

    NavHost(
        navController = navController,
        startDestination = PaalScreen
    ) {
        composable<PaalScreen> {
            PaalListScreen(
                mDetailList,
                navController,
                modifier = modifier
            )
        }
        composable<AthikaramScreen> {
            val args = it.toRoute<AthikaramScreen>()
            AthikaramListScreen(
                mDetailList,
                args.name,
                args.number,
                navController,
                modifier = modifier
            )
        }
        composable<ThirukuralScreen> {
            val args = it.toRoute<ThirukuralScreen>()
            ThirukuralListScreen(
                thirukuralList,
                args.athikaramName,
                args.start,
                args.end,
                navController,
                modifier = modifier
            )
        }
        composable<ThirukuralDetailScreen> {
            val args = it.toRoute<ThirukuralDetailScreen>()
            ThirukuralDetailsScreen(
                thirukuralList,
                args.kuralName,
                args.number,
                modifier = modifier
            )
        }
    }
}

@Serializable
object PaalScreen

@Serializable
data class AthikaramScreen(
    val name: String,
    val number: Int
)

@Serializable
data class ThirukuralScreen(
    val athikaramName: String,
    val start: Int, val end: Int
)

@Serializable
data class ThirukuralDetailScreen(
    val kuralName: String,
    val number: Int
)

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    MainScreen()
}