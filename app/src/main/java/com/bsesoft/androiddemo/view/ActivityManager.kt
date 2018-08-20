package com.bsesoft.androiddemo.view

import android.app.Activity
import com.orhanobut.logger.Logger

/**
 * @author zhangchen
 * @date 2018/8/14
 * @version 1.0
 */
object ActivityManager{

    @JvmStatic
    private val activities:ArrayList<Activity> = ArrayList()


    @JvmStatic
    fun append(activity: Activity){
        Logger.d("activities 增加了一个 Activity ${activity::class.simpleName}")
        activities.add(activity)
    }
    @JvmStatic
    fun remove(activity: Activity){
        Logger.d("activities 移除了一个 Activity ${activity::class.simpleName}")
        activities.remove(activity)
    }

    @JvmStatic
    fun finishAll(){
        for (activity in activities){
            activity.finish()
        }
    }

}

