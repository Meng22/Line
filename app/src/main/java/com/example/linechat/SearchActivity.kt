package com.example.linechat

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_searchmessage.*

class SearchActivity : AppCompatActivity() {
    private val searchResultAdapter = SearchResultAdapter()
    private val recordAdapter = RecordAdapter()
    lateinit var sharedPreferences : SharedPreferences
    private var keyword = ""
    private var recordList = ArrayList<Recordword>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchmessage)
        sharedPreferences = getSharedPreferences("saveRecord", Activity.MODE_PRIVATE)
        getRecord()

        btn_leaveSearch.setOnClickListener {
            onBackPressed()
        }


        img_search.setOnClickListener {
            keyword = ed_search.text.toString()   //搜尋的關鍵字

            if (ed_search.text.isNullOrEmpty()){
                getRecord()        //刷新歷史紀錄

                inner_constraint.visibility = View.VISIBLE
                rv_searchResult.visibility = View.GONE
                tv_unfound.visibility = View.GONE

            }else {
                val checkList = ArrayList<String>()
                recordList.forEach {
                    if ("$keyword" == it.value) checkList.add("true") }

                if (checkList.isEmpty())
                saveRecord(keyword)


                val search_list = itemList.filter { "$keyword" in it.content || "$keyword" in it.title}

                if (search_list.isEmpty()){
                    tv_unfound.visibility = View.VISIBLE
                    rv_searchResult.visibility = View.GONE
                    inner_constraint.visibility = View.GONE
                }else {
                    rv_searchResult.layoutManager = LinearLayoutManager(this)
                    rv_searchResult.adapter = searchResultAdapter
                    searchResultAdapter.update(search_list)

                    inner_constraint.visibility = View.GONE
                    tv_unfound.visibility = View.GONE
                    rv_searchResult.visibility = View.VISIBLE
                }
            }

        }

    }
    fun getRecord(){
        val keyList = sharedPreferences.all.keys.sorted()
        recordList.clear()
        for (key in keyList){
            val record = Recordword(key, sharedPreferences.getString(key, "")!!)
            recordList.add(record)                                    //弄出全部的record字串(陣列)
        }                                                            //取資料時要包好
        showRecord(recordList)
    }

    fun showRecord(recordlist: ArrayList<Recordword>){
        rv_recentSearch.layoutManager = LinearLayoutManager(this)
        rv_recentSearch.adapter = recordAdapter
        recordAdapter.setToClick(object: RecordAdapter.ItemClickListener{
            override fun toDelete(record: Recordword) {
                deleteRecord(record)
            }
        })
        recordAdapter.update(recordlist)
    }
    fun deleteRecord(keyword: Recordword){
        val editor = sharedPreferences.edit()
        editor.remove(keyword.key).apply()
        getRecord()
    }

    fun saveRecord(keyword: String){
        val editor = sharedPreferences.edit()
        var i = 0
        while (!sharedPreferences.getString("record-${i}","").isNullOrEmpty()){
            i++
        }
        editor.putString("record-${i}", keyword)
        editor.apply()
    }


}