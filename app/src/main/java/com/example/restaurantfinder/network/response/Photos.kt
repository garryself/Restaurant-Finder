package com.example.example

import com.google.gson.annotations.SerializedName


data class Photos(
    @SerializedName("photo_reference")
    var photoReference: String? = null,
)