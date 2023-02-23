package com.jzyc.instock.bean

class NewBeanResponses {
    var stat = 0
    var data = ArrayList<NewBean>()
    var page = 0
    var pageSize = 0

    override fun toString(): String {
        return "NewBeanResponses(stat=$stat, data=$data, page=$page, pageSize=$pageSize)"
    }

}