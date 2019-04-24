package org.kotlin.everywhere.kevalidate

typealias Getter<T, U> = (T) -> U

fun <T, U> notEmpty(getter: Getter<T, String>, error: U): Validator<T, U> =
        { if (getter(it).isEmpty()) listOf(error) else listOf() }