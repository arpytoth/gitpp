package com.toth.gitpp

import com.toth.gitpp.model.CommitParser
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class CommitParserTest {

    @Test fun parseDateTest() {
        val parser = CommitParser()
        val date = parser.parseTime("   Tue Jun 13 13:47:06 2017 -0700")
        var calendar = Calendar.getInstance()
        calendar.time = date;

        assertEquals(13, calendar[Calendar.DAY_OF_MONTH])
        assertEquals(5, calendar[Calendar.MONTH])
        assertEquals(2017, calendar[Calendar.YEAR])
        assertEquals(6, calendar[Calendar.SECOND])
        assertEquals(47, calendar[Calendar.MINUTE])
        assertEquals(13, calendar[Calendar.HOUR_OF_DAY])
    }

}