package com.dm.android.comm.util

import android.content.Context
import android.util.Log
import com.dm.android.comm.ex.md5
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.security.DigestInputStream
import java.security.MessageDigest
import java.util.*
import kotlin.experimental.and

private fun convertHashToString(md5Bytes: ByteArray): String {
    var returnVal = ""
    for (i in md5Bytes.indices) {
        returnVal += ((md5Bytes[i] and 0xff.toByte()) + 0x100).toString(16).substring(1)
    }
    return returnVal.toUpperCase(Locale.ROOT)
}

private fun syncSingleFile(context: Context, assertPath: String, toFolder: String) {
    val fileName = File(assertPath).name
    val outFolder = File(toFolder)
    if (!outFolder.exists()) {
        if (!outFolder.mkdirs()) {
            return
        }
    }

    val fromMd5 = context.assets.open(assertPath).md5()

    var needCopy = true

    Log.i("tonghu", "fromMd5: $fromMd5");

    val toFile = File(toFolder, fileName)
    if (toFile.exists()) {
        Log.i("tonghu", "toFileMd5: " + toFile.md5());
    } else {
        Log.i("tonghu", "toFile not exist")
    }

    if (toFile.exists() && toFile.md5() == fromMd5 && fromMd5.isNotBlank()) {
        needCopy = false
    }


    if (needCopy) {
        context.assets.open(assertPath).copyTo(toFile.outputStream())
    }
}

/**
 * This is a sync method.
 * @param fromPath the subPath in assert folder
 */
fun syncAsset(context: Context, fromPath: String, toFolder: String = "") {
    // how can I check it's a folder or file?

    if (assetIsFile(context, fromPath)) {
        syncSingleFile(context, fromPath, toFolder)
    } else {

        val subFiles = context.assets.list(fromPath)
        subFiles?.forEach {
            val path = File(fromPath, it).path
            if (assetIsFile(context, path)) {
                syncSingleFile(context, path, toFolder)
            } else {
                val name = File(fromPath).name
                val newToFolder = File(toFolder, name).path
                syncAsset(context, path, newToFolder)
            }
        }
    }

}

fun assetIsFile(context: Context, path: String): Boolean {
    var `is`: InputStream? = null
    try {
        `is` = context.assets.open(path)
        return true
    } catch (ex: IOException) {
        // ignore
    } finally {
        `is`?.close()
    }
    return false
}