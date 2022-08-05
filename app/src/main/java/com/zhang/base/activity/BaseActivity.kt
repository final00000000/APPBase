package com.zhang.base.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.gyf.immersionbar.ImmersionBar
import com.zhang.base.action.IsBase
import com.zhang.base.network.NetWorkState
import com.zhang.base.network.NetworkManager
import com.zhang.base.utils.BaseUtils.getViewBindingForActivity

/**
 * @Author : zhang
 * @Create Time : 2022/8/5
 * @Class Describe : 描述
 * @Project Name : Base
 */
open class BaseActivity<VB : ViewBinding>(layoutId: Int = 0) : AppCompatActivity(layoutId), IsBase {

    lateinit var mBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initImmersionBar()
        NetworkManager.instance.mNetworkStateCallback.observe(this) {
            onNetworkStateChanged(it)
        }

    }

    open fun onNetworkStateChanged(netState: NetWorkState){}

    fun initViewBinding(): View {
        mBinding = getViewBindingForActivity(layoutInflater)
        return mBinding.root
    }

    /**
     * 初始化设置沉浸式状态栏
     */
    private fun initImmersionBar() {
        ImmersionBar.with(this)
            .statusBarDarkFont(true, 0.2f)
            .init()
    }

    /**
     * 关闭界面
     */
    fun finishTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition()
        } else {
            super.finish()
        }
    }
}