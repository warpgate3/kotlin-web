package info.m2sj.kotlinweb.tutorial

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class UserJpaRepository(@PersistenceContext val em: EntityManager) {
    fun save(user: User): User {
        em.persist(user)
        return user
    }

    fun find(id: Long): User {
        return em.find(User::class.java, id)
    }
}