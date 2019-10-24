package com.example.linechat

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val manager = supportFragmentManager
    private val listener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_friend -> {
                title =""
                val transaction = manager.beginTransaction()
                transaction.replace(R.id.frameLayout, BlankFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_chat -> {
                title = "聊天"
                val transaction = manager.beginTransaction()
                transaction.replace(R.id.frameLayout, ChatFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_posts -> {
                title =""
                val transaction = manager.beginTransaction()
                transaction.replace(R.id.frameLayout, BlankFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_today -> {
                title =""
                val transaction = manager.beginTransaction()
                transaction.replace(R.id.frameLayout, BlankFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_pocket -> {
                title =""
                val transaction = manager.beginTransaction()
                transaction.replace(R.id.frameLayout, BlankFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageList = mutableListOf(R.drawable.icon_1,
            R.drawable.icon_2, R.drawable.icon_3, R.drawable.icon_4,R.drawable.icon_5, R.drawable.icon_6, R.drawable.icon_7)
        for (i in 0 until 55){
            val items = Item(imageList[Random.nextInt(7)], "標題${i}", "內容${i}",
                "${String.format("%02d", (Math.random()*24).toInt())}:${Random.nextInt(48)+ 10}", (Math.random()*100).toInt(), false)
            itemList.add(items)
        }

        val navView: BottomNavigationView = findViewById(R.id.nav_view)   //底部導覽列
        navView.setOnNavigationItemSelectedListener(listener)

    }

    override fun onStart() {
        unread_num(itemList)
        super.onStart()
    }

    fun unread_num(newList: List<Item>){
        val numList = newList.map { it.num }
        var sums = 0
        for (i in 0 until numList.size){
            sums = sums + numList[i]
        }
        if (sums > 999){
            tv_unread.text = "999+"
        }else if(sums == 0){
            tv_unread.visibility = View.INVISIBLE
        }
        else{
            tv_unread.visibility = View.VISIBLE
            tv_unread.text = "$sums"
        }
    }


}
