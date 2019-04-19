package org.kotlin.everywhere.kevalidate

typealias Validator<T, U> = (T) -> List<U>

fun <T, U> all(vararg validations: Validator<T, U>) = all(validations.asList())

fun <T, U> all(validations: Iterable<Validator<T, U>>): Validator<T, U> {
    return { model -> validations.flatMap { it(model) } }
}

fun <T, U> first(vararg validations: Validator<T, U>) = first(validations.asList())

fun <T, U> first(validations: Iterable<Validator<T, U>>): Validator<T, U> {
    return { model -> validations.asSequence().map { it(model) }.firstOrNull { it.isNotEmpty() } ?: listOf() }
}
