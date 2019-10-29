package com.example.linechat

data class Item(
    val image: Int,
    val title: String,
    val content: String,
    val time: String,
    var num: Int,
    var isSelected: Boolean = false
)

var itemList=mutableListOf<Item>()

