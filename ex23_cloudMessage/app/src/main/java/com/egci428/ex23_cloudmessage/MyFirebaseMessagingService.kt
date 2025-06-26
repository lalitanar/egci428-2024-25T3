package com.egci428.ex23_cloudmessage

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK

/*
* Created by Lalita N. on 26/6/25
*/

class MyFirebaseMessagingService: FirebaseMessagingService() {
    val channelId = "EGCI428 Notification Channel"
    val channelName = "com.egci428.ex23_cloudmessage"
    val TAG = "Clouds Message"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "From: ${remoteMessage.from}")

        if(remoteMessage.notification != null) {
            Log.d(TAG, "Message Title Payload: ${remoteMessage.notification!!.title}")
            Log.d(TAG, "Message Body Payload: ${remoteMessage.notification!!.body}")
            Log.d(TAG, "Message Image URL Payload: ${remoteMessage.notification!!.imageUrl}")

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("mTitle", remoteMessage.notification!!.title)
            intent.putExtra("mBody", remoteMessage.notification!!.body)
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }


    }
}