package info.m2sj.kotlinweb.fps

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface FbsRepository: JpaRepository<FbsEntity, Long> {

}
