package com.dm.android.comm

data class Result(val code: Int = 0, var msg: String = "success") {

    companion object {
        fun error(msg: String, errCode: Int = 1): Result {
            require(errCode != 0) { "0 means success" }
            return Result(errCode, msg)
        }

        fun check(rst: Boolean, msg: String, errCode: Int = -1): Result {
            return if (rst) {
                Result()
            } else {
                error(msg, errCode)
            }
        }
    }

    fun success(): Boolean {
        return code == 0
    }

    fun requireSuccess(errMsg: String = ""): Result {
        require(success()) { if (errMsg.isNotBlank()) errMsg else msg }
        return this
    }

    fun requireSuccess(lazyMessage: (result: Result) -> String): Result {
        val errMsg = lazyMessage(this)
        require(success()) {if (errMsg.isNotBlank()) errMsg else msg}
        return this
    }

    fun changeErrMsg(errMsg: String): Result {
        if (!success()) {
            msg = errMsg
        }
        return this
    }

    constructor(success: Boolean, errMsg: String, rightMsg: String = ""): this(if (success) 0 else 1, if (success) rightMsg else errMsg)
}