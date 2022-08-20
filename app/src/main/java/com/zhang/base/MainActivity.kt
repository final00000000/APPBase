package com.zhang.base

import android.os.Bundle
import android.widget.Toast
import com.zhang.base.activity.BaseToolbarActivity
import com.zhang.base.activity.BaseVBVMActivity
import com.zhang.base.databinding.ActivityMainBinding
import com.zhang.base.network.NetWorkState
import com.zhang.base.utils.BaseUtils
import java.util.*

class MainActivity :
    BaseToolbarActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override fun initView(savedInstanceState: Bundle?) {

        mBinding.apply {
            tvStatusBarHeight.text = "状态栏高度=:> ${BaseUtils.statusBarHeight}"
        }
    }

    override fun initData() {

    }

    override fun onClick() {
        mBinding.apply {
            tvStatusBarHeight.setOnClickListener {
                mViewModel.mLiveData().value = Random().nextInt(100).toString()
            }
        }
    }

    override fun onNetworkStateChanged(netState: NetWorkState) {
        super.onNetworkStateChanged(netState)
        if (netState.isSuccess) {
            Toast.makeText(this, "来网了", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "网丢哪儿去了", Toast.LENGTH_SHORT).show()
        }
    }

    override fun createObserver() {
        mViewModel.apply {
            mLiveData().observe(this@MainActivity) {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

}