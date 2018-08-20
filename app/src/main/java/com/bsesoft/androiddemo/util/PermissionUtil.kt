package com.bsesoft.androiddemo.util

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.orhanobut.logger.Logger

/**
 * @author zhangchen
 * @date 2018/8/13
 * @version 1.0
 */
object PermissionUtil {
    @TargetApi(Build.VERSION_CODES.M)
    private fun checkPermission(list: Array<String>, context:Context) :List<String>{
        return list.filter { permission: String -> context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED }
    }
    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermission(list:Array<String>, context: Context,permissionRequestCode:Int){
        val permissions = checkPermission(list, context)
        if(permissions.isNotEmpty()){
            if(context is Activity){
                context.requestPermissions(permissions.toTypedArray(),permissionRequestCode)
            } else throw RuntimeException(context::class.qualifiedName+" unable cast to an Activity")
        }else Logger.d("没有可请求的权限！")
    }
}