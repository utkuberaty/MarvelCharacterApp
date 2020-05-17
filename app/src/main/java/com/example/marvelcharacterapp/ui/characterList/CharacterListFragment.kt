package com.example.marvelcharacterapp.ui.characterList

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelcharacterapp.R
import com.example.marvelcharacterapp.base.BaseFragment
import com.example.marvelcharacterapp.model.Character
import com.example.marvelcharacterapp.ui.characterDetail.CHARACTER_ID
import com.example.marvelcharacterapp.ui.characterDetail.CHARACTER_NAME
import com.example.marvelcharacterapp.ui.characterDetail.CharacterDetailFragment
import com.example.marvelcharacterapp.ui.favoriteCharacter.FavoriteCharacterFragment
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.character_list_fragment.*


class CharacterListFragment private constructor() : BaseFragment<CharacterListViewModel>() {

    override val layout: Int = R.layout.character_list_fragment

    private val characterListAdapter by lazy { CharacterListAdapter(::navigateToCharacterDetail) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        characterListRecyclerView?.apply {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(GridSpacingItemDecoration(2, 10, true))
            itemAnimator = DefaultItemAnimator()
            adapter = characterListAdapter
        }

        viewModel.characterLiveData.observe(viewLifecycleOwner, Observer {
            characterListAdapter.submitList(it)
        })

        favoriteListButton?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .add(R.id.container, FavoriteCharacterFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun navigateToCharacterDetail(
        characterId: String,
        characterName: String,
        characterDescription: String,
        characterLargeImageUrl: String
    ) {
        context?.let { FirebaseAnalytics.getInstance(it).apply {
            logEvent("selectedCharacter", bundleOf(CHARACTER_ID to characterId, CHARACTER_NAME to characterName))
        } }
        parentFragmentManager.beginTransaction()
            .add(R.id.container, CharacterDetailFragment.newInstance(characterId, characterName, characterDescription, characterLargeImageUrl))
            .addToBackStack(null)
            .commit()
    }


    companion object {
        fun newInstance() = CharacterListFragment()
    }


}
