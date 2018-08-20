package com.bsesoft.androiddemo.view

import android.app.Activity
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * @author zhangchen
 * @date 2018/8/15
 * @version 1.0
 */

//Activity  函数扩展
fun Activity.toast(charSequence: CharSequence, duration:Int = Toast.LENGTH_SHORT){
    Toast.makeText(this,"${this::class.simpleName}____$charSequence",duration).show()
}
//Fragment  函数扩展
fun Fragment.toast(charSequence: CharSequence, duration:Int = Toast.LENGTH_SHORT){
    Toast.makeText(this.activity,"${this::class.simpleName}____$charSequence",duration).show()
}


