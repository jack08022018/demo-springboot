package com.demo.common.kotlin

import com.fasterxml.jackson.databind.ObjectMapper

open class KotlinDemo {

}
fun main() {
//    val b: String? = null
//    println(b?.length)

    var test = Test(name = "king")
    var log = Constructor("data log")
//    test.stringRepresentation = "asd"

//    println(ObjectMapper().writeValueAsString(test))
    println(ObjectMapper().writeValueAsString(log))

    var enum = Direction.valueOf("NORTH")
    println(enum)
}
class Test(
    val name: String? = null,
) {
    var stringRepresentation: String? = null
        get() = field
        set(value) {field = value}
}
class Constructor {
    var data: String = ""
    var numberOfData = 0
    constructor(_data: String) {
        data = _data
    }
    constructor(_data: String, _numberOfData: Int) {
        data = _data
        numberOfData = _numberOfData
        println("$data: $numberOfData times")
    }
}
enum class Direction(val description : String) {
    NORTH(description = "North"),
    SOUTH(description = "South");

    companion object {
        @JvmStatic
        fun fromInt(code : String): Direction =
            values().find { value -> value.toString() == code } ?: throw IllegalArgumentException("No matching Direction for [${code}]")
    }
}

