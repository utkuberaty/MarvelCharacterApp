package com.example.marvelcharacterapp.di

import com.example.marvelcharacterapp.network.MarvelRepository
import com.example.marvelcharacterapp.network.RetrofitService
import com.example.marvelcharacterapp.ui.characterDetail.CharacterDetailViewModel
import com.example.marvelcharacterapp.ui.characterList.CharacterListViewModel
import com.example.marvelcharacterapp.ui.favoriteCharacter.FavoriteCharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitService() }
}

val viewModelModule = module {

    single { MarvelRepository() }
    viewModel { CharacterListViewModel(get()) }
    viewModel { CharacterDetailViewModel(get()) }
    viewModel { FavoriteCharacterViewModel() }


}