package info.m2sj.kotlinweb

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/member")
class   MemberController(
    var memberService: MemberService
) {

    @PostMapping("")
    fun saveMember(@RequestBody m: Member): Member {
        return memberService.save(m)
    }

    @GetMapping("/{id}")
    fun getMember(@PathVariable id:  Long): Member {
        return memberService.findById(id)
    }

    @DeleteMapping("/{id}")
    fun deleteMember(@PathVariable id:  Long) {
        return memberService.deleteById(id)
    }

    @PutMapping("")
    fun updateMember(@RequestBody m: Member): Member {
        return memberService.update(m)
    }
}
