package info.m2sj.kotlinweb.fps

import info.m2sj.kotlinweb.member.MemberService
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class FbsService(
    var fbsRepository: FbsRepository,
    var memberService: MemberService
) {

    fun save(fbsDto: FbsDto): FbsEntity {
        val member = fbsDto.memberId?.let {
            memberService.findById(it)
        }
        val f = FbsEntity(null, LocalDate.now(), fbsDto.bloodSugar, member)
        return fbsRepository.save(f)
    }
}