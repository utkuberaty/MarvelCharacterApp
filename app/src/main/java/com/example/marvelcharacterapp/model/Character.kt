package com.example.marvelcharacterapp.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class Character (

    @SerializedName("id") val id : String,
    @SerializedName("name") val name : String,
    @SerializedName("description") val description : String,
    @SerializedName("thumbnail") val thumbnail : Thumbnail
): Parcelable