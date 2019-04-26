package org.kotlin.everywhere.kevalidate

import org.kotlin.everywhere.keresult.Err
import org.kotlin.everywhere.keresult.Ok
import org.kotlin.everywhere.keresult.Result

typealias Validator<T, E> = (T) -> Result<List<E>, T>

fun <T, E> all(vararg validators: Validator<T, E>) = all(validators.asList())

fun <T, E> all(validators: Iterable<Validator<T, E>>): Validator<T, E> {
    return { model ->
        all(model, validators.iterator(), listOf())
    }
}

private tailrec fun <T, U> all(model: T, validatorIterator: Iterator<Validator<T, U>>, errors: List<U>): Result<List<U>, T> {
    return if (!validatorIterator.hasNext()) {
        if (errors.isEmpty()) {
            Ok(model)
        } else {
            Err(errors)
        }
    } else {
        when (val result = (validatorIterator.next())(model)) {
            is Ok -> all(result.value, validatorIterator, errors)
            is Err -> all(model, validatorIterator, errors + result.error)
        }
    }
}

