package com.example.marvelcharacterapp.ui.characterDetail

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelcharacterapp.base.BaseFragment
import com.example.marvelcharacterapp.R
import com.example.marvelcharacterapp.loadURL
import com.example.marvelcharacterapp.model.Comic
import kotlinx.android.synthetic.main.character_detail_fragment.*

const val CHARACTER_ID = "characterId"
const val CHARACTER_NAME = "characterName"
const val CHARACTER_DESCRIPTION = "characterDescription"
const val CHARACTER_LARGE_IMAGE_URL = "characterLargeImageUrl"

class CharacterDetailFragment private constructor():BaseFragment<CharacterDetailViewModel>(){

    override val layout: Int = R.layout.character_detail_fragment

    private var comicList: MutableList<Comic>? = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(CHARACTER_ID, "")?.let { viewModel.getComicList(it) }

        arguments?.getString(CHARACTER_NAME, "").apply {
            characterName.text = this
            characterNameHeader.text = this
        }

        characterDescription.text = arguments?.getString(CHARACTER_DESCRIPTION, getString(R.string.no_description))
        characterImage.loadURL(arguments?.getString(CHARACTER_LARGE_IMAGE_URL, ""))

        characterComicsRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context).apply { orientation = LinearLayoutManager.HORIZONTAL }
            adapter = CharacterComicsListAdapter(comicList)
        }

        viewModel.comicListObserver.observe(viewLifecycleOwner, Observer {
            it?.let { comics -> comicList?.addAll(comics) }
            characterComicsRecyclerView?.adapter?.notifyDataSetChanged()
        })
    }

    companion object {
        fun newInstance(characterId: String, characterName: String, characterDescription: String, characterLargeImageUrl: String) =
            CharacterDetailFragment().apply {
                arguments = bundleOf(
                    CHARACTER_ID to characterId,
                    CHARACTER_NAME to characterName,
                    CHARACTER_DESCRIPTION to characterDescription,
                    CHARACTER_LARGE_IMAGE_URL to characterLargeImageUrl
                )
            }
    }
}