package com.egci428.ex15_basicsqlite

/*
* Created by Lalita N. on 5/6/25
*/

class Comment {
    var id: Long = 0
    var message: String = ""

    override fun toString(): String {
        return message.toString()
    }
}