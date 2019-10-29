package com.example.linechat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class MyAdapter(val itemList:MutableList<Item>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return itemList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val img_icon = v.findViewById<CircleImageView>(R.id.search_icon)
        val tv_title = v.findViewById<TextView>(R.id.search_title)
        val tv_content = v.findViewById<TextView>(R.id.search_message)
        val tv_time = v.findViewById<TextView>(R.id.tv_time)
        val tv_number = v.findViewById<TextView>(R.id.tv_number)

        fun bind(item: Item) {
            img_icon.setImageResource(item.image)
            tv_title.text = item.title
            tv_content.text = item.content
            tv_time.text = item.time

//            tv_number.setText(item.num)
            if (item.num == 0){
                tv_number.visibility = View.INVISIBLE //4
            }else{
                tv_number.visibility = View.VISIBLE//Remember!!!!!!
                tv_number.text = item.num.toString()
            }
        }
    }
    fun update(newList: List<Item>){
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }




//    如果只變更畫面，寫在adpater裡面
//    fun sort(){
//        itemList.sortByDescending { it.time.substring(0,2) }
//        notifyDataSetChanged()
//    }
//    fun unread(){
//        itemList.sortByDescending { it.num }
//        notifyDataSetChanged()
//    }
//
//    fun markAllRead() {
//        update(itemList.map { it.copy(num = "0") })
//    }

}
