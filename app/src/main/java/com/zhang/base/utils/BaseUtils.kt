package com.zhang.base.utils

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.zhang.base.BaseActivityCallbacks
import java.lang.reflect.ParameterizedType

/**
 * @Author : zhang
 * @Create Time : 2022/8/5
 * @Class Describe : 工具类
 * @Project Name : Base
 */
object BaseUtils {

    /** 默认的生命周期回调 */
    @JvmStatic
    var activityCallbacks: BaseActivityCallbacks = BaseActivityCallbacks()

    /**
     * 获取 StatusBar 高度
     * @return StatusBar 高度
     */
    val statusBarHeight: Int
        get() {
            try {
                val resources = Resources.getSystem()
                val id = resources.getIdentifier(
                    "status_bar_height", "dimen", "android"
                )
                if (id != 0) {
                    return resources.getDimensionPixelSize(id)
                }
            } catch (e: Exception) {
            }
            return 0
        }

    /** 获取状态栏高度  */
    fun getStatusBarHeight(context: Context): Int {
        var result = 24
        val resId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        result = if (resId > 0) {
            context.resources.getDimensionPixelSize(resId)
        } else {
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                result.toFloat(), Resources.getSystem().displayMetrics
            ).toInt()
        }
        return result
    }

    /**
     * Activity 通过反射获取 ViewBinding
     */
    fun <VB : ViewBinding, any : Any> any.getViewBindingForActivity(layoutInflater: LayoutInflater): VB {
        val type = javaClass.genericSuperclass as ParameterizedType
        val aClass = type.actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        return method.invoke(null, layoutInflater) as VB
    }

    /**
     * Fragment 通过反射获取 ViewBinding
     */
    @Suppress("UNCHECKED_CAST")
    fun <VB : ViewBinding, any : Any> any.getViewBindingForFragment(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): VB {
        val type = javaClass.genericSuperclass as ParameterizedType
        val aClass = type.actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        return method.invoke(null, layoutInflater, container, false) as VB
    }

    /**
     * 通过反射获取ViewModel
     */
    fun AppCompatActivity.getViewModel(): ViewModel = ViewModelProvider(
        this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    )[getVmClass(this)]

    /**
     * 获取当前类绑定的泛型ViewModel-clazz
     */
    fun <VM> getVmClass(obj: AppCompatActivity): VM {
        return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as VM
    }

    /**
     * check NetworkAvailable
     *
     * @param context
     * @return
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val manager = context.applicationContext.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager ?: return false
        val info = manager.activeNetworkInfo
        return null != info && info.isAvailable
    }
}