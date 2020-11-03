package com.dm.android.comm.ex

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun File.md5(): String {
    if (!this.exists() || !this.canRead())  {
        return ""
    }

    return inputStream().md5()
}

