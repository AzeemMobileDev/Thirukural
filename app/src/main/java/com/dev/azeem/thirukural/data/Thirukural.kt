package com.dev.azeem.thirukural.data

import com.google.gson.annotations.SerializedName

data class Thirukural(
    @SerializedName("Number")
    val number: Int,
    @SerializedName("Line1")
    val lineNo1: String,
    @SerializedName("Line2")
    val lineNo2: String,
    @SerializedName("Translation")
    val translation: String,
    @SerializedName("mv")
    val explanationByMV: String,
    @SerializedName("sp")
    val explanationBySP: String,
    @SerializedName("mk")
    val explanationByMK: String,
    @SerializedName("explanation")
    val explanationInEnglish: String,
    @SerializedName("transliteration1")
    val transliteration1: String,
    @SerializedName("transliteration2")
    val transliteration2: String
)
