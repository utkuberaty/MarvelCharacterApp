package com.example.marvelcharacterapp.ui.characterList

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelcharacterapp.base.BaseFragment
import kotlinx.android.synthetic.main.character_list_fragment.*
import com.example.marvelcharacterapp.R
import com.example.marvelcharacterapp.model.Character

class CharacterListFragment : BaseFragment<CharacterListViewModel>() {

    override val layout: Int = R.layout.character_list_fragment

    private val characterListAdapter by lazy { CharacterListAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterListRecyclerView?.apply {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(GridSpacingItemDecoration(2,10, true))
            itemAnimator = DefaultItemAnimator()
            adapter = characterListAdapter
        }

        viewModel.characterLiveData.observe(viewLifecycleOwner, Observer {
            characterListAdapter.submitList(it)
        })

    }


    companion object {
        fun newInstance() = CharacterListFragment()
    }


}
