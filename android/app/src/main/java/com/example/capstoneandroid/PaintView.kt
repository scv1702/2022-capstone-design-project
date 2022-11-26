package com.example.capstoneandroid

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.capstoneandroid.stylusActivity.Companion.paintBrush
import com.example.capstoneandroid.stylusActivity.Companion.path

class PaintView : View{

    var params : ViewGroup.LayoutParams? = null

    companion object{
        var pathList = ArrayList<Path>()
        var colorList = ArrayList<Int>()
        var currentBrush = Color.BLACK;
    }

    constructor(context: Context) : super(context, null) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init(){
        paintBrush.isAntiAlias = true
        paintBrush.color = currentBrush
        paintBrush.style = Paint.Style.STROKE
        paintBrush.strokeJoin = Paint.Join.ROUND
        paintBrush.strokeWidth = 8f

        params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
    var id = IntArray(3) // id값을 저장하는 배열

    override fun onTouchEvent(event: MotionEvent) :Boolean{
        var x = event.x
        var y = event.y

        var pointer_count = event.pointerCount // 현재 터치 발생한 포인터 수를 얻는다.
        if(pointer_count > 2) pointer_count = 2; //3개 이상의 포인트를 터치했더라도 2개까지만 처리를 한다.

        when(event.action and MotionEvent.ACTION_MASK){
            MotionEvent.ACTION_DOWN -> {  //한 개 포인트에 대한 DOWN을 얻을 때.
                if(pointer_count == 1) {
                    path.moveTo(x,y)
                    return true
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if(pointer_count == 1) {
                    path.lineTo(x, y)
                    pathList.add(path)
                    colorList.add(currentBrush)
                }
            }
            else -> return false
        }
        postInvalidate()
        return false;
    }


    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)
        for(i in pathList.indices){
            paintBrush.setColor(colorList[i])
            canvas.drawPath(pathList[i], paintBrush)
            invalidate()
        }
    }

}