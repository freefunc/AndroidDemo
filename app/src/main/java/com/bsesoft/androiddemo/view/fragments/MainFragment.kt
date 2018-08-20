package com.bsesoft.androiddemo.view.fragments


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.view.*
import com.bsesoft.androiddemo.R
import com.bsesoft.androiddemo.view.SecondActivity
import com.bsesoft.androiddemo.view.toast
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MainFragment : Fragment(), View.OnClickListener {
    private var param1: String? = null
    private var param2: String? = null

    //单例返回Fragment
    companion object {

        @JvmStatic
        fun newInstance() =
        //Fragment f =  new MainFragment();
        //f.setArguments(bundle);
        //return f;
                MainFragment().apply {
                    /*Bundle bundle = new Bundle();
                      bundle.putString("");
                      bundle.putString("");
                      this.setArguments(bundle)
                      */
                    arguments = Bundle().apply {
//                        putString(ARG_PARAM1, param1)
//                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    private fun bindEvent() {
        btn_obvious_switch?.setOnClickListener(this)
        btn_implicit_switch?.setOnClickListener(this)
        btn_switch_to_other_app_activity?.setOnClickListener(this)
        btn_switch_to_camera?.setOnClickListener(this)
    }

    //重写点击事件
    override fun onClick(v: View?) {
        val intent: Intent by lazy { Intent() }
        when (v?.id) {
            R.id.btn_obvious_switch -> {
                intent.setClass(activity, SecondActivity::class.java)
            }
            R.id.btn_implicit_switch -> {
                intent.action = "com.bsesoft.androiddemo.SECOND_ACTIVITY"
            }
            R.id.btn_switch_to_other_app_activity -> {
                intent.data = Uri.parse("http://www.baidu.com")
            }
            R.id.btn_switch_to_camera -> {
                intent.action = MediaStore.ACTION_IMAGE_CAPTURE
                toast("打开相机！")
            }
        }
        startActivity(intent)
    }


    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menu?.add(0, 0, 0, R.string.context_menu_selectionAll)
        menu?.add(0, 1, 1, R.string.context_menu_cut)
        menu?.add(0, 2, 2, R.string.context_menu_copy)
        menu?.add(0, 3, 3, R.string.context_menu_paste)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    //Fragment 注册上下文菜单OnActivityCreated中
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadSpinner()
        registerForContextMenu(edit_text)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when (item?.groupId) {
            0 -> {
                when (item.itemId) {
                    0 -> edit_text?.selectAll()
                    1 -> editTextCut(edit_text)
                    2 -> editTextCopy(edit_text)
                    3 -> editTextPaste(edit_text)
                }
            }
        }
        return super.onContextItemSelected(item)
    }


    override fun onResume() {
        super.onResume()
        bindEvent()
    }


}
