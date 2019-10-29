package com.example.linechat

data class Item(
    val image: Int,
    val title: String,
    val content: String,
    val time: String,
    var num: Int,
    var isSelected: Boolean = false
)

data class Recordword(
    val key: String,
    val value: String
)

var itemList=mutableListOf<Item>()

