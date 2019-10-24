package com.example.linechat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class EditAdapter: RecyclerView.Adapter<EditAdapter.ViewHolder>(){
    val itemList:MutableList<Item> = ArrayList()
    private var listener : ItemClickListener? = null    //這個變數有interface裡的方法可以使用

    interface ItemClickListener{
        fun toSelect(item: Item)   //interface裡面的function都沒有被實作 x{}
    }
    fun setToSelect(listener : ItemClickListener){
        this.listener = listener         //指定(名稱不一樣可略)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.edit_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val img_icon = v.findViewById<CircleImageView>(R.id.img_icon)
        val tv_title = v.findViewById<TextView>(R.id.tv_title)
        val tv_content = v.findViewById<TextView>(R.id.tv_content)
        val tv_time = v.findViewById<TextView>(R.id.tv_time)
        val chk_delete = v.findViewById<CheckBox>(R.id.chk_delete)


        fun bind(item: Item){
            img_icon.setImageResource(item.image)
            tv_title.setText(item.title)
            tv_content.setText(item.content)
            tv_time.setText(item.time)
            chk_delete.isChecked = item.isSelected   //每次綁資料都會去更新
            chk_delete.setOnClickListener{
                listener?.toSelect(item)
            }
        }

    }
    fun update(newList: List<Item>){
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

}