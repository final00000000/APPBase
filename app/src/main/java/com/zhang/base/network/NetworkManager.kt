package com.zhang.base.network

import androidx.lifecycle.MutableLiveData

/**
 * 描述　: 网络变化管理者
 */
class NetworkManager private constructor() {

    val mNetworkStateCallback = MutableLiveData<NetWorkState>()

    companion object {
        val instance: NetworkManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkManager()
        }
    }

}