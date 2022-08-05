package com.zhang.base

import android.app.Application
import com.zhang.base.utils.BaseUtils

/**
 * @Author : zhang
 * @Create Time : 2022/8/5
 * @Project Name : Base
 * @Class Describe : 描述
 */
class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(BaseUtils.activityCallbacks)
    }

}