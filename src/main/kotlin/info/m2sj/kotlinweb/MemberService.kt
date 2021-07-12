package info.m2sj.kotlinweb

import org.springframework.stereotype.Service

interface MemberService {
    fun save(member: Member): Member
}

@Service
class MemberServiceImpl(
    private var memberRepository: MemberRepository
) : MemberService {
    override fun save(member: Member): Member {
       return memberRepository.save(member)
    }
}