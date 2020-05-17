package com.example.marvelcharacterapp.ui.characterList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelcharacterapp.R
import com.example.marvelcharacterapp.utils.characterFavoriteList
import com.example.marvelcharacterapp.utils.loadURL
import com.example.marvelcharacterapp.model.Character
import com.example.marvelcharacterapp.utils.TAG
import kotlinx.android.synthetic.main.character_item.view.*

class CharacterListAdapter(val onItemClick: (characterId: String, characterName: String, characterDescription: String, characterLargeImageUrl: String) -> Unit) :
    PagedListAdapter<Character, CharacterListAdapter.ViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { character ->
            holder.bindItem(character)
            holder.itemView.setOnClickListener {
                onItemClick(
                    character.id,
                    character.name,
                    character.description,
                    "${character.thumbnail.path}/portrait_fantastic.${character.thumbnail.extension}"
                )
            }
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var characterList: MutableList<Character> = characterFavoriteList
        fun bindItem(character: Character) {
            itemView.name.text = character.name
            itemView.image.loadURL("${character.thumbnail.path}/standard_large.${character.thumbnail.extension}")
            itemView.favButton.apply {
                visibility = View.VISIBLE
                if (characterList.contains(character))
                    setImageDrawable(resources.getDrawable(R.drawable.star_pressed, null))
                 else setImageDrawable(resources.getDrawable(R.drawable.star_normal, null))
                setOnClickListener {
                    if (!characterList.contains(character)) {
                        Log.i(TAG, " character is not contain ${character.name}")
                        characterList.add(character)
                        characterFavoriteList = characterList
                        Log.i(TAG, "${character.name} is added ")
                        Log.i(TAG, "add characterFavoriteList size is ${characterFavoriteList.size}")
                        characterFavoriteList.forEach { Log.i(TAG, "add characterFavoriteList is ${it.name}") }
                        setImageDrawable(resources.getDrawable(R.drawable.star_pressed, null))
                    } else {
                        Log.i(TAG, " character is contain ${character.name}")
                        characterList.remove(character)
                        characterFavoriteList = characterList
                        Log.i(TAG, "${character.name} is remove ")
                        Log.i(TAG, "remove characterFavoriteList size is ${characterFavoriteList.size}")
                        characterFavoriteList.forEach { Log.i(TAG, "remove characterFavoriteList is ${it.name}") }
                        setImageDrawable(resources.getDrawable(R.drawable.star_normal, null))
                    }
                }
            }
        }

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return false
        }

    }
}