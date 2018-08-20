package com.bsesoft.androiddemo.view.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.text.Editable
import android.widget.ArrayAdapter
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * @author zhangchen
 * @date 2018/8/15
 * @version 1.0
 */
fun MainFragment.editTextCut(v: EditText?) {
    if (v != null) {
        editTextCopy(v)
        v.text = Editable.Factory().newEditable("")
    }
}

fun MainFragment.editTextCopy(v: EditText?) {
    if (v != null) {
        val manager: ClipboardManager = activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val editTextContent: String = v.text.toString()
        val clipData: ClipData = ClipData.newPlainText("editTextContent", editTextContent)
        manager.primaryClip = clipData
    }
}

fun MainFragment.editTextPaste(v: EditText?) {
    if (v != null) {
        val manager: ClipboardManager = activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        if (manager.hasPrimaryClip()) {
            val item: ClipData.Item = manager.primaryClip.getItemAt(0)
            v.text = Editable.Factory().newEditable(item.text)
        }
    }
}

fun MainFragment.loadSpinner(){
    single_line_spinner.adapter = ArrayAdapter<String>(activity,android.R.layout.simple_list_item_1, android.R.id.text1, listOf("item1","item2","item3","item4","item6"))
}
