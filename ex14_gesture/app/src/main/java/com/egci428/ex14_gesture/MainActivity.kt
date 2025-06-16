package com.egci428.ex14_gesture

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private val MIN_SWIPE_DISTANCE_X = 100
    private val MIN_SWIPE_DISTANCE_Y = 100

    private val MAX_SWIPE_DISTANCE_X = 1000
    private val MAX_SWIPE_DISTANCE_Y = 1000

    private var statusMsg:TextView? = null
    private var gestureDetector: GestureDetectorCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        statusMsg = findViewById(R.id.statusMsg)
        this.gestureDetector = GestureDetectorCompat(this,this)
        gestureDetector!!.setOnDoubleTapListener(this)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        this.gestureDetector!!.onTouchEvent(event)
        return true
    }

    override fun onDown(p0: MotionEvent): Boolean {
        statusMsg!!.text = "onDown"
        return true
    }

    override fun onShowPress(p0: MotionEvent) {
        statusMsg!!.text = "onShowPress"
    }

    override fun onSingleTapUp(event: MotionEvent): Boolean {
        statusMsg!!.text = "onSingleTapUp"
        return true
    }

    override fun onScroll(
        p0: MotionEvent?,
        p1: MotionEvent,
        p2: Float,
        p3: Float
    ): Boolean {
//        statusMsg!!.text = "onScroll"
        return true
    }

    override fun onLongPress(p0: MotionEvent) {
        statusMsg!!.text = "onLongPress"
    }

    override fun onFling(
        event1: MotionEvent?,
        event2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {

        val deltaX = event1!!.x - event2.x
        val deltaY = event1!!.y - event2.y
        val deltaX_Abs = Math.abs(deltaX)
        val deltaY_Abs = Math.abs(deltaY)

        if((deltaX_Abs >= MIN_SWIPE_DISTANCE_X) && (deltaX_Abs<=MIN_SWIPE_DISTANCE_X)){
            if(deltaX>0){
                statusMsg!!.text = "Swipe to left"
            } else {
                statusMsg!!.text = "Swipe to right"
            }
        }

        if((deltaY_Abs >= MIN_SWIPE_DISTANCE_Y) && (deltaY_Abs<=MIN_SWIPE_DISTANCE_Y)){
            if(deltaY>0){
                statusMsg!!.text = "Swipe up"
            } else {
                statusMsg!!.text = "Swipe down"
            }
        }

        return true
    }

    override fun onSingleTapConfirmed(p0: MotionEvent): Boolean {
        statusMsg!!.text = "onSingleTapConfirmed"
        return true
    }

    override fun onDoubleTap(p0: MotionEvent): Boolean {
        statusMsg!!.text = "onDoubleTap"
        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent): Boolean {
        statusMsg!!.text = "onDoubleTapEvent"
        return true
    }
}