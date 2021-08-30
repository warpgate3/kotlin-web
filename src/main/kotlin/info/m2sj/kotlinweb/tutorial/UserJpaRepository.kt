package info.m2sj.kotlinweb.tutorial

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class UserJpaRepository(@PersistenceContext val em: EntityManager) {

    fun findByPage(age:Int, offset:Int, limit:Int): MutableList<User> {
        return em.createQuery("select u from User u where u.age = :age order by u.username desc",
            User::class.java)
            .setParameter("age", age)
            .setFirstResult(offset)
            .setMaxResults(limit)
            .resultList
    }

    fun totalCount(age: Int): Long {
        return em.createQuery("select count(u) from User u where u.age = :age", Long::class.java)
            .setParameter("age", age)
            .singleResult
    }

    fun findByUsernameAndAgeGreaterThan(name: String, age: Int): MutableList<User> {
        return em.createQuery("select m from User m where m.username = :username and m.age > :age"
            , User::class.java)
            .setParameter("username", name)
            .setParameter("age", age)
            .resultList
    }

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
        return em.createQuery("select count(u) from User u", Long::class.javaObjectType) //unboxed type 을 위한 선언
            .singleResult
    }

    fun find(id:Long): User {
        return em.find(User::class.java, id)
    }
}