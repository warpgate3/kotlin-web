package info.m2sj.kotlinweb.bloodpressure

import info.m2sj.kotlinweb.member.MemberRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class BPService(
    var bpRepository: BPRepository,
    var memberRepository: MemberRepository
) {
    fun saveBloodPressure(bpDto: BPDto): BPEntity {
        bpDto.memberId
        val member = bpDto.memberId?.let {
            memberRepository.findById(it)
                .orElseThrow { throw RuntimeException("No exist member") }
        }

        val bp = BPEntity(
            null, LocalDate.now(), bpDto.systolic,
            bpDto.diastolic, member = member
        )

        return bpRepository.save(bp)
    }

    fun listBpDto(param: BpSearchParamDto): List<BPDto> {
        val startDate = param.startDate?:LocalDate.of(2005,1,1)
        val endDate = param.endDate?: LocalDate.now()
        return bpRepository.findByMemberBpSearchParamDto(id = param.id,
            startDate = startDate, endDate = endDate)
            .map { m ->
                m.member?.id?.let {
                    BPDto(it, m.systolic, m.diastolic)
                }
            }.requireNoNulls()
    }

    fun avgBpDto(param: BpSearchParamDto): Pair<Int, Int> {
        val startDate = param.startDate?:LocalDate.of(2005,1,1)
        val endDate = param.endDate?: LocalDate.now()
        return bpRepository.findByMemberBpSearchParamDto(id = param.id,
            startDate = startDate, endDate = endDate
        )
            .map {
                Pair(it.diastolic, it.systolic)
            }
            .reduce { acc, pair -> Pair((acc.first + pair.first) / 2, (acc.second + pair.second) / 2) }
    }

    fun updateBpList(bpList: List<BPDto>): List<BPDto> {
        bpList.forEach {
            it.memberId
        }
        return listOf()
    }
}