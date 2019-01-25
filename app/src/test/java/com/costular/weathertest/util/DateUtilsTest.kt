package com.costular.weathertest.util

import org.assertj.core.api.Assertions
import org.joda.time.DateTime
import org.junit.Test

class DateUtilsTest {

    @Test
    fun `test date formatting`() {
        // Given
        val now = DateTime(2019, 1, 24, 9, 0, 0)

        // When
        val dateFormatted = DateUtils.formatDate(now)

        // Then
        Assertions.assertThat(dateFormatted).isEqualTo("24 Jan")
    }

    @Test
    fun `test time formatting`() {
        // Given
        val now = DateTime(2019, 1, 24, 9, 0, 0)

        // When
        val timeFormatted = DateUtils.formatTime(now)

        // Then
        Assertions.assertThat(timeFormatted).isEqualTo("09:00")
    }

}