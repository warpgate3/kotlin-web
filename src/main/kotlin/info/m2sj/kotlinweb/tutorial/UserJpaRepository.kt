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

    fun delete(user: User) {
        em.remove(user)
    }

    fun findAll(): List<User> {
        return em.createQuery("select u from User u", User::class.java)
            .resultList
    }

    fun findById(id: Long): User? {
        return em.find(User::class.java, id)
    }

    fun count(): Long {
        return em.createQuery("select count(u) from User u", Long::class.javaObjectType)
            .singleResult
    }

    fun find(id:Long): User {
        return em.find(User::class.java, id)
    }
}