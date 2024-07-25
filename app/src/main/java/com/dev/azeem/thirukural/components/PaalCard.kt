package com.dev.azeem.thirukural.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dev.azeem.thirukural.IyalScreen
import com.dev.azeem.thirukural.MainScreen
import com.dev.azeem.thirukural.R
import com.dev.azeem.thirukural.data.Details
import com.dev.azeem.thirukural.ui.theme.Green80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaalCard(
    sectionDetail: Details.Section.SectionDetail,
    athikaramSize: Int,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        colors = CardDefaults.cardColors(Green80),
        shape = MaterialTheme.shapes.medium,
        onClick = {
            navController.navigate(
                IyalScreen(
                    "${sectionDetail.name} / ${sectionDetail.transliteration}",
                    sectionDetail.number
                )
            )
        }
    ) {
        Column(
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        ) {
            Text(
                text = "${sectionDetail.name} / ${sectionDetail.transliteration}",
                modifier = Modifier
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = sectionDetail.translation,
                modifier = Modifier
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.athikarangal, athikaramSize),
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PaalPreview() {
    MainScreen()
}