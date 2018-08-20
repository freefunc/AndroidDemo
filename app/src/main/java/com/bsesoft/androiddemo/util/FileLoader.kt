package com.bsesoft.androiddemo.util

import java.io.File

/**
 * @author zhangchen
 * @date 2018/8/15
 * @version 1.0
 */



object FileLoader{

    private var filePath:String? = ""
    set(value) {
        if(value != null){
            value.replace("\\", File.separator)
            field = value
        }
    }


    fun getFileLoader(filePath:String) {
        FileLoader.filePath = filePath
    }

}