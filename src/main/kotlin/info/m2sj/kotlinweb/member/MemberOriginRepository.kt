package info.m2sj.kotlinweb.member

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class MemberOriginRepository(val em: EntityManager) {
    fun findMember(memberId: Long) : Member {
        return em.createQuery(
            """
           select m from User m
            join fetch m.bps
            join fetch m.group where m.id =?1
        """, Member::class.java).setParameter(1, memberId)
            .singleResult
    }

    fun findMembers(memberId: Long) : List<Member> {
        //distinct는 1:N 관계에서 fetch join을 할경우 N의 갯수만큼 리턴하는것을 방지한다.
        return em.createQuery(
            """
           select distinct m from User m   
            join fetch m.bps
            join fetch m.group where m.id =?1
        """, Member::class.java).setParameter(1, memberId)
            .resultList
    }


    fun findMemberDto(memberId: Long) : MemberDto {
        return em.createQuery(
            """
            select new info.m2sj.kotlinweb.member.MemberDto(m.name, m.age, m.sex) from User m
            where m.id = ?1
        """, MemberDto::class.java).setParameter(1, memberId)
            .singleResult;
    }
}
