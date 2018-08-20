package com.bsesoft.androiddemo

import android.graphics.Color

/**
 * @author zhangchen
 * @date 2018/8/15
 * @version 1.0
 */

val rgba:(Int,Int,Int,Int) -> Int = {x,y,z,a -> Color.argb(a,x,y,z)}


val randomColor:() -> Int = {
    val random = Math.random()
    val x = (random*255).toInt()
    val y = (random*255.0).toInt()
    val z = (random*255.0).toInt()


    Color.argb(1,x,y,z)
}