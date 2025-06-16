package com.egci428.ex15_basicsqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

/*
* Created by Lalita N. on 5/6/25
*/

class CommentDataSource(context: Context) {
    private var database: SQLiteDatabase? = null
    private val dbHelper: MySQLiteHelper
    private  val allColumns = arrayOf<String>(MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_COMMENT)

    init {
        dbHelper = MySQLiteHelper(context)
    }

    fun open(){
        database = dbHelper.writableDatabase
    }
    fun close(){
        dbHelper.close()
    }


    val allComments: List<Comment>
        get() {
            val comments = ArrayList<Comment>()
            val cursor = database!!.query(MySQLiteHelper.TABLE_COMMENTS, allColumns,null, null, null, null, null)

            cursor.moveToFirst()
            while (!cursor.isAfterLast){
                val comment = cursorToComment(cursor)
                comments.add(comment)
                cursor.moveToNext()
            }
            cursor.close()
            return comments
        }

    private fun cursorToComment(cursor: Cursor): Comment {
        val comment = Comment()
        comment.id = cursor.getLong(0)
        comment.message = cursor.getString(1)
        return comment
    }

    fun createComment(comment: String): Comment{
        val values = ContentValues()
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment.toString())
        val insertId = database!!.insert(MySQLiteHelper.TABLE_COMMENTS, null,values)
        val cursor = database!!.query(MySQLiteHelper.TABLE_COMMENTS, allColumns, MySQLiteHelper.COLUMN_ID+" = "+insertId, null, null,null, null)

        cursor.moveToFirst()
        val newComment = cursorToComment(cursor)
        cursor.close()

        return newComment
    }

    fun deleteComment(){

    }


}