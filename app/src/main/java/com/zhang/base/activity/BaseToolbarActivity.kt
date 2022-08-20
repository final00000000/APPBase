package com.zhang.base.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.zhang.base.R
import com.zhang.base.utils.BaseUtils
import com.zhang.base.utils.BaseUtils.getViewModel

/**
 * @Author : zhang
 * @Create Time : 2022/8/5
 * @Project Name : Base
 * @Class Describe : 带Toolbar的Base基类
 */
abstract class BaseToolbarActivity<VB : ViewBinding, VM : ViewModel>(layoutId: Int = 0) :
    BaseActivity<VB>(layoutId) {

    lateinit var mViewModel: VM

    /*  标题  */
    lateinit var tvPageTitle: TextView

    /*  右侧标题文字  */
    lateinit var tvRightTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = this.getViewModel() as VM

        setContentView(R.layout.activity_base_toolbar)
        findViewById<FrameLayout>(R.id.baseContent).addView(initViewBinding())

        findViewById<ImageView>(R.id.ivPageBack).setOnClickListener { onBack(it) }
        tvPageTitle = findViewById(R.id.tvPageTitle)
        tvRightTitle = findViewById(R.id.tvRightTitle)

        initView(savedInstanceState)

        initData()

        onClick()

        createObserver()
    }


    protected abstract fun initView(savedInstanceState: Bundle?)
    protected abstract fun initData()
    protected abstract fun onClick()
    protected abstract fun createObserver()

    open fun onBack(v: View) {
        finishTransition()
    }
}