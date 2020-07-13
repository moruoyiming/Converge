package com.example.hotfix.note.class10

import java.io.*

data class Course(var name: String, var score: Float) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 1L
    }
}

private fun test1() {
    var course = Course("jianruilin", 100f)

    var out = ByteArrayOutputStream()

    var oos = ObjectOutputStream(out)

    println("序列化前$course")

    oos.writeObject(course)

    var bs = out.toByteArray()

    oos.close()

    var ois = ObjectInputStream(ByteArrayInputStream(bs))

    course = ois.readObject() as Course

    println("序列化后$course")
}

fun main() {
    test1()
}