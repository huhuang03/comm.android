package com.dm.android.comm.util

import android.content.Context
import java.io.File
import java.io.IOException
import java.io.InputStream

private fun _syncSingleFile(context: Context, fromPath: String, toFolder: String = "") {
    val dstFile = File(toFolder)
//    context.assets.openFd()
//    if (dstFile.exists() && )
}


/**
 * @param fromPath the subPath in assert folder
 */
fun syncAsset(context: Context, fromPath: String, toFolder: String = "") {
    // how can I check it's a folder or file?

    if (assetIsFile(context, fromPath)) {
        val inputStream = context.assets.open(fromPath)
        inputStream.use {
            val toFile = File(toFolder, fromPath)
//            val needCopy = !toFile.exists() || (toFile.md5() != )
        }
    }

//    context.assets.open()
//    var dstFolder = if (toFolder.isEmpty()) {
//        File(context.filesDir, toFolder).absolutePath
//    } else {
//        toFolder
//    }
//
//    val list = context.assets.list(fromPath)

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