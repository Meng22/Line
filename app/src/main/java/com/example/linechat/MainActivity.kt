package com.example.linechat

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.badge.BadgeDrawable
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
                transaction.replace(R.id.frameLayout, LineChatFramgnet()).commit()
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
    lateinit var navView: BottomNavigationView
    private var sums: Int = 0

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

        unread_num(itemList)
        navView = findViewById(R.id.nav_view)
//        val navView: BottomNavigationView = findViewById(R.id.nav_view)   //綁定底部導覽列xml
        navView.setOnNavigationItemSelectedListener(listener)               //監聽底部導覽欄位的變化
        showBadge(sums)
    }

    override fun onStart() {
        unread_num(itemList)
        showBadge(sums)
        super.onStart()
    }

    fun showBadge(num: Int){
        navView.showBadge(R.id.nav_chat).apply {
            if (num > 999) number = 999
            else if (num == 0) navView.removeBadge(R.id.nav_chat)
            else number = num
        }
    }

    fun removeBadge(){
        navView.removeBadge(R.id.nav_chat)
        }

    fun unread_num(newList: List<Item>){
        val numList = newList.map { it.num }
        sums = 0
        for (i in 0 until numList.size){
            sums = sums + numList[i]
        }
    }


}
