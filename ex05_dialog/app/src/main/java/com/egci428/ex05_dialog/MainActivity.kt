package com.egci428.ex05_dialog

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Arrays

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val alertBtn = findViewById<Button>(R.id.alertBtn)
        val optionBtn = findViewById<Button>(R.id.optionBtn)
        val selectBtn = findViewById<Button>(R.id.selectBtn)

        val alertTextView = findViewById<TextView>(R.id.alertTextView)
        val optionTextView = findViewById<TextView>(R.id.optionTextView)
        val selectTextView = findViewById<TextView>(R.id.selectTextView)

        alertBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Do you want to delete this item?")
            builder.setTitle("Alert!")
            builder.setCancelable(false)

            builder.setPositiveButton("Yes") { dialog, which ->
                alertTextView.text = "Selected Item: Yes"
            }
            builder.setNegativeButton("No") { dialog, which ->
                dialog.cancel()
                alertTextView.text = "Selected Item: No"
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }

        val checkedItem = intArrayOf(-1)
        optionBtn.setOnClickListener {
            val optionDialog = AlertDialog.Builder(this)
            optionDialog.setIcon(R.drawable.ic_launcher_background)
            optionDialog.setTitle("Chooose one item from list below.")
            val listItems = arrayOf("Ant", "Bat","Cat","Dog")
            optionDialog.setSingleChoiceItems(listItems, checkedItem[0]) { dialog, which ->
                checkedItem[0] = which
                //optionTextView.text = "Selected item is: ${listItems[which]}"
                //dialog.dismiss()
            }
            optionDialog.setPositiveButton("Confirm") { dialog, which ->
                optionTextView.text = "Selected item is: ${listItems[checkedItem[0]]}"
            }
            optionDialog.setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }
                val customOptionDialog = optionDialog.create()
                customOptionDialog.show()
        }

        val listItem = arrayOf("C","C++","Python", "Java")
        val selCheckItem = BooleanArray(listItem.size)
        val selectItem = mutableListOf(*listItem)
        
        selectBtn.setOnClickListener {
            selectTextView.text = null
            val selectBuilder = AlertDialog.Builder(this)
            selectBuilder.setTitle("Choose the programming languages")
            selectBuilder.setIcon(R.drawable.ic_launcher_background)
            selectBuilder.setMultiChoiceItems(listItem,selCheckItem){ dialog, which, isChecked ->
                selCheckItem[which] = isChecked
                
            }
            selectBuilder.setCancelable(false)
            selectBuilder.setPositiveButton("Done") { dialog, which ->
                for (i in selCheckItem.indices){
                    if(selCheckItem[i]){
                        selectTextView.text = String.format("%s%s, ", selectTextView.text, selectItem[i])
                    }
                }
            }
            selectBuilder.setNegativeButton("clear all") { dialog, which ->
                Arrays.fill(selCheckItem, false)
            }
            val selectDialog = selectBuilder.create()
            selectDialog.show()
        }
    }
}