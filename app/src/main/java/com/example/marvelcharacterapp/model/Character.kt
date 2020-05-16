package com.example.marvelcharacterapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character (

    @SerializedName("id") val id : String,
    @SerializedName("name") val name : String,
    @SerializedName("description") val description : String,
    @SerializedName("modified") val modified : String,
    @SerializedName("resourceURI") val resourceURI : String,
    @SerializedName("urls") val urls : List<Urls>,
    @SerializedName("thumbnail") val thumbnail : Thumbnail,
    @SerializedName("comics") val comics : Comics,
    @SerializedName("stories") val stories : Stories,
    @SerializedName("events") val events : Events,
    @SerializedName("series") val series : Series
): Parcelable