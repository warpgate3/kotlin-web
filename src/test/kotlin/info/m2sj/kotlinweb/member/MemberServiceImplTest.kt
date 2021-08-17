package info.m2sj.kotlinweb.member

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.annotation.Transactional
import javax.management.Query.eq

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
internal class MemberServiceImplTest(val memberService: MemberService,
                                     val memberOriginRepository: MemberOriginRepository,
                                     val memberRepository: MemberRepository
                                     ) {

    @Test
    @Transactional
    fun findByIdBatchFetchSizeTest() {
        val memList = memberService.findAll()
        memList.forEach { it ->
            it.bps.forEach { println(">>>> $it") }
        }
    }



    @Test
    fun findWithMemberOrigin() {
        val mem = memberOriginRepository.findMember(2)
        println("mem >>> $mem")
    }

    @Test
    fun findWithMembersOrigin() {
        val mem = memberOriginRepository.findMembers(2)
        println("mem >>> $mem")
    }


    @Test
    fun findWithMemberDtoOrigin() {
        val mem = memberOriginRepository.findMemberDto(2)
        println("mem >>> $mem")
    }

    @Test
    fun findById() {
        val mem = memberService.findById(2)

        assertThat(2L, `is`(equalTo(mem.id)))
        assertThat(0, `is`(lessThan(mem.bps.size)))

    }

    @Test
    fun findById2() {

    }
}