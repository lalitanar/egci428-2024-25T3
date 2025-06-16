package com.egci428.ex16_firestore

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

/*
* Created by Lalita N. on 10/6/25
*/

class MessageAdapter(val mContext: Context, val layoutResId: Int, val messageList: List<Message>): ArrayAdapter<Message> (mContext, layoutResId, messageList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater.inflate(layoutResId, null)
        val messageTextView = view.findViewById<TextView>(R.id.msgView)
        messageTextView.text = messageList[position].message
        return view
    }

}