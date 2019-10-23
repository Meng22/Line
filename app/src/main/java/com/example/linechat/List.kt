package com.example.linechat

data class item(
    val image: Int,
    val title: String,
    val content: String,
    val time: String,
    val num: String,
    var isSelected: Boolean = false
)


        var itemList=mutableListOf<item>()

