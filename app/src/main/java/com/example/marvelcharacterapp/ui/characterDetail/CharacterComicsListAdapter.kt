package com.example.marvelcharacterapp.ui.characterDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelcharacterapp.R
import com.example.marvelcharacterapp.utils.loadURL
import com.example.marvelcharacterapp.model.Comic
import kotlinx.android.synthetic.main.character_item.view.*

class CharacterComicsListAdapter(private val comicList: List<Comic>?) :
    RecyclerView.Adapter<CharacterComicsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        )


    override fun getItemCount(): Int = comicList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        comicList?.get(position)?.apply { holder.bindItem(this) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(comic: Comic) {
            itemView.apply {
                name.text = comic.title
                image.loadURL("${comic.thumbnail.path}/standard_xlarge.${comic.thumbnail.extension}")
            }
        }
    }
}