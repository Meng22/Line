package com.example.linechat

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    private val editAdapter = EditAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        title = "編輯聊天列表"

//        val listToEdit = intent.getStringExtra("listToEdit")
//        val itemList = Gson().fromJson(listToEdit, List<item>::class.java)

        recycleview2.layoutManager = LinearLayoutManager(this)
        recycleview2.adapter  = editAdapter
        editAdapter.update(itemList)
        editAdapter.setToSelect(object : EditAdapter.ItemClickListener{   //建立物件
            override fun toSelect(item : Item) {
                item.isSelected = !item.isSelected
                Toast.makeText(this@EditActivity, "選取${item.title}", Toast.LENGTH_SHORT).show()
            }
        })

        btn_delete.setOnClickListener {
            val deleteItems = itemList.filter { it.isSelected == true }  //要刪除的item們
            deleteItems.forEach { itemList.remove(it) }
            Toast.makeText(this, "刪除資料", Toast.LENGTH_SHORT).show()
            editAdapter.update(itemList)
        }
        val actionBar = supportActionBar    //得到返回鍵
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}