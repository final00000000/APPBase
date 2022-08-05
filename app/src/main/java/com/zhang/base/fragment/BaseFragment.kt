package com.zhang.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.zhang.base.network.NetWorkState
import com.zhang.base.network.NetworkManager
import com.zhang.base.utils.BaseUtils.getViewBindingForFragment

/**
 * @Author : zhang
 * @Create Time : 2022/8/5
 * @Class Describe : 描述
 * @Project Name : Base
 */
abstract class BaseFragment<VB : ViewBinding>(layoutId: Int = 0) : Fragment(layoutId) {

    lateinit var mBinding: VB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mBinding = getViewBindingForFragment(layoutInflater, container)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(savedInstanceState)

        initData()

        onClick()
        NetworkManager.instance.mNetworkStateCallback.observe(this) {
            onNetworkStateChanged(it)
        }
    }

    open fun onNetworkStateChanged(netState: NetWorkState){}

    protected abstract fun initView(savedInstanceState: Bundle?)

    protected abstract fun initData()

    protected abstract fun onClick()

}