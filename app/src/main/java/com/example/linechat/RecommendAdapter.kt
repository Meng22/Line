package com.example.linechat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecommendAdapter: RecyclerView.Adapter<RecommendAdapter.ViewHolder>() {
    val recoomendList:MutableList<String> = ArrayList()
    var listener : ItemClickListener ?= null

    interface ItemClickListener{
        fun toClick(recommend: String)
    }
    fun setToClick(listener: ItemClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recommend_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recoomendList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recoomendList[position])
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tv_recommend = view.findViewById<TextView>(R.id.tv_recommend)

        fun bind(recommend: String){
            tv_recommend.text = recommend
            tv_recommend.setOnClickListener {
                listener?.toClick(recommend)
            }
        }
    }
    fun update(newList: ArrayList<String>){
        recoomendList.clear()
        recoomendList.addAll(newList)
        notifyDataSetChanged()
    }
}