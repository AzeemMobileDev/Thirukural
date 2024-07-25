package com.dev.azeem.thirukural.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dev.azeem.thirukural.data.Details
import com.dev.azeem.thirukural.data.FavListViewModel
import com.dev.azeem.thirukural.data.Kural
import com.dev.azeem.thirukural.data.UiState
import com.dev.azeem.thirukural.ui.theme.DarkGreen
import com.dev.azeem.thirukural.ui.theme.Green40
import com.dev.azeem.thirukural.ui.theme.Green80
import com.dev.azeem.thirukural.ui.theme.LightGreen
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun readJsonFromAssets(context: Context, fileName: String): String {
    return context.assets.open(fileName).bufferedReader().use { it.readText() }
}

inline fun <reified T> getDataFromJson(context: Context, jsonFile: String): T {
    val jsonString = readJsonFromAssets(context, jsonFile)
    val bookList = parseJsonToModel<T>(jsonString)
    return bookList
}

inline fun <reified T> parseJsonToModel(jsonString: String): T {
    val gson = Gson()
    return gson.fromJson(jsonString, object : TypeToken<T>() {}.type)
}

@Composable
fun PaalListScreen(
    mList: Details, navController: NavHostController, modifier: Modifier = Modifier
) {
    Column(modifier) {
        LazyColumn(
            modifier = modifier.weight(1f),
            verticalArrangement = Arrangement.Top,
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
        ) {
            items(mList.section.detail) { mListItem ->
                val detailsList =
                    arrayListOf<Details.Section.SectionDetail.ChapterGroup.ChapterGroupDetail>()
                val chapDetailsList =
                    arrayListOf<Details.Section.SectionDetail.ChapterGroup.ChapterGroupDetail.Chapters.ChaptersDetails>()
                detailsList.addAll(mListItem.chapterGroup.chapterGroupDetail)
                for (item in detailsList) {
                    chapDetailsList.addAll(item.chapters.chaptersDetails)
                }
                PaalCard(mListItem, chapDetailsList.size, navController)
            }
        }
    }
}

