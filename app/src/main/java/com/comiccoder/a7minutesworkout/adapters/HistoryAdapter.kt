package com.comiccoder.a7minutesworkout.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.comiccoder.a7minutesworkout.databinding.ItemHistoryRowBinding
import com.comiccoder.a7minutesworkout.models.HistoryEntity

class HistoryAdapter(private val items: ArrayList<HistoryEntity>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemHistoryRowBinding): RecyclerView.ViewHolder(binding.root){
        val llHistoryItemMain = binding.llHistoryItemMain
        val tvItem = binding.tvItem
        val tvPosition = binding.tvPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = ItemHistoryRowBinding.inflate(layoutInflater, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val history = items[position]
        holder.tvPosition.text = (position + 1).toString()
        holder.tvItem.text = history.date
        if(position % 2 == 0)
        {
            holder.llHistoryItemMain.setBackgroundColor(Color.parseColor("#EBEBEB"))
        }
        else
        {
            holder.llHistoryItemMain.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }
}