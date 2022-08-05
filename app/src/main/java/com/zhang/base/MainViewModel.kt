package com.zhang.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @Author : zhang
 * @Create Time : 2022/8/5
 * @Class Describe : 描述
 * @Project Name : Base
 */
class MainViewModel : ViewModel() {

    private var mLiveData = MutableLiveData<String>()

    fun mLiveData(): MutableLiveData<String> {
        if (mLiveData.value == null) {
            mLiveData.postValue("0")
        }
        return mLiveData
    }
}