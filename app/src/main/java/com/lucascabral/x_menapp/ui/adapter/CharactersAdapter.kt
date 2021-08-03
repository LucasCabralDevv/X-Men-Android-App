package com.lucascabral.x_menapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucascabral.x_menapp.data.network.model.Character
import com.lucascabral.x_menapp.databinding.CharacterItemBinding
import com.lucascabral.x_menapp.ui.adapter.CharactersAdapter.MyViewHolder

class CharactersAdapter : PagingDataAdapter<Character, MyViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    inner class MyViewHolder(
        private val binding: CharacterItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Character) {
            with(binding) {
                characterNameTextView.text = data.name
                characterAliasTextView.text = if (data.alias.isEmpty()) data.name else data.alias
                characterAffiliationTextView.text = data.affiliation
                characterDescriptionTextView.text = data.description
                Glide.with(characterImageView).load(data.img).into(characterImageView)
                val isExpandable = data.expanded
                binding.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

                binding.characterNameTextView.setOnClickListener {
                    data.expanded = !isExpandable
                    notifyItemChanged(bindingAdapterPosition)
                }
            }
        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == newItem.name
        }

    }
}