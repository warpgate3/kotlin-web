package info.m2sj.kotlinweb

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    var memberRepository: MemberRepository
) {

    @GetMapping("/")
    fun test(name: String): String {
        val m = Member(
            id = null, name = "rick", age = 40, sex = "M"
        )
        memberRepository.save(m);
        return name
    }
}
