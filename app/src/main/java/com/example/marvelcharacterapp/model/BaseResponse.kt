package com.example.marvelcharacterapp.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class BaseResponse<T> (

    @SerializedName("code") val code : String,
    @SerializedName("status") val status : String,
    @SerializedName("copyright") val copyright : String,
    @SerializedName("attributionText") val attributionText : String,
    @SerializedName("attributionHTML") val attributionHTML : String,
    @SerializedName("data") val data : Data<T>,
    @SerializedName("etag") val etag : String
)