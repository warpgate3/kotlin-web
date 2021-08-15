package info.m2sj.kotlinweb.member

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class MemberOriginRepository(val em: EntityManager) {
    fun findMember(memberId: Long) : Member {
        return em.createQuery("""
           select m from Member m
            join fetch m.team where m.id =?1
        """, Member::class.java).setParameter(1, memberId)
            .singleResult
    }

}
