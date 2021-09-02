package info.m2sj.kotlinweb.tutorial

import lombok.RequiredArgsConstructor
import javax.persistence.EntityManager

@RequiredArgsConstructor
class UserRepositoryCustomImpl(val em: EntityManager): UserRepositoryCustom {
    override fun findUserCustom(): MutableList<User> {
        return em.createQuery("select u from User u", User::class.java)
            .resultList
    }
}