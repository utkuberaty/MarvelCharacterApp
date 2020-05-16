package com.example.marvelcharacterapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Events (

	@SerializedName("available") val available : String,
	@SerializedName("returned") val returned : String,
	@SerializedName("collectionURI") val collectionURI : String,
	@SerializedName("items") val items : List<Items>
): Parcelable