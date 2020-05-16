package com.example.marvelcharacterapp.ui.characterList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelcharacterapp.R
import com.example.marvelcharacterapp.model.Character
import kotlinx.android.synthetic.main.character_item.view.*

class CharacterListAdapter: PagedListAdapter<Character, CharacterListAdapter.ViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bindItem(it) }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindItem(character: Character) {
            bindImage("${character.thumbnail.path}/standard_large.${character.thumbnail.extension}")
            itemView.name.text = character.name
        }

        private fun bindImage(url: String) {
            Glide.with(itemView.context.applicationContext)
                .load(url)
                .into(itemView.image)
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