@file:OptIn(ExperimentalMaterial3Api::class)

package com.dev.azeem.thirukural

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.window.core.layout.WindowWidthSizeClass
import com.dev.azeem.thirukural.components.AthikaramListScreen
import com.dev.azeem.thirukural.components.FavKuralListScreen
import com.dev.azeem.thirukural.components.IyalListScreen
import com.dev.azeem.thirukural.components.PaalListScreen
import com.dev.azeem.thirukural.components.ThirukuralDetailsScreen
import com.dev.azeem.thirukural.components.ThirukuralListScreen
import com.dev.azeem.thirukural.components.getDataFromJson
import com.dev.azeem.thirukural.data.Details
import com.dev.azeem.thirukural.data.FavListViewModel
import com.dev.azeem.thirukural.data.Kural
import com.dev.azeem.thirukural.data.Thirukural
import com.dev.azeem.thirukural.data.UiState
import com.dev.azeem.thirukural.ui.theme.DarkBrown
import com.dev.azeem.thirukural.ui.theme.Green40
import com.dev.azeem.thirukural.ui.theme.Green80
import com.dev.azeem.thirukural.ui.theme.LightBrown
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

@Composable
fun MainScreen(favListViewModel: FavListViewModel = viewModel(factory = FavListViewModel.Factory)) {
    ThirukuralTheme {

        val savedFavourites by favListViewModel.uiState.collectAsState()

        var currentDestination by remember {
            mutableStateOf(AppDestinations.HOME)
        }

        val adaptiveInfo = currentWindowAdaptiveInfo()
        val layoutType = with(adaptiveInfo) {
            when (windowSizeClass.windowWidthSizeClass) {
                WindowWidthSizeClass.EXPANDED -> {
                    NavigationSuiteType.NavigationDrawer
                }
                else -> {
                    NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
                }
            }
        }

        val myNavigationSuiteItemColors = NavigationSuiteDefaults.itemColors(
            navigationBarItemColors = NavigationBarItemDefaults.colors(
                indicatorColor = if (isSystemInDarkTheme()) Green40 else Green80
            ),
            navigationRailItemColors = NavigationRailItemDefaults.colors(
                indicatorColor = if (isSystemInDarkTheme()) Green40 else Green80
            ),
            navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = if (isSystemInDarkTheme()) Green40 else Green80
            )
        )

        NavigationSuiteScaffold(
            layoutType = layoutType,
            navigationSuiteItems = {

                AppDestinations.entries.forEach {

                    item(
                        selected = currentDestination == it,
                        onClick = {
                            currentDestination = it
                        },
                        icon = {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = it.contentDescription.toString()
                            )
                        },
                        colors = myNavigationSuiteItemColors,
                        label = {
                            Text(text = stringResource(id = it.label))
                        },
                    )
                }
            },
            navigationSuiteColors = NavigationSuiteDefaults.colors(
                navigationBarContainerColor = if (isSystemInDarkTheme()) Green80 else Green40,
                navigationDrawerContainerColor = if (isSystemInDarkTheme()) Green80 else Green40,
                navigationRailContainerColor = if (isSystemInDarkTheme()) Green80 else Green40
            ),
        ) {
            when (currentDestination) {
                AppDestinations.HOME -> HomeDestination(savedFavourites, favListViewModel)
                AppDestinations.FAVORITES -> FavoritesDestination(savedFavourites, favListViewModel)
                AppDestinations.ABOUT -> AboutDestination()
            }
        }
    }
}

@Composable
fun HomeDestination(savedFavourites: UiState, favListViewModel: FavListViewModel) {
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
            savedFavourites = savedFavourites,
            favListViewModel = favListViewModel,
            modifier = Modifier
                .padding(innerPadding)
        )
    }
}

@Composable
fun FavoritesDestination(savedFavourites: UiState, favListViewModel: FavListViewModel) {
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
        FavKuralNavHost(
            navController = navController,
            savedFavourites = savedFavourites,
            favListViewModel = favListViewModel,
            modifier = Modifier
                .padding(innerPadding)
        )
    }
}

