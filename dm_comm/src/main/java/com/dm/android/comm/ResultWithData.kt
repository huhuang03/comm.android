package com.dm.android.comm

class ResultWithData<T>(val result: Result, val data: T? = null) {
    fun success(): Boolean {
        return result.success()
    }

    fun requireSuccess(errMsg: String = ""): ResultWithData<T> {
        result.requireSuccess(errMsg) 
        return this
    }

    fun requireSuccess(lazyMessage: (result: Result) -> String): ResultWithData<T> {
        result.requireSuccess(lazyMessage)
        return this
    }

    fun changeErrMsg(errMsg: String): ResultWithData<T> {
        result.changeErrMsg(errMsg)
        return this
    }
}