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
//    println(ObjectMapper().writeValueAsString(log))

    var enum = Direction.valueOf("NORTH")
//    println(enum)

    var x : Int = 2
    when(x) {
        2 -> println("This is 2")
        3,4,5,6,7,8 -> println("When x is any number from 3,4,5,6,7,8")
        in 9..15 -> println("When x is something from 9 to 15")
        //if you want to perform some action
        in 20..25 -> {
            val action = "Perform some action"
            println(action)
        }
        else -> println("When x does not belong to any of the above case")
    }

    val map = mapOf("a" to 1, "b" to 2, "c" to 3)
    for ((k, v) in map) {
        println("$k -> $v")
    }
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

