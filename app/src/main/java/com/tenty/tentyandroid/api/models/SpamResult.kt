package com.tenty.tentyandroid.api.models

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class SpamResult(
    @Json(name = "number") val number: String,
    @Json(name = "spam") val isSpam: Boolean
)