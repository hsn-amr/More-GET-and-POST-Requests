package com.example.moregetandpostrequests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class RV(private val names: ArrayList<Name>): RecyclerView.Adapter<RV.ItemViewHolder>() {
    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val name = names[position]

        holder.itemView.apply {
            textView.text = name.name
        }
    }

    override fun getItemCount() = names.size
}