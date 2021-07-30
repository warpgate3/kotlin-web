package info.m2sj.kotlinweb.bloodpressure

import org.springframework.web.bind.annotation.*
import javax.persistence.Tuple

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
    fun listBp(@PathVariable id: Long): List<BPDto> {
        return bpService.listBpDto(id)
    }

    @GetMapping("/avg/{id}")
    fun avgBp(@PathVariable id: Long) {
        return bpService.avgBpDto(id)
    }
}