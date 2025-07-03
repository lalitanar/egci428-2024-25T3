package com.egci428.egci428_practice_2024_25t1.model

/*
 * Created by Lalita N. on 27/11/24.
 */

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.egci428.egci428_practice_2024_25t1.R

class UserAdapter (val mContext: Context, val layoutResId: Int, val userList: List<User>): ArrayAdapter<User>(mContext, layoutResId, userList) {
 override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

  val layoutInflator: LayoutInflater = LayoutInflater.from(mContext)
  val view:View = layoutInflator.inflate(layoutResId, null)
  val usrTextView = view.findViewById<TextView>(R.id.usrTextView)
  val usr = userList[position]
  usrTextView.text = usr.username
  return view
 }
}