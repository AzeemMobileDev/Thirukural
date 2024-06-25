package com.dev.azeem.thirukural.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dev.azeem.thirukural.AthikaramScreen
import com.dev.azeem.thirukural.data.Details
import com.dev.azeem.thirukural.ui.theme.Green80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IyalCard(
    sectionDetail: Details.Section.SectionDetail.ChapterGroup.ChapterGroupDetail,
    paalName: String,
    paalNumber: Int,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, Green80),
        onClick = {
            navController.navigate(
                AthikaramScreen(
                    "${sectionDetail.name} / ${sectionDetail.transliteration}",
                    sectionDetail.number,
                    paalName,
                    paalNumber
                )
            )
        }
    ) {
        Row(
            Modifier
                .height(IntrinsicSize.Min)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(modifier = Modifier.background(color = Green80)) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(48.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .padding(6.dp)
                ) {
                    Text(
                        text = "${sectionDetail.number}",
                        modifier = Modifier
                            .align(Alignment.Center),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Column(
                Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = sectionDetail.name,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        3.dp
                    )
                )
                Text(
                    text = sectionDetail.transliteration,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(3.dp)
                )
            }
            Column(modifier = Modifier.background(color = Green80)) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(2.dp))
                        .padding(6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowRight,
                        contentDescription = "Next"
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun IyalPreview() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, Green80),
        onClick = { }
    ) {
        Row(
            Modifier
                .height(IntrinsicSize.Min)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(modifier = Modifier.background(color = Green80)) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(48.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .padding(6.dp)
                ) {
                    Text(
                        text = "123",
                        modifier = Modifier
                            .align(Alignment.Center),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Column(
                Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Sample",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        3.dp
                    )
                )
                Text(
                    text = "Sample",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(3.dp)
                )
            }
            Column(modifier = Modifier.background(color = Green80)) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(2.dp))
                        .padding(6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowRight,
                        contentDescription = "Next"
                    )
                }
            }
        }
    }
}