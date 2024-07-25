package com.dev.azeem.thirukural.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dev.azeem.thirukural.ThirukuralDetailScreen
import com.dev.azeem.thirukural.data.FavListViewModel
import com.dev.azeem.thirukural.data.Kural
import com.dev.azeem.thirukural.data.Thirukural
import com.dev.azeem.thirukural.data.UiState
import com.dev.azeem.thirukural.ui.theme.Green80

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ThirukuralCard(
    paalName: String,
    iyalName: String,
    athikaramName: String,
    athikaramNumber: String,
    thirukural: Thirukural,
    showAsFavKural: Boolean,
    navController: NavHostController,
    savedFavourites: UiState,
    favListViewModel: FavListViewModel
) {
    var checkedState by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(PaddingValues(0.dp, 8.dp, 0.dp, 12.dp)),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, Green80)
    ) {
        Column(
            Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.background)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .clip(RoundedCornerShape(2.dp))
                        .background(
                            color = Green80
                        )
                        .padding(6.dp)
                ) {
                    Text(
                        text = "குறள் - ${thirukural.number}",
                        modifier = Modifier
                            .align(Alignment.Center),
                    )
                }

                if (showAsFavKural) {

                    val favList = savedFavourites.favourites
                    val result = favList.kural.filter { it.number == thirukural.number }
                    checkedState = result.isNotEmpty()

                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .clip(RoundedCornerShape(2.dp))
                    ) {
                        IconToggleButton(
                            // on below line we are
                            // specifying default check state
                            checked = checkedState,
                            // on below line we are adding on check change
                            onCheckedChange = {
                                checkedState = !checkedState
                                val newList = favList.kural as ArrayList<Thirukural>
                                if (checkedState) {
                                    newList.add(thirukural)
                                } else {
                                    newList.remove(thirukural)
                                }
                                favListViewModel.saveFavourites(Kural(newList))
                            }
                        ) {
                            // on below line we are creating a
                            // variable for our transition
                            val transition = updateTransition(checkedState, label = "")

                            // on below line we are creating a variable for
                            // color of our icon
                            val tint by transition.animateColor(label = "iconColor") { isChecked ->
                                // if toggle button is checked we are setting color as red.
                                // in else condition we are setting color as black
                                if (isChecked) Color.Red else {
                                    if (isSystemInDarkTheme()) Color.White else Color.Black
                                }
                            }

                            // om below line we are specifying transition
                            val size by transition.animateDp(
                                transitionSpec = {
                                    // on below line we are specifying transition
                                    if (false isTransitioningTo true) {
                                        // on below line we are specifying key frames
                                        keyframes {
                                            // on below line we are specifying animation duration
                                            durationMillis = 250
                                            // on below line we are specifying animations.
                                            30.dp at 0 with LinearOutSlowInEasing // for 0-15 ms
                                            35.dp at 15 with FastOutLinearInEasing // for 15-75 ms
                                            40.dp at 75 // ms
                                            35.dp at 150 // ms
                                        }
                                    } else {
                                        spring(stiffness = Spring.StiffnessVeryLow)
                                    }
                                },
                                label = "Size"
                            ) { 30.dp }

                            // on below line we are creating icon for our toggle button.
                            Icon(
                                // on below line we are specifying icon for our image vector.
                                imageVector = if (checkedState) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favorite",
                                // on below line we are specifying
                                // tint for our icon.
                                tint = tint,
                                // on below line we are specifying
                                // size for our icon.
                                modifier = Modifier.size(size)
                            )
                        }
                    }
                }
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
                HorizontalDivider(color = Green80)
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

            if (showAsFavKural) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(
                            color = Green80
                        )
                        .padding(6.dp)
                        .clickable {
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
                    Text(
                        text = "விளக்கம் / Explanation",
                        modifier = Modifier.align(Alignment.CenterVertically),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        modifier = Modifier
                            .size(14.dp)
                            .align(Alignment.CenterVertically),
                        contentDescription = "drawable icons"
                    )
                }
            }
        }
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    val checkedState = remember { mutableStateOf(false) }

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
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .clip(RoundedCornerShape(2.dp))
                        .background(
                            color = Green80
                        )
                        .padding(6.dp)
                ) {
                    Text(
                        text = "குறள் - 133",
                        modifier = Modifier
                            .align(Alignment.Center),
                    )
                }

                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .clip(RoundedCornerShape(2.dp))
                ) {
                    IconToggleButton(
                        // on below line we are
                        // specifying default check state
                        checked = checkedState.value,
                        // on below line we are adding on check change
                        onCheckedChange = {
                            checkedState.value = !checkedState.value
                        }
                    ) {
                        // on below line we are creating a
                        // variable for our transition
                        val transition = updateTransition(checkedState.value)

                        // on below line we are creating a variable for
                        // color of our icon
                        val tint by transition.animateColor(label = "iconColor") { isChecked ->
                            // if toggle button is checked we are setting color as red.
                            // in else condition we are setting color as black
                            if (isChecked) Color.Red else Color.Black
                        }

                        // om below line we are specifying transition
                        val size by transition.animateDp(
                            transitionSpec = {
                                // on below line we are specifying transition
                                if (false isTransitioningTo true) {
                                    // on below line we are specifying key frames
                                    keyframes {
                                        // on below line we are specifying animation duration
                                        durationMillis = 250
                                        // on below line we are specifying animations.
                                        30.dp at 0 with LinearOutSlowInEasing // for 0-15 ms
                                        35.dp at 15 with FastOutLinearInEasing // for 15-75 ms
                                        40.dp at 75 // ms
                                        35.dp at 150 // ms
                                    }
                                } else {
                                    spring(stiffness = Spring.StiffnessVeryLow)
                                }
                            },
                            label = "Size"
                        ) { 30.dp }

                        // on below line we are creating icon for our toggle button.
                        Icon(
                            // on below line we are specifying icon for our image vector.
                            imageVector = if (checkedState.value) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite",
                            // on below line we are specifying
                            // tint for our icon.
                            tint = tint,
                            // on below line we are specifying
                            // size for our icon.
                            modifier = Modifier.size(size)
                        )
                    }
                }
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
                HorizontalDivider(color = Green80)
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

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(
                        color = Green80
                    )
                    .padding(6.dp)
            ) {
                Text(
                    text = "விளக்கம் / Explanation",
                    modifier = Modifier.align(Alignment.CenterVertically),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    modifier = Modifier
                        .size(14.dp)
                        .align(Alignment.CenterVertically),
                    contentDescription = "drawable icons"
                )
            }
        }
    }
}