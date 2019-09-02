package com.example.android.learnmongolian.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WordProperty (
    val id: Int,
    val in_mongolian: String,
    val in_chinese: String,
    val pronounce: String,
    val image_url: String,
    val audio_url: String
): Parcelable