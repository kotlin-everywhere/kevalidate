package org.kotlin.everywhere.kevalidate

import org.kotlin.everywhere.keresult.Ok
import kotlin.test.Test
import kotlin.test.assertEquals

class TestHelper {
    @Test
    fun testTrim() {
        assertEquals(
                Ok(TestModel(name = "John")),
                trim(TestModel::name) { copy(name = it) }(TestModel(name = " John "))
        )
    }
}

private data class TestModel(val name: String)