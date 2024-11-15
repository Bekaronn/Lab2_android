package com.example.simpleapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simpleapp.R
import com.example.simpleapp.databinding.ItemCatBinding
import com.example.simpleapp.model.entity.Cat

class CatAdapter : ListAdapter<Cat, CatAdapter.ViewHolder>(CatItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemCatBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cat: Cat) {
            with(binding) {
                catName.text = cat.name
                catOrigin.text = cat.origin
                catLength.text = cat.length
                catFamilyFriendly.text = "Family Friendly: ${cat.familyFriendly}/5"
                catShedding.text = "Shedding: ${cat.shedding}/5"

                Glide
                    .with(root.context)
                    .load(cat.imageUrl.takeIf { it.isNotEmpty() } ?: R.drawable.default_cat_image)
                    .into(catImage)
            }
        }
    }
}
