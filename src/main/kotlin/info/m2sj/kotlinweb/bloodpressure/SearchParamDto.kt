package info.m2sj.kotlinweb.bloodpressure

import org.springframework.format.annotation.DateTimeFormat
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalDateTime

data class BpSearchParamDto (
    val id: Long,
    @DateTimeFormat(pattern = "yyyyMMdd") val startDate: LocalDate?,
    @DateTimeFormat(pattern = "yyyyMMdd") val endDate: LocalDate?
)
