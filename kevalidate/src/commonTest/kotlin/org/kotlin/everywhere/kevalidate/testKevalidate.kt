package org.kotlin.everywhere.kevalidate

import org.kotlin.everywhere.keresult.Err
import org.kotlin.everywhere.keresult.Ok
import kotlin.test.Test
import kotlin.test.assertEquals

class TestKevalidate {
    @Test
    fun testAll() {
        // 이름은 공백이 될 수 없다.
        val dataValidator = all<TestValidateData, TestValidateError>(
                { if (it.name.isEmpty()) Err(listOf(TestValidateError.EmptyName)) else Ok(it) },
                { if (it.age < 18) Err(listOf(TestValidateError.TooYoung)) else Ok(it) }
        )

        // 이름이 비어있고, 나이가 18세 미만인 경우
        assertEquals(
                Err(listOf(TestValidateError.EmptyName, TestValidateError.TooYoung)),
                dataValidator(TestValidateData(name = "", age = 10))
        )

        // 이름이 있고, 나이가 18세 이상인 경우
        assertEquals(
                Ok(TestValidateData(name = "john", age = 23)),
                dataValidator(TestValidateData(name = "john", age = 23))
        )
    }

    data class TestValidateData(val name: String, val age: Int)

    sealed class TestValidateError {
        object EmptyName : TestValidateError()
        object TooYoung : TestValidateError()
    }
}

