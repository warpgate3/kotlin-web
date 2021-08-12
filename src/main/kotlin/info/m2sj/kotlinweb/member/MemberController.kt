package info.m2sj.kotlinweb.member

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/member")
class MemberController(
    var memberService: MemberService
) {
    @PostMapping("")
    fun saveMember(@RequestBody m: MemberDto): Member {
        return memberService.save(m)
    }

    @GetMapping("/{id}")
    fun getMember(@PathVariable id: Long): MemberDto {
        val mem = memberService.findById(id)

        return MemberDto(mem.name, mem.age, mem.sex)
    }

    @DeleteMapping("/{id}")
    fun deleteMember(@PathVariable id: Long) {
        return memberService.deleteById(id)
    }

    @PutMapping("")
    fun updateMember(@RequestBody m: Member): Member {
        return memberService.update(m)
    }
}