@Composable
fun AboutDestination() {
    Scaffold { innerPadding ->
        AboutScreen(
            modifier = Modifier
                .padding(innerPadding)
        )
    }
}

@Composable
fun KuralNavHost(
    navController: NavHostController,
    savedFavourites: UiState,
    favListViewModel: FavListViewModel,
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
        composable<IyalScreen> {
            val args = it.toRoute<IyalScreen>()
            IyalListScreen(
                mDetailList,
                args.name,
                args.number,
                navController,
                modifier = modifier
            )
        }
        composable<AthikaramScreen> {
            val args = it.toRoute<AthikaramScreen>()
            AthikaramListScreen(
                mDetailList,
                args.iyalName,
                args.iyalNumber,
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
                args.paalName,
                args.iyalName,
                args.athikaramName,
                args.athikaramNumber,
                args.start,
                args.end,
                navController,
                savedFavourites,
                favListViewModel,
                modifier = modifier
            )
        }
        composable<ThirukuralDetailScreen> {
            val args = it.toRoute<ThirukuralDetailScreen>()
            ThirukuralDetailsScreen(
                thirukuralList,
                args.paalName,
                args.iyalName,
                args.athikaramName,
                args.athikaramNumber,
                args.kuralName,
                args.number,
                modifier = modifier
            )
        }
    }
}

@Composable
fun FavKuralNavHost(
    navController: NavHostController,
    savedFavourites: UiState,
    favListViewModel: FavListViewModel,
    modifier: Modifier
) {

    NavHost(
        navController = navController,
        startDestination = FavoritesScreen
    ) {
        composable<FavoritesScreen> {
            FavKuralListScreen(
                navController,
                savedFavourites,
                favListViewModel,
                modifier = modifier
            )
        }
    }
}

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    val gradientColors = if (isSystemInDarkTheme())
        listOf(Green40, LightBrown, Green80)
    else listOf(Green80, DarkBrown, Green40)

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = "Thirukural is a classic Tamil language text consisting of 1,330 short couplets, or kurals, of seven words each. This application contains the kurals and its explanations.",
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Justify
        )
        Text(
            text = "Version 1.0",
            modifier = Modifier.align(Alignment.BottomCenter),
            textAlign = TextAlign.Center,
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = gradientColors
                )
            )
        )
    }
}

@Serializable
object PaalScreen

@Serializable
object FavoritesScreen

@Serializable
data class IyalScreen(
    val name: String,
    val number: Int
)

@Serializable
data class AthikaramScreen(
    val iyalName: String,
    val iyalNumber: Int,
    val name: String,
    val number: Int
)

@Serializable
data class ThirukuralScreen(
    val paalName: String,
    val iyalName: String,
    val athikaramName: String,
    val athikaramNumber: String,
    val start: Int,
    val end: Int
)

@Serializable
data class ThirukuralDetailScreen(
    val paalName: String,
    val iyalName: String,
    val athikaramName: String,
    val athikaramNumber: String,
    val kuralName: String,
    val number: Int
)

@Serializable
data class FavThirukuralDetailScreen(
    val paalName: String,
    val iyalName: String,
    val athikaramName: String,
    val athikaramNumber: String,
    val kuralName: String,
    val number: Int
)

enum class AppDestinations(
    @StringRes val label: Int,
    val icon: ImageVector,
    @StringRes val contentDescription: Int
) {
    HOME(R.string.home, Icons.Default.Home, R.string.home),
    FAVORITES(R.string.favorites, Icons.Default.Favorite, R.string.favorites),
    ABOUT(R.string.about, Icons.Default.Info, R.string.about),
}

@Preview(showSystemUi = true, device = Devices.PIXEL_7_PRO)
@Preview(showSystemUi = true, device = Devices.PIXEL)
@Preview(showSystemUi = true, device = Devices.PIXEL_5)
@Preview(
    showSystemUi = true,
    device = "spec:id=reference_tablet,shape=Normal,width=800,height=1280,unit=dp,dpi=240"
)
@Composable
fun GreetingPreview() {
    MainScreen()
}