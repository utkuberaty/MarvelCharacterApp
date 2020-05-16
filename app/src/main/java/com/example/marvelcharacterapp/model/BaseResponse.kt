package com.example.marvelcharacterapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class BaseResponse<T> (

    @SerializedName("code") val code : String,
    @SerializedName("status") val status : String,
    @SerializedName("copyright") val copyright : String,
    @SerializedName("attributionText") val attributionText : String,
    @SerializedName("attributionHTML") val attributionHTML : String,
    @SerializedName("data") val data : Data<T>,
    @SerializedName("etag") val etag : String
)