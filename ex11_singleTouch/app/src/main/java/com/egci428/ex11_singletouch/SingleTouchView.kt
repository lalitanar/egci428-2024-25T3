package com.egci428.ex11_singletouch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import android.view.View


/*
* Created by Lalita N. on 4/6/25
*/

class SingleTouchView(context: Context): View(context) {
    private val paint = Paint()
    private  val path = Path()
    private  var eventX: Float = 0F
    private  var eventY: Float = 0F
    private var fingerDown = false

    init {
        paint.isAntiAlias = true
        paint.strokeWidth = 6F
        paint.strokeJoin = Paint.Join.ROUND
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        eventX = event.x
        eventY = event.y

        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                fingerDown = true
                path.moveTo(eventX, eventY)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(eventX,eventY)
            }
            MotionEvent.ACTION_UP -> {
                fingerDown = false
            }
            else -> return false
        }
        invalidate()


        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPath(path,paint)
        if (fingerDown){
            canvas.drawCircle(eventX,eventY,10F, paint)
        }
    }
}