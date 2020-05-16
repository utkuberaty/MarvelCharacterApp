package com.example.marvelcharacterapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Data<T> (

	@SerializedName("offset") val offset : String,
	@SerializedName("limit") val limit : String,
	@SerializedName("total") val total : String,
	@SerializedName("count") val count : String,
	@SerializedName("results") val results : List<T>
)