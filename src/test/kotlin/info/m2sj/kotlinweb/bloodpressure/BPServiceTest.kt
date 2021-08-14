package info.m2sj.kotlinweb.bloodpressure

import info.m2sj.kotlinweb.member.MemberService
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.lessThan
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
internal class BPServiceTest(val bpService: BPService, val memberService: MemberService) {
    @Test
    fun listBpDto() {
        val list = bpService.listBp(2)

        //hamcrest
        assertThat(0, `is`(lessThan(list.size)))

        //AssertJ
        org.assertj.core.api.Assertions.assertThat(0).isLessThan(list.size)
    }
}