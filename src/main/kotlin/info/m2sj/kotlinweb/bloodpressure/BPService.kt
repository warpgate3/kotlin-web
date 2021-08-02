package info.m2sj.kotlinweb.bloodpressure

import info.m2sj.kotlinweb.member.MemberRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
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
            null, LocalDate.now(), bpDto.systolic,
            bpDto.diastolic, member = member
        )

        return bpRepository.save(bp)
    }

    fun listBpDto(param: BpSearchParamDto): List<BPDto> {
        return bpRepository.findByMemberBpSearchParamDto(id = param.id,
            startDate = param.startDate, endDate = param.endDate)
            .map { m ->
                m.member?.id?.let {
                    BPDto(it, m.systolic, m.diastolic)
                }
            }.requireNoNulls()
    }

    fun avgBpDto(id: Long): Pair<Int, Int> {
        return bpRepository.findByMemberId(id)
            .map {
                Pair(it.diastolic, it.systolic)
            }
            .reduce { acc, pair -> Pair((acc.first + pair.first) / 2, (acc.second + pair.second) / 2) }
    }
}