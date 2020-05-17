package com.example.marvelcharacterapp.ui.favoriteCharacter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelcharacterapp.R
import com.example.marvelcharacterapp.model.Character
import com.example.marvelcharacterapp.ui.favoriteCharacter.FavoriteCharacterListAdapter.ViewHolder
import com.example.marvelcharacterapp.utils.TAG
import com.example.marvelcharacterapp.utils.loadURL
import kotlinx.android.synthetic.main.character_item.view.*

class FavoriteCharacterListAdapter(
    private val favoriteCharacterList: List<Character>?,
    val onItemClick: (characterId: String, characterName: String,
                      characterDescription: String, characterLargeImageUrl: String) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        )

    override fun getItemCount(): Int {
        Log.i(TAG, "ItemCount is ${favoriteCharacterList?.size}")
       return favoriteCharacterList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        favoriteCharacterList?.get(position)?.let { character ->
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
        fun bindItem(character: Character) {
            itemView.name.text = character.name
            itemView.image.loadURL("${character.thumbnail.path}/standard_large.${character.thumbnail.extension}")
        }
    }
}