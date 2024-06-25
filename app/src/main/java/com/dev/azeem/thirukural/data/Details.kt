package com.dev.azeem.thirukural.data

import com.google.gson.annotations.SerializedName

data class Details(
    val section: Section
) {
    data class Section(
        val detail: List<SectionDetail>
    ) {
        data class SectionDetail(
            val name: String,
            val transliteration: String,
            val number: Int,
            val chapterGroup: ChapterGroup
        ) {
            data class ChapterGroup(
                @SerializedName("detail")
                val chapterGroupDetail: List<ChapterGroupDetail>
            ){
                data class ChapterGroupDetail(
                    val name: String,
                    val translation: String,
                    val transliteration: String,
                    val number: Int,
                    val chapters: Chapters
                ) {
                    data class Chapters(
                        @SerializedName("detail")
                        val chaptersDetails: List<ChaptersDetails>
                    ) {
                        data class ChaptersDetails(
                            val name: String,
                            val translation: String,
                            val transliteration: String,
                            val number: Int,
                            val start: Int,
                            val end: Int
                        )
                    }
                }
            }
        }
    }
}
