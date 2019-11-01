package com.example.linechat


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_searchmessage.*
import kotlinx.android.synthetic.main.fragment_search.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchFragment(var list: List<Item>) : Fragment() {
    private val searchResultAdapter = SearchResultAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        val keyword = (activity as SearchActivity).ed_search.text.toString()
//        val search_list = itemList.filter { "$keyword" in it.content || "$keyword" in it.title}


        if (list.isEmpty()){
            tv_unfound.visibility = View.VISIBLE
            rv_searchResult.visibility = View.GONE
        }else {
            rv_searchResult.layoutManager = LinearLayoutManager(this.context)
            rv_searchResult.adapter = searchResultAdapter
            searchResultAdapter.update(list)
            tv_unfound.visibility = View.GONE
            rv_searchResult.visibility = View.VISIBLE
    }
        super.onActivityCreated(savedInstanceState)
    }


}
