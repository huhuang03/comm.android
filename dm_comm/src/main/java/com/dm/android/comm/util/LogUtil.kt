package com.dm.android.comm.util

import android.util.Log

private var qLogTag = ""

/**
 * 只应该在一个app中使用。不要在lib中使用。因为这个是全局只能有一个
 */
fun QLogSetTag(tag: String) {
    qLogTag = tag
}

fun qlogi(msg: String) {
    Log.i(qLogTag, msg)    
}

fun qlogerr(err: Throwable) {
    qlogi(Log.getStackTraceString(err))
}
