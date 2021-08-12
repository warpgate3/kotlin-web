package info.m2sj.kotlinweb.member

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import javax.management.Query.eq

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
internal class MemberServiceImplTest(val memberService: MemberService) {

    @Test
    fun findById() {
        val mem = memberService.findById(2)

        assertThat(2L, `is`(equalTo(mem.id)))
        assertThat(0, `is`(lessThan(mem.bps.size)))

    }
}