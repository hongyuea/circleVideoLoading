package com.example.videoloadingdemo

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import kotlin.math.cos
import kotlin.math.sin


/**
 * author:yuehong
 * date:2020/6/12
 * time:9:13
 */
class CircleVideoLoading:View {

    //    定义画笔
    var paint: Paint? = null

    // 半径
    var radius = 0f

    //左边距
    var marginLeft = 0f

    //画圆的矩形
    var oval: RectF? = null

    var path0:Path? = null
    var path1:Path? = null
    var path2:Path? = null
    var path3:Path? = null
    var path4:Path? = null

    var point0:Path? = null
    var point1:Path? = null
    var point2:Path? = null

    //    定义画笔
    var paint0: Paint? = null
    var paint1: Paint? = null
    var paint2: Paint? = null
    var paintPoint1: Paint? = null
    var paintPoint2: Paint? = null
    var paintPoint3: Paint? = null

    var paintEar1: Paint? = null//耳朵的画笔
    var paintMirk: Paint? = null//麦克风

    var alphaAnimatio: ValueAnimator? = null
    var animaDuration:Long = 1000
    var animaPercent:Float? = 0f

    var myColor:Int? = Color.GREEN //#09F738

    constructor(context:Context):super(context){
        initView()
    }

    constructor(context: Context,attributeSet: AttributeSet?):super(context,attributeSet){
        initView()
    }

    constructor(context: Context,attributeSet: AttributeSet?,defStyleAttr:Int):super(context,attributeSet,defStyleAttr){
        initView()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int):super(context,attributeSet,defStyleAttr,defStyleRes){
        initView()
    }