@Composable
fun IyalListScreen(
    mList: Details,
    paalName: String,
    paalNumber: Int,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val _mList = mList.section.detail.filter { it.number == paalNumber }
    val detailsList = arrayListOf<Details.Section.SectionDetail.ChapterGroup.ChapterGroupDetail>()
    for (i in _mList) {
        detailsList.addAll(i.chapterGroup.chapterGroupDetail)
    }
    Column(modifier) {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier.background(Green80)
        ) {
            Text(
                text = paalName,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Box(contentAlignment = Alignment.Center, modifier = Modifier.background(Green40)) {
            Text(
                text = "இயல்கள்",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 16.dp, bottom = 12.dp, start = 8.dp, end = 8.dp),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(detailsList) { item ->
                IyalCard(item, paalName, paalNumber, navController)
            }
        }
    }
}

@Composable
fun AthikaramListScreen(
    mList: Details,
    iyalName: String,
    iyalNumber: Int,
    paalName: String,
    paalNumber: Int,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val _mList = mList.section.detail.filter { it.number == paalNumber }
    val detailsList = arrayListOf<Details.Section.SectionDetail.ChapterGroup.ChapterGroupDetail>()
    val chapDetailsList =
        arrayListOf<Details.Section.SectionDetail.ChapterGroup.ChapterGroupDetail.Chapters.ChaptersDetails>()
    for (i in _mList) {
        detailsList.addAll(i.chapterGroup.chapterGroupDetail)
    }
    val _detailsList = detailsList.filter { it.number == iyalNumber }
    for (item in _detailsList) {
        chapDetailsList.addAll(item.chapters.chaptersDetails)
    }
    Column(modifier) {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier.background(Green80)
        ) {
            Text(
                text = iyalName,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Box(contentAlignment = Alignment.Center, modifier = Modifier.background(Green40)) {
            Text(
                text = "அதிகாரங்கள்",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 16.dp, bottom = 12.dp, start = 8.dp, end = 8.dp),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), contentPadding = PaddingValues(16.dp)
        ) {
            items(chapDetailsList) { item ->
                val athikaram = "${item.name} / ${item.transliteration}"
                val athikaramNumber = "${item.number}"
                val start = item.start
                val end = item.end
                AthikaramCard(
                    item, paalName, iyalName, athikaram, athikaramNumber, start, end, navController
                )
            }
        }
    }
}

@Composable
fun ThirukuralListScreen(
    mList: Kural,
    paalName: String,
    iyalName: String,
    athikaramName: String,
    athikaramNumber: String,
    start: Int,
    end: Int,
    navController: NavHostController,
    savedFavourites: UiState,
    favListViewModel: FavListViewModel,
    modifier: Modifier = Modifier
) {
    val filteredList = mList.kural.subList(start - 1, end)

    Column(modifier) {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier.background(Green80)
        ) {
            Text(
                text = athikaramName,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Box(contentAlignment = Alignment.Center, modifier = Modifier.background(Green40)) {
            Text(
                text = "திருக்குறள்கள்",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 16.dp, bottom = 12.dp, start = 8.dp, end = 8.dp),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), contentPadding = PaddingValues(16.dp)
        ) {
            items(items = filteredList, key = { item -> item.number }) { item ->
                ThirukuralCard(
                    paalName,
                    iyalName,
                    athikaramName,
                    athikaramNumber,
                    item,
                    true,
                    navController,
                    savedFavourites,
                    favListViewModel
                )
            }
        }
    }
}

@Composable
fun ThirukuralDetailsScreen(
    mList: Kural,
    paalName: String,
    iyalName: String,
    athikaramName: String,
    athikaramNumber: String,
    kuralName: String,
    number: Int,
    modifier: Modifier = Modifier
) {
    val filteredList = mList.kural.filter { it.number == number }

    Column(modifier) {
        Row(
            Modifier
                .background(Green80)
                .wrapContentHeight()
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = kuralName,
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(start = 16.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = athikaramNumber,
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        SpannableText(paalName, iyalName, athikaramName)
        ThirukuralDetailsCard(filteredList.first())
    }
}

@Composable
fun SpannableText(paalName: String, iyalName: String, athikaramName: String) {
    val fontColor: Color = if (isSystemInDarkTheme()) LightGreen else DarkGreen
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = fontColor, fontWeight = FontWeight.Bold)) {
            append("குறள் பால்:")
        }
        append(" $paalName\n")
        withStyle(style = SpanStyle(color = fontColor, fontWeight = FontWeight.Bold)) {
            append("குறள் இயல்:")
        }
        append(" $iyalName\n")
        withStyle(style = SpanStyle(color = fontColor, fontWeight = FontWeight.Bold)) {
            append("குறள் அதிகாரம்:")
        }
        append(" $athikaramName\n")
    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.background(Green40)) {
        Text(
            text = annotatedString,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 16.dp, start = 16.dp, end = 8.dp),
            textAlign = TextAlign.Start,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun FavKuralListScreen(
    navController: NavHostController,
    savedFavourites: UiState,
    favListViewModel: FavListViewModel,
    modifier: Modifier = Modifier
) {

    val mList = savedFavourites.favourites.kural
    Column(modifier) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.background(Green40)) {
            Text(
                text = "பிடித்த திருக்குறள்கள்",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 16.dp, bottom = 12.dp, start = 8.dp, end = 8.dp),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        if (mList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(), contentPadding = PaddingValues(16.dp)
            ) {
                items(items = mList, key = { item -> item.number }) { item ->
                    ThirukuralCard(
                        "",
                        "",
                        "",
                        "",
                        item,
                        false,
                        navController,
                        savedFavourites,
                        favListViewModel
                    )
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "No favorites added yet!",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}