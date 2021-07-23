package info.m2sj.kotlinweb.bloodpressure

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bp")
class BPController (
    var bpService: BPService
) {
    @PostMapping
    fun saveBp(@RequestBody bpDto: BPDto): BPEntity {
        return bpService.saveBloodPressure(bpDto)
    }
}