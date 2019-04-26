package org.kotlin.everywhere.kevalidate

import org.kotlin.everywhere.keresult.Err
import org.kotlin.everywhere.keresult.Ok

typealias Getter<T, U> = (T) -> U

fun <T, U> notEmpty(getter: Getter<T, String>, error: U): Validator<T, U> =
        { if (getter(it).isEmpty()) Err(listOf(error)) else Ok(it) }

fun <T, U> notBlank(getter: Getter<T, String>, error: U): Validator<T, U> =
        { if (getter(it).isBlank()) Err(listOf(error)) else Ok(it) }
