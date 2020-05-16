package com.example.marvelcharacterapp.ui.characterDetail

import android.os.Bundle
import android.view.View
import com.example.marvelcharacterapp.base.BaseFragment
import com.example.marvelcharacterapp.R

class CharacterDetailFragment:BaseFragment<CharacterDetailViewModel>(){

    override val layout: Int = R.layout.character_detail_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}