package com.dev.azeem.thirukural.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dev.azeem.thirukural.ThirukuralDetailScreen
import com.dev.azeem.thirukural.data.Thirukural
import com.dev.azeem.thirukural.ui.theme.Green80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirukuralCard(
    paalName: String,
    iyalName: String,
    athikaramName: String,
    athikaramNumber: String,
    thirukural: Thirukural,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(PaddingValues(0.dp, 8.dp, 0.dp, 8.dp)),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, Green80),
        onClick = {
            navController.navigate(
                ThirukuralDetailScreen(
                    paalName,
                    iyalName,
                    athikaramName,
                    "அதிகாரம் - $athikaramNumber",
                    "குறள் - ${thirukural.number}",
                    thirukural.number
                )
            )
        }
    ) {
        Column(
            Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.background)
        ) {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(RoundedCornerShape(2.dp))
                    .background(
                        color = Green80
                    )
                    .align(Alignment.Start)
                    .padding(6.dp)
            ) {
                Text(
                    text = "குறள் - ${thirukural.number}",
                    modifier = Modifier
                        .align(Alignment.Center),
                )
            }
            Column(Modifier.padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 2.dp)) {
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
                    modifier = Modifier.padding(3.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Green80)
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
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(PaddingValues(0.dp, 8.dp, 0.dp, 8.dp)),
        elevation = CardDefaults.cardElevation(5.dp),
        border = BorderStroke(1.dp, Green80),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.background)
        ) {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(RoundedCornerShape(2.dp))
                    .background(
                        color = Green80
                    )
                    .align(Alignment.Start)
                    .padding(6.dp)
            ) {
                Text(
                    text = "குறள் - 133",
                    modifier = Modifier
                        .align(Alignment.Center),
                )
            }

            Column(Modifier.padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 2.dp)) {
                Text(
                    text = "Sample",
                    modifier = Modifier.padding(
                        3.dp
                    ),
                    fontSize = 13.sp
                )
                Text(
                    text = "thirukural.lineNo2",
                    fontSize = 13.sp,
                    modifier = Modifier.padding(3.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Green80)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "thirukural.transliteration1",
                    modifier = Modifier.padding(
                        3.dp
                    ),
                    fontSize = 13.sp,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "thirukural.transliteration2",
                    modifier = Modifier.padding(3.dp),
                    fontSize = 13.sp,
                    fontStyle = FontStyle.Italic
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}