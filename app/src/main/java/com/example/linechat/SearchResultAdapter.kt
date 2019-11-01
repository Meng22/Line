package com.example.linechat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {
    val searchList = ArrayList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.search_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(searchList[position])
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val search_icon = view.findViewById<ImageView>(R.id.search_icon)
        val search_title = view.findViewById<TextView>(R.id.search_title)
        val search_message = view.findViewById<TextView>(R.id.search_message)
        val search_arrow = view.findViewById<ImageView>(R.id.search_arrow)

        fun bind(item: Item){
            search_icon.setImageResource(item.image)
            search_title.text = item.title
            search_message.text = "找到 n 則訊息"

        }
    }
    fun update(newList: List<Item>){
        searchList.addAll(newList)
        notifyDataSetChanged()
    }
}