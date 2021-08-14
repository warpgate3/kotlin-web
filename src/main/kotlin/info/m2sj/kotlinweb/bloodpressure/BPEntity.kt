package info.m2sj.kotlinweb.bloodpressure

import info.m2sj.kotlinweb.member.Member
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name="blood_pressure")
class BPEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column(name="reg_date") var regDate: LocalDate,
    @Column(name="systolic") var systolic: Int,
    @Column(name="diastolic") var diastolic: Int,
    @ManyToOne(fetch = FetchType.LAZY)  @JoinColumn(name = "member_id") var member: Member?
)