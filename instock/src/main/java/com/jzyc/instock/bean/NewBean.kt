package com.jzyc.instock.bean

class NewBean {
    var uniquekey = ""
    var title = ""
    var date = ""
    var category = ""
    var author_name = ""
    var url = ""
    var thumbnail_pic_s = ""
    var thumbnail_pic_s02 = ""
    var thumbnail_pic_s03 = ""
    var is_content = ""

    override fun toString(): String {
        return "NewBean(uniquekey='$uniquekey', title='$title', date='$date', category='$category', author_name='$author_name', url='$url', thumbnail_pic_s='$thumbnail_pic_s', thumbnail_pic_s02='$thumbnail_pic_s02', thumbnail_pic_s03='$thumbnail_pic_s03', is_content='$is_content')"
    }


}