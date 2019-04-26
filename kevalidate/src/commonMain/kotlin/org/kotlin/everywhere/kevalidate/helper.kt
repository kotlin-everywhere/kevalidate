package org.kotlin.everywhere.kevalidate

import org.kotlin.everywhere.keresult.Ok

fun <T> trim(getter: (T) -> String, setter: T.(String) -> T): Validator<T, Nothing> {
    return { model ->
        Ok(model.setter(getter(model).trim()))
    }
}
