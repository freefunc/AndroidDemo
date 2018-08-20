package com.bsesoft.androiddemo

import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.bsesoft.androiddemo.enums.CodeEnum
import com.bsesoft.androiddemo.util.PermissionUtil
import com.bsesoft.androiddemo.view.fragments.MainFragment
import com.bsesoft.androiddemo.view.toast
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_title.*

//将一些基本不变的东西放到框架 Activity  中，子页面由 ViewPager 和 Fragment 实现
//比如 设置 Toolbar、请求权限等
class MainActivity : BaseActivity() {

    private val permissionRequestCode: Int = CodeEnum.PERMISSION_REQUEST_CODE.code

    private fun initLogger() {
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    private fun initToolBar() {

        tool_bar.inflateMenu(R.menu.options_menu_item)

        tool_bar.setOnMenuItemClickListener {
            toast( "点击了 ${it?.title} 项")
            false
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLogger()
        window.statusBarColor = resources.getColor(R.color.colorPrimary, null)
        setContentView(R.layout.activity_main)
        initToolBar()
        loadViewPager()
        PermissionUtil.requestPermission(getNeedPermissionArray(), this, permissionRequestCode)
    }


    @TargetApi(Build.VERSION_CODES.M)
    private fun getNeedPermissionArray(): Array<String> {
        return arrayOf(android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_CONTACTS,
                android.Manifest.permission.WRITE_CONTACTS)
    }

    //处理权限
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == permissionRequestCode) {
            for ((index, result) in grantResults.withIndex()) {
                // Logger.d("%s:%d",permissions[index],result)
                if (result == PackageManager.PERMISSION_GRANTED) {
                    Logger.d("%s,权限获取成功！", permissions[index])
                } else {
                    Logger.d("%s,权限获取失败！", permissions[index])
                }
            }
        }
    }

    private fun loadViewPager() {
        //TODO 加载  ViewPager ,默认 MainFragment ,其他延迟加载
        //Fragment List
        val fragmentList: MutableList<Fragment> = ArrayList()
        fragmentList.add(MainFragment.newInstance())
        //Title List
        val titleList: MutableList<String> = ArrayList()
        titleList.add(resources.getString(R.string.main_fragment))
        //实例化适配器
        val mVPAdapter: FragmentPagerAdapter = PagerSwitchAdapter(
                fragmentManager = supportFragmentManager,
                fragmentList = fragmentList,
                titleList = titleList
        )
        //设置适配器
        vp_fragments.adapter = mVPAdapter

    }

    companion object {
        class PagerSwitchAdapter(fragmentManager: FragmentManager, val fragmentList: List<Fragment>, val titleList: List<String>) : FragmentPagerAdapter(fragmentManager) {

            override fun getItem(position: Int): Fragment {
                return fragmentList[position]
            }

            override fun getCount(): Int {
                return fragmentList.size
            }


            override fun getPageTitle(position: Int): CharSequence {

                return titleList[position]
            }

        }
    }

}
