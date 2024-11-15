package com.example.simpleapp.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.simpleapp.model.entity.Cat

class CatItemCallBack : DiffUtil.ItemCallback<Cat>() {
    override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem == newItem
    }
}

