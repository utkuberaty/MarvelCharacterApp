package com.example.marvelcharacterapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.marvelcharacterapp.MarvelApplication

val Any.TAG: String
    get() = javaClass.simpleName

fun ImageView.loadURL(url: String?) {
    Glide.with(MarvelApplication.instance?.applicationContext!!).load(url).into(this)
}