package com.dm.android.comm.util

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun File.md5(): String {
    if (!this.exists() || !this.canRead())  {
        return ""
    }
    var mdEnc: MessageDigest? = null
    try {
        mdEnc = MessageDigest.getInstance("MD5")
    } catch (e: NoSuchAlgorithmException) {
        println("Exception while encrypting to md5")
        e.printStackTrace()
        return ""
    } // Encryption algorithm


    val bytes = readBytes()
    mdEnc!!.update(bytes, 0, bytes.size)
    var md5: String = BigInteger(1, mdEnc.digest()).toString(16)
    while (md5.length < 32) {
        md5 = "0$md5"
    }
    return md5
}