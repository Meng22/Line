package com.example.linechat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecordAdapter: RecyclerView.Adapter<RecordAdapter.ViewHolder>() {
    val recordList:MutableList<Recordword> = ArrayList()
    var recordlistener : ItemClickListener? = null

    interface ItemClickListener{
        fun toDelete(record: Recordword)
    }
    fun setToClick(listener: ItemClickListener){
        recordlistener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.record_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recordList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recordList[position])
    }
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tv_record = view.findViewById<TextView>(R.id.tv_record)
        val delete_record = view.findViewById<ImageView>(R.id.delete_record)  //尚未設定點擊事件

        fun bind(record: Recordword){
            tv_record.text = record.value
            delete_record.setOnClickListener {
                recordlistener?.toDelete(record)
            }
        }
    }
    fun update(newList: ArrayList<Recordword>){
        recordList.clear()
        recordList.addAll(newList)
        notifyDataSetChanged()
    }
}