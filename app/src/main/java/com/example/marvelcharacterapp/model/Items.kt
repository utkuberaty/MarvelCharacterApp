package com.example.marvelcharacterapp.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class Items (
	@SerializedName("resourceURI") val resourceURI : String,
	@SerializedName("name") val name : String
): Parcelable
