package com.egci428.ex16_firestore

/*
* Created by Lalita N. on 10/6/25
*/

class Message(val id: String, val message: String, rating: Int, val timeStamp: String) {
    constructor(): this("","",0,"")
}