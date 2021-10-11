package com.example.studyapplication.delegate

class Derived(b: Base) :Base by b

fun main(args: Array<String>) {
    val b = BaseImpl(10)
    Derived(b).print()
}