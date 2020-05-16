package com.example.marvelcharacterapp.ui.characterDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelcharacterapp.model.Comic
import com.example.marvelcharacterapp.network.MarvelRepository
import com.example.marvelcharacterapp.network.listen

class CharacterDetailViewModel(private val repository: MarvelRepository): ViewModel() {

    internal var comicListObserver = MutableLiveData<List<Comic>?>()

    fun getComicList(characterId: String) {
        repository.getCharacterComicList(listen {
            onSuccess { comicListObserver.value = it }
            onFailure { Log.i("getComicList", "exception is $it") }
        }, characterId)
    }
}