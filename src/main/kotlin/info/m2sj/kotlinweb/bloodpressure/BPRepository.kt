package info.m2sj.kotlinweb.bloodpressure

import org.springframework.data.jpa.repository.JpaRepository

interface BPRepository: JpaRepository<BPEntity, Long> {
    fun findByMemberId(id: Long): List<BPEntity>
}
