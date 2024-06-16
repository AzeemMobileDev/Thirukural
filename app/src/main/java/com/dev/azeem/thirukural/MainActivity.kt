package com.dev.azeem.thirukural

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.azeem.thirukural.data.Kural
import com.dev.azeem.thirukural.data.Thirukural
import com.dev.azeem.thirukural.ui.theme.Green
import com.dev.azeem.thirukural.ui.theme.ThirukuralTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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
        val context = LocalContext.current
        val mList: Kural = getDataFromJson(context)
        Scaffold(
            topBar =
            {
                TopAppBar(
                    colors = topAppBarColors(Green),
                    title = { Text(stringResource(R.string.app_name)) }
                )
            }
        ) { innerPadding ->
            PopulateThirukuralList(
                mList,
                modifier = Modifier
                    .padding(innerPadding)
            )
        }
    }
}

fun readJsonFromAssets(context: Context, fileName: String): String {
    return context.assets.open(fileName).bufferedReader().use { it.readText() }
}

fun getDataFromJson(context: Context): Kural {
    val jsonString = readJsonFromAssets(context, "thirukkural.json")
    val bookList: Kural = parseJsonToModel(jsonString)
    return bookList
}

inline fun <reified T> parseJsonToModel(jsonString: String): T {
    val gson = Gson()
    return gson.fromJson(jsonString, object : TypeToken<T>() {}.type)
}

@Composable
fun PopulateThirukuralList(mList: Kural, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(mList.kural) { item ->
            ThirukuralCard(item)
        }
    }
}

@Composable
fun ThirukuralCard(thirukural: Thirukural) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(PaddingValues(0.dp, 8.dp, 0.dp, 8.dp)),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(modifier = Modifier.padding(3.dp)) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        color = Green
                    )
                    .align(Alignment.Top)
            ) {
                Text(
                    text = thirukural.number.toString(),
                    modifier = Modifier
                        .align(Alignment.Center),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
            Column {
                Text(
                    text = thirukural.lineNo1,
                    modifier = Modifier.padding(
                        3.dp
                    ),
                    fontSize = 13.sp
                )
                Text(
                    text = thirukural.lineNo2,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(3.dp),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Green)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = thirukural.transliteration1,
                    modifier = Modifier.padding(
                        3.dp
                    ),
                    fontSize = 13.sp,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = thirukural.transliteration2,
                    modifier = Modifier.padding(3.dp),
                    fontSize = 13.sp,
                    fontStyle = FontStyle.Italic
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Green)
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "Explanation in English",
                    modifier = Modifier
                        .background(
                            Green,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(6.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = thirukural.explanationInEnglish,
                    modifier = Modifier.padding(3.dp),
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    MainScreen()
}