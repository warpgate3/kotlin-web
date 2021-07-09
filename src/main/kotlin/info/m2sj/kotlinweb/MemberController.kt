package info.m2sj.kotlinweb

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller

class MemberController {
    @GetMapping("/")
    fun test(name: String): String {
        return name
    }
}