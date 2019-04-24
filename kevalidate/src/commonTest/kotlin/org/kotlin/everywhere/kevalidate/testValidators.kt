package org.kotlin.everywhere.kevalidate

import kotlin.test.Test
import kotlin.test.assertEquals

class TestValidators {
    @Test
    fun testNotEmpty() {
        val dataValidator = all<TestValidateData, TestValidateError>(
                notEmpty(TestValidateData::name, EmptyName)
        )

        // 비어있는 string 일 경우 오류이다.
        assertEquals(
                listOf(EmptyName),
                dataValidator(TestValidateData(name = "", age = 10))
        )

        assertEquals(
                listOf(),
                dataValidator(TestValidateData(name = "john", age = 10))
        )
    }


}

private data class TestValidateData(val name: String, val age: Int)

private sealed class TestValidateError
private object EmptyName : TestValidateError()
