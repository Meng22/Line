package com.example.linechat


import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search_history.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchHistoryFragment : Fragment() {
    private val recordAdapter = RecordAdapter()
    private val recommendAdapter = RecommendAdapter()
    private var recordList = ArrayList<Recordword>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_history, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        showRecommend()
        getRecord()
        super.onActivityCreated(savedInstanceState)
    }

    fun showRecord(recordlist: ArrayList<Recordword>){
        rv_SearchHistory.layoutManager = LinearLayoutManager(this.context)  //搜尋歷史
        rv_SearchHistory.adapter = recordAdapter
        recordAdapter.setToClick(object: RecordAdapter.ItemClickListener{
            override fun toDelete(record: Recordword) {
                deleteRecord(record)
                getRecord()
            }
        })
        recordAdapter.update(recordlist)
    }


    fun getRecord(){
        val sharedPreferences = (activity as SearchActivity).getSharedPreferences("saveSearch", Activity.MODE_PRIVATE)
        val keyList = sharedPreferences.all.keys.sorted()
        recordList.clear()
        for (key in keyList){
            val record = Recordword(key, sharedPreferences?.getString(key, "")!!)
            recordList.add(record)
        }
        showRecord(recordList)
    }
//
    fun deleteRecord(keyword: Recordword){
        val sharedPreferences = (activity as SearchActivity).getSharedPreferences("saveSearch", Activity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(keyword.key).apply()
    }

    fun showRecommend(){
        val list = arrayListOf("NBA", "12強", "星宇航空", "世界大戰", "川普", "英國脫歐", "小三通", "香港", "山林解禁", "總統")

        if (list.isEmpty()){  //防止畫面壞掉
            list.add("日本旅遊")
            list.add("健行")
            list.add("嘉明湖")
            rv_recommend.layoutManager = GridLayoutManager(this.context, 4)
            rv_recommend.adapter = recommendAdapter
            recommendAdapter.update(list)

        }else {
            rv_recommend.layoutManager = GridLayoutManager(this.context, 4)
            rv_recommend.adapter = recommendAdapter
            recommendAdapter.setToClick(object :RecommendAdapter.ItemClickListener{
                override fun toClick(recommend: String) {

                }
            })
            recommendAdapter.update(list)
        }
    }





}
