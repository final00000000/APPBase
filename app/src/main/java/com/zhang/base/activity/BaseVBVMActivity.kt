package com.zhang.base.activity

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.zhang.base.utils.BaseUtils.getViewModel

/**
 * @Author : zhang
 * @Create Time : 2022/8/5
 * @Class Describe : 描述
 * @Project Name : Base
 */
abstract class BaseVBVMActivity<VB : ViewBinding, VM : ViewModel>(layoutId: Int = 0) :
    BaseActivity<VB>(layoutId) {

    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = this.getViewModel() as VM

        setContentView(initViewBinding())
        initView(savedInstanceState)
        initData()
        onClick()
        createObserver()
    }


    protected abstract fun initView(savedInstanceState: Bundle?)
    protected abstract fun initData()
    protected abstract fun onClick()
    protected abstract fun createObserver()
}