package com.example.marvelcharacterapp.ui.favoriteCharacter

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelcharacterapp.R
import com.example.marvelcharacterapp.base.BaseFragment
import com.example.marvelcharacterapp.model.Character
import com.example.marvelcharacterapp.ui.characterDetail.CharacterDetailFragment
import com.example.marvelcharacterapp.ui.characterList.GridSpacingItemDecoration
import com.example.marvelcharacterapp.utils.characterFavoriteList
import kotlinx.android.synthetic.main.character_list_fragment.*

class FavoriteCharacterFragment private constructor(): BaseFragment<FavoriteCharacterViewModel>() {

    override val layout: Int= R.layout.favorite_character_list_fragment

    private val favoriteCharacterList: MutableList<Character> = mutableListOf()

    private val favoriteCharacterListAdapter = FavoriteCharacterListAdapter(favoriteCharacterList, ::navigateToCharacterDetail)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterListRecyclerView?.apply {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(GridSpacingItemDecoration(2, 10, true))
            itemAnimator = DefaultItemAnimator()
            adapter = favoriteCharacterListAdapter
        }
        favoriteCharacterList.addAll(characterFavoriteList)
        characterListRecyclerView?.adapter?.notifyDataSetChanged()
    }

    private fun navigateToCharacterDetail(
        characterId: String,
        characterName: String,
        characterDescription: String,
        characterLargeImageUrl: String
    ) {
        parentFragmentManager.beginTransaction()
            .add(R.id.container, CharacterDetailFragment.newInstance(characterId, characterName, characterDescription, characterLargeImageUrl))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance(): FavoriteCharacterFragment = FavoriteCharacterFragment()
    }

}