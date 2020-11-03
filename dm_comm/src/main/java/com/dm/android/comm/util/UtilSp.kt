package com.dm.android.comm.util

import android.content.Context

const val SP_DEFAULT_FILE = "comm_config"

fun spSaveStr(context: Context, key: String, value: String, spFileName: String = SP_DEFAULT_FILE) {
    context.getSharedPreferences(spFileName, Context.MODE_PRIVATE)
        .edit().putString(key, value).apply()
}

fun spGetStr(context: Context, key: String, defValue: String = "", spName: String = SP_DEFAULT_FILE): String {
    return context.getSharedPreferences(spName, Context.MODE_PRIVATE)
        .getString(key, defValue)?: ""
}

fun spSaveLong(context: Context, key: String, value: Long, spFileName: String = SP_DEFAULT_FILE) {
    context.getSharedPreferences(spFileName, Context.MODE_PRIVATE)
        .edit().putLong(key, value).apply()
}

fun spGetLong(context: Context, key: String, defValue: Long = 0, spName: String = SP_DEFAULT_FILE): Long {
    return context.getSharedPreferences(spName, Context.MODE_PRIVATE)
        .getLong(key, defValue)
}
