package info.m2sj.kotlinweb.bloodpressure

import info.m2sj.kotlinweb.member.MemberDto
import info.m2sj.kotlinweb.member.MemberService
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import java.time.LocalDate

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
internal class BPServiceTest(val bpService: BPService, val memberService: MemberService) {
    @Test
    fun listBpDto() {
        var mem = memberService.save(MemberDto("rick", 40, "M"));
        val bpdto = bpService.saveBloodPressure(BPDto(mem.id, 80, 120))
        val param = BpSearchParamDto(bpdto.id, LocalDate.now(), LocalDate.now())

        bpService.avgBpDto(param)
    }
}