package com.zhang.base.network

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.content.IntentFilter
import android.database.Cursor
import android.net.ConnectivityManager
import android.net.Uri
import androidx.lifecycle.ProcessLifecycleOwner
import com.zhang.base.BaseActivityCallbacks

/**
 * 描述　:
 */

class Ktx : ContentProvider() {

    companion object {
        lateinit var app: Application
        private var mNetworkStateReceive: NetworkStateReceive? = null
        var watchActivityLife = true
        var watchAppLife = true
    }

    override fun onCreate(): Boolean {
        val application = context!!.applicationContext as Application
        install(application)
        return true
    }

    private fun install(application: Application) {
        app = application
        mNetworkStateReceive = NetworkStateReceive()
        app.registerReceiver(
            mNetworkStateReceive,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )

        if (watchActivityLife) application.registerActivityLifecycleCallbacks(BaseActivityCallbacks())
        if (watchAppLife) ProcessLifecycleOwner.get().lifecycle.addObserver(AppLifeObserver)
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? = null


    override fun update(
        uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?
    ): Int = 0

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun getType(uri: Uri): String? = null
}