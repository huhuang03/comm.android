package com.dm.android.comm.util

import android.content.Context
import java.io.File

private fun _syncSingleFile(context: Context, fromPath: String, toFolder: String = "") {
    val dstFile = File(toFolder)
//    context.assets.openFd()
//    if (dstFile.exists() && )
}

/**
 * sync from assert/fromPath to toFolder
 */
fun syncAsset(context: Context, fromPath: String, toFolder: String = "") {
    var dstFolder = if (toFolder.isEmpty()) {
        File(context.filesDir, toFolder).absolutePath
    } else {
        toFolder
    }

    val list = context.assets.list(fromPath)
}