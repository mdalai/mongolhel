package com.example.android.learnmongolian.network


data class GroupProperty(
    val group_id: Int,
    val in_mongolian: String,
    val in_chinese: String,
    val pronounce: String,
    val image_url: String,
    val audio_url: String
)