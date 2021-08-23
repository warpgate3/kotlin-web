package info.m2sj.kotlinweb.tutorial

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class GroupJpaRepository(@PersistenceContext val em: EntityManager) {
    fun save(groups: Groups): Groups {
        em.persist(groups)
        return groups
    }

    fun delete(groups: Groups) {
        em.remove(groups)
    }

    fun findAll(): List<Groups> {
        return em.createQuery("select g from Groups g", Groups::class.java)
            .resultList
    }

    fun findById(id: Long): Groups? {
        return em.find(Groups::class.java, id)
    }

    fun count(): Long {
        return em.createQuery("select count(g) from Groups g", Long::class.java)
            .singleResult
    }
}