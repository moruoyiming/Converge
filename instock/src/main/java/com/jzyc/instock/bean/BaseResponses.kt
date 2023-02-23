package com.jzyc.instock.bean

public open class BaseResponses<T> {
    var reason = ""
    var error_code = 0
    var result: T? = null
}