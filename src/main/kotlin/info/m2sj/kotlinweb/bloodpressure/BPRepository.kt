package info.m2sj.kotlinweb.bloodpressure

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface BPRepository: JpaRepository<BPEntity, Long> {
    @Query("select b from BPEntity b where b.member.id = :id and b.regDate between :startDate and :endDate")
    fun findByMemberBpSearchParamDto(id:Long, startDate:LocalDate = LocalDate.of(2005, 11, 1),
                                     endDate: LocalDate = LocalDate.now()): List<BPEntity>

}
