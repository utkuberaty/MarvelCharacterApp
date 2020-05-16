package com.example.marvelcharacterapp.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@JsonIgnoreProperties(ignoreUnknown = true)

data class Data<T> (

	@SerializedName("offset") val offset : String,
	@SerializedName("limit") val limit : String,
	@SerializedName("total") val total : String,
	@SerializedName("count") val count : String,
	@SerializedName("results") val results : List<T>
)