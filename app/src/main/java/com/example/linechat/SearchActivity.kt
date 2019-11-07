package com.example.linechat

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_searchmessage.*
import kotlinx.android.synthetic.main.fragment_search.*

class SearchActivity : AppCompatActivity() {
    private var recordList = ArrayList<Recordword>()
    private val SearchHistory : Fragment = SearchHistoryFragment()
    private val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchmessage)

        //初始fragment
        val transaction1 = manager.beginTransaction()
        transaction1.replace(R.id.searchFragmentLayout, SearchHistory).commit()

        //返回鍵
        btn_leaveSearch.setOnClickListener {
            onBackPressed()
        }

        //監聽EditText
        ed_search.addTextChangedListener (object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (ed_search.text.isNullOrEmpty()) {
                    val transaction2 = supportFragmentManager.beginTransaction()
                    transaction2.replace(R.id.searchFragmentLayout, SearchHistory).commit()

                } else {
                    val keyword = ed_search.text.toString()
                    val search_list = itemList.filter { "$keyword" in it.content || "$keyword" in it.title}

                    val SearchResult: Fragment = SearchFragment(search_list)
                    val transaction3 = supportFragmentManager.beginTransaction()
                    transaction3.replace(R.id.searchFragmentLayout, SearchResult).commit()


                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })

        //搜尋按鈕儲存搜尋紀錄
        img_search.setOnClickListener {
                checkHistory()
        }
    }

    fun checkHistory(){
        val keyword = ed_search.text.toString()
        //判斷有無重複的字眼，儲存
        val sharedPreferences = getSharedPreferences("saveSearch", Activity.MODE_PRIVATE)
        val keyList = sharedPreferences.all.keys.sorted()
        for (key in keyList){
            val record = Recordword(key, sharedPreferences.getString(key, "")!!)
            recordList.add(record)
        }
        val checkList = ArrayList<String>()
        recordList.forEach { if ("$keyword" == it.value) checkList.add("true") }
        if (checkList.isEmpty())
            saveRecord(keyword)
        }


    fun saveRecord(keyword: String) {

        val sharedPreferences = getSharedPreferences("saveSearch", Activity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        var i = 0
        while (!sharedPreferences.getString("record-${i}", "").isNullOrEmpty()) {
            i++
        }
        editor.putString("record-${i}", keyword)
        editor.apply()
    }
}