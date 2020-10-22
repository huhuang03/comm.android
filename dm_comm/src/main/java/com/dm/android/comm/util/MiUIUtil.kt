package com.dm.android.comm.util

import android.annotation.SuppressLint
import android.app.AppOpsManager
import android.content.Context
import android.os.Build
import android.os.Process
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

fun isMIUI(): Boolean {
    return "Xiaomi".equals(Build.BRAND, ignoreCase = true) || "Redmi".equals(Build.BRAND, ignoreCase = true)
}

fun getMIUIVersionCode(): Int {
    try {
        @SuppressLint("PrivateApi") val propertyClass =
            Class.forName("android.os.SystemProperties")
        val method: Method = propertyClass.getMethod("get", String::class.java)
        val versionCode =
            method.invoke(propertyClass, "ro.miui.ui.version.code") as String
        return versionCode.toInt()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return -1
}

fun hasPermissionBgOpen(context: Context): Boolean {
    if (!isMIUI()) return true
    var rst = false
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        val ops = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        try {
            val op = 10021 // >= 23
            val method = ops.javaClass.getMethod(
                "checkOpNoThrow",
                Int::class.javaPrimitiveType!!, Int::class.javaPrimitiveType!!, String::class.java
            )
            val result = method.invoke(ops, op, Process.myUid(), context.packageName) as Int
            rst = result == AppOpsManager.MODE_ALLOWED
        } catch (e: InvocationTargetException) {
            if (isMIUI()) {
                val vCode = getMIUIVersionCode()
                /**
                 * miui 7
                 */
                if (vCode in 0..5) {
                    rst = true
                }
            }
        }
        catch (e: Exception) {
            rst = false
        }
    } else {
        rst = true
    }

    return rst
}
