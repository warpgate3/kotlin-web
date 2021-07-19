package info.m2sj.kotlinweb

import org.springframework.stereotype.Service

interface MemberService {
    fun save(member: Member): Member
    fun findById(id: Long): Member
    fun deleteById(id: Long)
    fun update(m: Member): Member
}

@Service
class MemberServiceImpl(
    private var memberRepository: MemberRepository
) : MemberService {
    override fun save(member: Member): Member {
       return memberRepository.save(member)
    }

    override fun findById(id: Long): Member {
        return memberRepository.getById(id)
    }

    override fun deleteById(id: Long) {
        return memberRepository.deleteById(id)
    }

    override fun update(m: Member): Member {
        return memberRepository.save(m)
    }
}