    fun initView(){

        paint = Paint()
        oval = RectF()

        path0 = Path()
        path1 = Path()
        path2 = Path()
        path3 = Path()
        path4 = Path()

        point0 = Path()
        point1 = Path()
        point2 = Path()

        paint0 = Paint()
        paint1 = Paint()
        paint2 = Paint()

        paintPoint1 = Paint()
        paintPoint2 = Paint()
        paintPoint3 = Paint()


        //给画笔设置颜色
        paint?.color = myColor!!
        paint?.style = Paint.Style.STROKE //画笔属性是空心圆
        paint?.strokeWidth = 7f //设置画笔粗细

        paint0?.color = myColor!!
        paint0?.style = Paint.Style.STROKE
        paint0?.alpha = 200
        paint0?.strokeWidth = 7f

        paint1?.color = myColor!!
        paint1?.style = Paint.Style.STROKE
        paint1?.alpha = 30
        paint1?.strokeWidth = 7f

        paint2?.color = myColor!!
        paint2?.style = Paint.Style.STROKE //画笔属性是空心圆
        paint2?.alpha = 150
        paint2?.strokeWidth = 7f

        paintPoint1?.color = myColor!!
        paintPoint1?.style = Paint.Style.STROKE //画笔属性是空心圆
        paintPoint1?.alpha = 205
        paintPoint1?.strokeWidth = 15f

        paintPoint2?.color = myColor!!
        paintPoint2?.style = Paint.Style.STROKE //画笔属性是空心圆
        paintPoint2?.alpha = 100
        paintPoint2?.strokeWidth = 15f

        paintPoint3?.color = myColor!!
        paintPoint3?.style = Paint.Style.STROKE //画笔属性是空心圆
        paintPoint3?.alpha = 30
        paintPoint3?.strokeWidth = 15f

        alphaAnimatio = ValueAnimator.ofFloat(0f,1f).setDuration(animaDuration)
        alphaAnimatio?.repeatMode = ValueAnimator.REVERSE//反向重复
        alphaAnimatio?.repeatCount = ValueAnimator.INFINITE //无限循环
        alphaAnimatio?.addUpdateListener {
            animaPercent = it.animatedValue as Float?
            paint0?.alpha = 120 + (100 * animaPercent!!).toInt()
            paint1?.alpha = 30 + (100 * animaPercent!!).toInt()
            paint2?.alpha = 70 + (130 * animaPercent!!).toInt()
            paintPoint1?.alpha = 200 + (50 * animaPercent!!).toInt()
            paintPoint2?.alpha = 100 + (100 * animaPercent!!).toInt()
            paintPoint3?.alpha = 100 + (-100 * animaPercent!!).toInt()
            invalidate()
        }

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        radius = width / 5f
        marginLeft = radius
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //画圆的矩形框
        oval?.left = marginLeft
        oval?.top = height / 2f - radius
        oval?.right = width / 2f + radius/2
        oval?.bottom = height / 2f + radius

        //画圆环
        paint?.strokeWidth = 7f
        canvas?.drawArc(oval!!, 80f, 285f, false, paint!!)

        //画耳朵
        paint?.strokeWidth = 30f
        canvas?.drawArc(oval!!,170f,20f,false,paint!!)
        canvas?.drawArc(oval!!,350f,20f,false,paint!!)

        //画麦克风
        paint?.strokeWidth = 15f
        canvas?.drawArc(oval!!,80f,20f,false,paint!!)

        path0?.moveTo(radius * 3 / 2,height/2f  - radius/6)
        path0?.lineTo(radius * 3 / 2,height/2f +  radius/6)
        canvas?.drawPath(path0!!,paint1!!)

        path1?.moveTo(radius + radius *3f/ 4,height/2f - radius * 5 / 12)
        path1?.lineTo(radius + radius *3f/ 4,height/2f + radius * 5 / 12)
        canvas?.drawPath(path1!!,paint2!!)

        path2?.moveTo(2 * radius,height/2f - radius * 2 /3 )
        path2?.lineTo(2 * radius,height/2f + radius * 2 / 3)
        canvas?.drawPath(path2!!,paint0!!)

        path3?.moveTo(2 * radius + radius / 4,height/2f - radius * 5 / 12)
        path3?.lineTo(2 * radius + radius / 4,height/2f + radius* 5 / 12)
        canvas?.drawPath(path3!!,paint2!!)

        path4?.moveTo(2 * radius + radius/2,height/2f - radius/6)
        path4?.lineTo(2 * radius + radius/2,height/2f + radius/6)
        canvas?.drawPath(path4!!,paint1!!)

        point0?.moveTo(2 * radius + radius * cos(Math.toRadians(55.0)).toFloat() + radius / 5,height / 2f + radius * sin(Math.toRadians(45.0)).toFloat())
        point0?.lineTo(2 * radius + radius * cos(Math.toRadians(55.0)).toFloat() + radius / 5 + 15,height / 2f + radius * sin(Math.toRadians(45.0)).toFloat() )
        canvas?.drawPath(point0!!,paintPoint1!!)

        point1?.moveTo(2 * radius + radius * cos(Math.toRadians(55.0)).toFloat() + radius * 7 / 10,height / 2f + radius * sin(Math.toRadians(45.0)).toFloat())
        point1?.lineTo(2 * radius + radius * cos(Math.toRadians(55.0)).toFloat() + radius * 7 / 10 + 15,height / 2f + radius * sin(Math.toRadians(45.0)).toFloat() )
        canvas?.drawPath(point1!!,paintPoint2!!)

        point2?.moveTo( 2 * radius + radius * cos(Math.toRadians(55.0)).toFloat() + radius * 6 / 5,height / 2f + radius * sin(Math.toRadians(45.0)).toFloat())
        point2?.lineTo( 2 * radius + radius * cos(Math.toRadians(55.0)).toFloat() + radius * 6 / 5 + 15,height / 2f + radius * sin(Math.toRadians(45.0)).toFloat())
        canvas?.drawPath(point2!!,paintPoint3!!)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        alphaAnimatio?.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        alphaAnimatio?.removeAllUpdateListeners()
        alphaAnimatio?.cancel()
    }
}