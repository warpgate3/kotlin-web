package info.m2sj.kotlinweb.bloodpressure

import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.LocalDateTime

@RestController
@RequestMapping("/bp")
class BPController (
    var bpService: BPService
) {
    @PostMapping
    fun saveBp(@RequestBody bpDto: BPDto): BPEntity {
        return bpService.saveBloodPressure(bpDto)
    }

    @GetMapping("/{id}")
    fun listBp(@PathVariable id: Long,
               searchParam: BpSearchParamDto
    ): List<BPDto> {
        return bpService.listBpDto(searchParam)
    }

    @GetMapping("/avg/{id}")
    fun avgBp(@PathVariable id: Long): Pair<Int, Int> {
        return bpService.avgBpDto(id)
    }
}