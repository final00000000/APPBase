package com.zhang.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.zhang.base.network.NetWorkState
import com.zhang.base.network.NetworkManager
import com.zhang.base.utils.BaseUtils.getViewBindingForFragment
import java.lang.reflect.ParameterizedType

/**
 * @Author : zhang
 * @Create Time : 2022/8/5
 * @Class Describe : 描述
 * @Project Name : Base
 */
abstract class BaseVBVMFragment<VB : ViewBinding, VM : ViewModel>(layoutId: Int = 0) :
    Fragment(layoutId) {

    lateinit var mViewModel: VM

    lateinit var mBinding: VB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mBinding = getViewBindingForFragment(layoutInflater, container)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = createViewModel()!!

        initView(savedInstanceState)

        initData()

        onClick()

        createObserver()
        NetworkManager.instance.mNetworkStateCallback.observe(this) {
            onNetworkStateChanged(it)
        }
    }

    open fun onNetworkStateChanged(netState: NetWorkState){}

    /**
     * 获取ViewModel
     */
    protected open fun createViewModel(): VM? {
        //这里获得到的是类的泛型的类型
        val type = javaClass.genericSuperclass
        if (type != null && type is ParameterizedType) {
            val actualTypeArguments = type.actualTypeArguments
            val tClass = actualTypeArguments[1]
            return ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
            )[tClass as Class<VM>]
        }
        return null
    }


    protected abstract fun initView(savedInstanceState: Bundle?)
    protected abstract fun initData()
    protected abstract fun onClick()
    protected abstract fun createObserver()
}