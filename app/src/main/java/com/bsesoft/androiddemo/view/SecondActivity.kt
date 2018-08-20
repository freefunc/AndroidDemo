package com.bsesoft.androiddemo.view

import android.os.Bundle
import android.view.View
import com.bsesoft.androiddemo.BaseActivity
import com.bsesoft.androiddemo.R
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : BaseActivity(),View.OnClickListener{

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_go_back -> {
                finish()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        btn_go_back.setOnClickListener(this)
    }
}
