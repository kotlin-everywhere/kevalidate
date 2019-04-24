package org.kotlin.everywhere.kevalidate

typealias Validator<T, U> = (T) -> List<U>

fun <T, U> all(vararg validations: Validator<T, U>) = all(validations.asList())

fun <T, U> all(validations: Iterable<Validator<T, U>>): Validator<T, U> {
    return { model -> validations.flatMap { it(model) } }
}

