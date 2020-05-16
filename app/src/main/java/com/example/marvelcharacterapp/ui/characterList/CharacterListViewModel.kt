package com.example.marvelcharacterapp.ui.characterList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.marvelcharacterapp.model.Character
import com.example.marvelcharacterapp.network.List_Page_Limit
import com.example.marvelcharacterapp.network.MarvelRepository
import com.example.marvelcharacterapp.network.listen


class CharacterListViewModel(private val repository: MarvelRepository) : ViewModel() {

    var characterLiveData  : LiveData<PagedList<Character>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        characterLiveData  = initializedPagedListBuilder(config).build()
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, Character> {

        val dataSourceFactory = object : DataSource.Factory<Int, Character>() {
            override fun create(): DataSource<Int, Character> {
                return getCharacterListWithPage()
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }

    private fun getCharacterListWithPage(offset: Int = 0) = object : PageKeyedDataSource<Int, Character>(){
        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, Character>
        ) {
            repository.getCharacterList(listen {
                onSuccess {callback.onResult(it!!, offset, offset + (List_Page_Limit - 1))  }
                onFailure { Log.i("CharacterList", "loadInitial exception is $it") }
            }, offset)
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
            repository.getCharacterList(listen {
                onSuccess { callback.onResult(it!!,  params.key + (List_Page_Limit - 1)) }
                onFailure { Log.i("CharacterList", "loadAfter exception is $it") }
            }, params.key)
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        }
    }

}
