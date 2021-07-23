package info.m2sj.kotlinweb.bloodpressure

import info.m2sj.kotlinweb.member.MemberRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class BPService(
    var bpRepository: BPRepository,
    var memberRepository: MemberRepository
) {
    fun saveBloodPressure(bpDto: BPDto): BPEntity {
        bpDto.memberId
        val member = memberRepository.findById(bpDto.memberId)
            .orElseThrow { throw RuntimeException("No exist member") }

        val bp = BPEntity(
            null, LocalDateTime.now(), bpDto.systolic,
            bpDto.diastolic, member = member)

        return bpRepository.save(bp)
    }
}