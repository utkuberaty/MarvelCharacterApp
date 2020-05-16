package com.example.marvelcharacterapp

import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.character_item.view.*

val Any.TAG: String
    get() = javaClass.simpleName

fun ImageView.loadURL(url: String?) {
    Glide.with(MarvelApplication.instance?.applicationContext!!).load(url).into(this)
}