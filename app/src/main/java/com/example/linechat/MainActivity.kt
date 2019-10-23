package com.example.linechat

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val manager = supportFragmentManager
    private val Listener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
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

//        var num = (Math.random()*100).toInt()  //當要取範圍0~(n-1)的亂數時，先乘上n，用toInt()取得四捨五入的值
//        var hour = (Math.random()*25).toInt()
//        var min = Random.nextInt(11)+ 49
//        var index = Random.nextInt(7)
//        String.format("%02d")  //java中int转String位数不够前面补零

        for (i in 0 until 55){
            val items = item(imageList[Random.nextInt(7)], "標題${i}", "內容${i}",
                "${String.format("%02d", (Math.random()*25).toInt())}:${Random.nextInt(48)+ 10}", "${(Math.random()*100).toInt()}", false)
            itemList.add(items)
        }
        val navView: BottomNavigationView = findViewById(R.id.nav_view)   //底部導覽列
        navView.setOnNavigationItemSelectedListener(Listener)
    }

}
