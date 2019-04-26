package org.kotlin.everywhere.kevalidate

import org.kotlin.everywhere.keresult.Err
import org.kotlin.everywhere.keresult.Ok
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
                Err(listOf(EmptyName)),
                dataValidator(TestValidateData(name = "", age = 10))
        )

        // 트림을 사용하지 않는다.
        assertEquals(
                Ok(TestValidateData(name = " ", age = 10)),
                dataValidator(TestValidateData(name = " ", age = 10))
        )

        assertEquals(
                Ok(TestValidateData(name = "john", age = 10)),
                dataValidator(TestValidateData(name = "john", age = 10))
        )
    }

    @Test
    fun testNotBlank() {
        val dataValidator = all<TestValidateData, TestValidateError>(
                notBlank(TestValidateData::name, EmptyName)
        )

        // 비어있는 string 일 경우 오류이다.
        assertEquals(
                Err(listOf(EmptyName)),
                dataValidator(TestValidateData(name = "", age = 10))
        )

        // Trim 후 비어있는지 확인한다.
        assertEquals(
                Err(listOf(EmptyName)),
                dataValidator(TestValidateData(name = " ", age = 10))
        )

        assertEquals(
                Ok(TestValidateData(name = "john", age = 10)),
                dataValidator(TestValidateData(name = "john", age = 10))
        )
    }
}

private data class TestValidateData(val name: String, val age: Int)

private sealed class TestValidateError
private object EmptyName : TestValidateError()
