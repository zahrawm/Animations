package com.dannyose.rmapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.dannyose.rmapp.R
import com.dannyose.rmapp.data.model.Character

class CharactersAdapter(
    private val context: Context
) : ListAdapter<Character, CharactersAdapter.CharacterHolder>(CharacterDiffCallback) {


    inner class CharacterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCharacterImage: ImageView = itemView.findViewById(R.id.ivCharacterImage)
        val tvCharacterName: TextView = itemView.findViewById(R.id.tvCharacterName)
        val tvCharacterSpeciesStatus: TextView = itemView.findViewById(R.id.tvSpeciesStatus)
        val tvCharacterGender: TextView = itemView.findViewById(R.id.tvCharacterGender)
    }

    object CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.character_list_item, parent, false)
        return CharacterHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val character = getItem(position)
        holder.tvCharacterName.text = character.name

        val imageUrl = character.image
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .transform(RoundedCorners(20))
            .into(holder.ivCharacterImage)

        holder.tvCharacterGender.text = character.gender
        holder.tvCharacterSpeciesStatus.text = "${character.status} : ${character.species}"
    }

}