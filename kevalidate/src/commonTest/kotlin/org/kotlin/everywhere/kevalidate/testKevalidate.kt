package org.kotlin.everywhere.kevalidate

import kotlin.test.Test
import kotlin.test.assertEquals

class TestKevalidate {
    @Test
    fun testAll() {
        // 이름은 공백이 될 수 없다.
        val dataValidator = all<TestValidateData, TestValidateError>(
                { if (it.name.isEmpty()) listOf(TestValidateError.EmptyName) else listOf() },
                { if (it.age < 18) listOf(TestValidateError.TooYoung) else listOf() }
        )

        // 이름이 비어있고, 나이가 18세 미만인 경우
        assertEquals(
                listOf(TestValidateError.EmptyName, TestValidateError.TooYoung),
                dataValidator(TestValidateData(name = "", age = 10))
        )

        // 이름이 있고, 나이가 18세 이상인 경우
        assertEquals(
                listOf(),
                dataValidator(TestValidateData(name = "john", age = 23))
        )
    }
    data class TestValidateData(val name: String, val age: Int)

    sealed class TestValidateError {
        object EmptyName : TestValidateError()
        object TooYoung : TestValidateError()
    }
}

