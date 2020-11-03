package com.dm.android.comm.ex

import java.io.InputStream
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Please take care that this will real all the data.
 */
fun InputStream.md5(): String {
    this.use {
        val mdEnc = MessageDigest.getInstance("MD5")

        val bytes = readBytes()
        mdEnc!!.update(bytes, 0, bytes.size)
        var md5: String = BigInteger(1, mdEnc.digest()).toString(16)
        while (md5.length < 32) {
            md5 = "0$md5"
        }
        return md5
    }
}
