package info.m2sj.kotlinweb.member

import info.m2sj.kotlinweb.team.Team
import info.m2sj.kotlinweb.team.TeamRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface MemberService {
    fun save(member: MemberDto): Member
    fun findById(id: Long): Member
    fun deleteById(id: Long)
    fun update(m: Member): Member
}

@Service
class MemberServiceImpl(
    private var memberRepository: MemberRepository,
    private var teamRepository: TeamRepository
) : MemberService {
    override fun save(member: MemberDto): Member {
        val m = Member(age = member.age, name = member.name, sex = member.sex, team = null, bps = mutableListOf(), fps = mutableListOf())

        val t = Team(null, "fox-team")
        teamRepository.save(t)
        m.team = t
        return memberRepository.save(m)
    }

    @Transactional
    override fun findById(id: Long): Member {
        val mem = memberRepository.getById(id)

        val id = mem.team?.id
        println("id--->$id")
        mem.fps.forEach { entity -> println("fps id--->${entity.id}") }
        mem.bps.forEach { entity -> println("pbs id--->${entity.id}") }

        return mem
    }

    override fun deleteById(id: Long) {
        return memberRepository.deleteById(id)
    }

    override fun update(m: Member): Member {
        return memberRepository.save(m)
    }
}