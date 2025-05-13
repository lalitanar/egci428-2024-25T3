package com.egci428.listactivity

/*
* Created by Lalita N. on 8/5/25
*/

class Course (val courseNumber:Int, val title: String, val description:String, val credits: Double) {
    override fun toString(): String {
        return title
    }
}