package com.example.marvelcharacterapp.ui.characterList

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelcharacterapp.R
import com.example.marvelcharacterapp.base.BaseFragment
import com.example.marvelcharacterapp.model.Character
import com.example.marvelcharacterapp.ui.characterDetail.CharacterDetailFragment
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
        fun newInstance() = CharacterListFragment()
    }


}
