package com.dm.android.comm.ex

import android.os.Build
import android.webkit.WebSettings
import android.webkit.WebView

fun WebView.setCommSetting() {
    settings.javaScriptEnabled = true
    settings.apply {
        javaScriptEnabled = true
        javaScriptCanOpenWindowsAutomatically = true
        allowFileAccess = true
        layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        setSupportZoom(true)
        setSupportMultipleWindows(true)
        domStorageEnabled = true
        setGeolocationEnabled(true)
        setAppCacheMaxSize(java.lang.Long.MAX_VALUE)
        pluginState = WebSettings.PluginState.ON_DEMAND
        setAppCacheEnabled(true)
        setSupportZoom(false)
        displayZoomControls = false
        builtInZoomControls = false
        defaultTextEncodingName = "UTF-8"
        useWideViewPort = true
        loadWithOverviewMode = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
    }
}

