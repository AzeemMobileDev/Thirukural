package com.dev.azeem.thirukural.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dev.azeem.thirukural.data.Details
import com.dev.azeem.thirukural.data.Kural
import com.dev.azeem.thirukural.ui.theme.Green80
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
    mList: Details,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(16.dp)
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
            contentAlignment = Alignment.Center,
            modifier = Modifier.background(Green80)
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
        Text(
            text = "இயல்கள்",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 16.dp, start = 8.dp, end = 8.dp),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
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
            contentAlignment = Alignment.Center,
            modifier = Modifier.background(Green80)
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
        Text(
            text = "அதிகாரங்கள்",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 16.dp, start = 8.dp, end = 8.dp),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(chapDetailsList) { item ->
                val athikaram = "${item.name} / ${item.transliteration}"
                val athikaramNumber = "${item.number}"
                val start = item.start
                val end = item.end
                AthikaramCard(
                    item,
                    paalName,
                    iyalName,
                    athikaram,
                    athikaramNumber,
                    start,
                    end,
                    navController
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
    modifier: Modifier = Modifier
) {
    val filteredList = mList.kural.subList(start - 1, end)

    Column(modifier) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.background(Green80)
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
        Text(
            text = "திருக்குறள்கள்",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 16.dp, start = 8.dp, end = 8.dp),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(filteredList) { item ->
                ThirukuralCard(
                    paalName,
                    iyalName,
                    athikaramName,
                    athikaramNumber,
                    item,
                    navController
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
        Text(
            text = "குறள் பால்: $paalName\nகுறள் இயல்: $iyalName\nகுறள் அதிகாரம்: $athikaramName",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 16.dp, start = 16.dp, end = 8.dp),
            textAlign = TextAlign.Start,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        ThirukuralDetailsCard(filteredList.first())
    }
}