package com.example.marvelcharacterapp

import com.example.marvelcharacterapp.model.Character
import io.paperdb.Paper

const val CHARACTER_FAVORITE_LIST = "characterFavoriteList"

var characterFavoriteList: MutableList<Character>
get() = Paper.book().read(CHARACTER_FAVORITE_LIST) ?: mutableListOf()
set(value) { Paper.book().write(CHARACTER_FAVORITE_LIST, value) }