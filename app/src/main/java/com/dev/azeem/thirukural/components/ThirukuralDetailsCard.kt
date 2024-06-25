package com.dev.azeem.thirukural.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.azeem.thirukural.data.Thirukural
import com.dev.azeem.thirukural.ui.theme.Green80
import com.dev.azeem.thirukural.ui.theme.Green40

@Composable
fun ThirukuralDetailsCard(thirukural: Thirukural) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(PaddingValues(8.dp)),
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(1.dp, Green80),
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            Column(
                Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 2.dp)
            ) {
                Text(
                    text = thirukural.lineNo1,
                    modifier = Modifier.padding(
                        3.dp
                    ),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = thirukural.lineNo2,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
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
                Divider(color = Green80)
                Column(
                    Modifier
                        .padding(top = 4.dp, bottom = 4.dp, start = 6.dp, end = 6.dp)
                        .verticalScroll(
                            rememberScrollState()
                        )
                ) {
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Explanation in English",
                        modifier = Modifier
                            .background(
                                Green80,
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
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "மு.வரதராசன் விளக்கம்",
                        modifier = Modifier
                            .background(
                                Green80,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(6.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = thirukural.explanationByMV,
                        modifier = Modifier.padding(3.dp),
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "சாலமன் பாப்பையா விளக்கம்",
                        modifier = Modifier
                            .background(
                                Green80,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(6.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = thirukural.explanationBySP,
                        modifier = Modifier.padding(3.dp),
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "கலைஞர் மு.கருணாநிதி விளக்கம்",
                        modifier = Modifier
                            .background(
                                Green80,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(6.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = thirukural.explanationByMK,
                        modifier = Modifier.padding(3.dp),
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ThirukuralDetailsCardPreview() {
    Column(
        Modifier
            .fillMaxHeight()
            .padding(top = 20.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(PaddingValues(8.dp)),
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(1.dp, Green80),
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            Column(
                Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 2.dp)
            ) {
                Text(
                    text = "Sample",
                    modifier = Modifier.padding(
                        3.dp
                    ),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "thirukural.lineNo2",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
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
                Divider(color = Green80)
                Column(
                    Modifier
                        .padding(top = 4.dp, bottom = 4.dp, start = 6.dp, end = 6.dp)
                        .verticalScroll(
                            rememberScrollState()
                        )
                ) {
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Explanation in English",
                        modifier = Modifier
                            .background(
                                Green80,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(6.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "thirukural.explanationInEnglish",
                        modifier = Modifier.padding(3.dp),
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "மு.வரதராசன் விளக்கம்",
                        modifier = Modifier
                            .background(
                                Green80,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(6.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "thirukural.explanationByMV",
                        modifier = Modifier.padding(3.dp),
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "சாலமன் பாப்பையா விளக்கம்",
                        modifier = Modifier
                            .background(
                                Green80,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(6.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "thirukural.explanationBySP",
                        modifier = Modifier.padding(3.dp),
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "கலைஞர் மு.கருணாநிதி விளக்கம்",
                        modifier = Modifier
                            .background(
                                Green80,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(6.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "thirukural.explanationByMK",
                        modifier = Modifier.padding(3.dp),
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}