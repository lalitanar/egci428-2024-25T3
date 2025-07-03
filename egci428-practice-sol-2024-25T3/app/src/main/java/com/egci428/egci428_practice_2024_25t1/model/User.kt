package com.egci428.egci428_practice_2024_25t1.model

/*
 * Created by Lalita N. on 27/11/24.
 */

class User (val username: String, val password: String, val latitude: Double, val longitude: Double){
 constructor(): this("","", 0.toDouble(), 0.toDouble())
}