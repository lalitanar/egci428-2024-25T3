package com.egci428.listactivity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    protected var data = ArrayList<Course>()
    val DETAIL_REQUEST_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val listView = findViewById<ListView>(R.id.listView)
        data = DataProvider.getData()

        val courseArrayAdapter = CourseArrayAdapter(this,0,data)
        listView.adapter = courseArrayAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val aCourse = data[position]
            sendDetail(aCourse, position)
        }

    }



    private  fun sendDetail(course: Course, position: Int){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("courseTitle", course.title)
        startActivity(intent)
    }

    private class CourseArrayAdapter(var context: Context, var resource:Int, var objects: ArrayList<Course>): BaseAdapter(){
        override fun getCount(): Int {
            return objects.size
        }

        override fun getItem(position: Int): Any? {
            return objects[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View? {
            val aRow: View
            val aCourse = objects[position]
            val colorCode = arrayListOf<String>("#FFFFFF","#E5E5E5")

            if (convertView == null){
                //val inflator = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val layoutInflator = LayoutInflater.from(viewGroup!!.context)
                aRow = layoutInflator.inflate(R.layout.course_row, viewGroup, false)
                val titleTextView = aRow.findViewById<TextView>(R.id.titleTextView)
                val imageView = aRow.findViewById<ImageView>(R.id.imageView)

                val viewHolder = ViewHolder(titleTextView,imageView)
                aRow.tag = viewHolder
            } else {
                aRow = convertView
            }

            val viewHolder = aRow.tag as ViewHolder
            viewHolder.titleTextView.text = aCourse.title
            val imgpos = position%3+1
            val res = context.resources.getIdentifier("image1010"+imgpos, "drawable",context.packageName)
            viewHolder.imageView.setImageResource(res)

            aRow.setBackgroundColor(Color.parseColor(colorCode[position%2]))

            return aRow
        }

    }
    private class ViewHolder(val titleTextView: TextView, val imageView: ImageView)
}