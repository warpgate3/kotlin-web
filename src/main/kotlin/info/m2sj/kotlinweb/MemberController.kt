package info.m2sj.kotlinweb

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class   MemberController(
    var memberService: MemberService
) {

    @GetMapping("/")
    fun test(name: String): String {
        val m = Member(
            id = null, name = "rick", age = 39, sex = "M"
        )
        memberService.save(m)
        return name
    }
}
