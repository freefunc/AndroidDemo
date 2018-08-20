package com.bsesoft.androiddemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.bsesoft.androiddemo.view.ActivityManager

/**
 * @author zhangchen
 * @date 2018/8/13
 * @version 1.0
 */

@SuppressLint("Registered")
open class BaseActivity :AppCompatActivity() {

    val tag:String?
        get() = this::class.simpleName
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        ActivityManager.append(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.remove(this)
    }
}