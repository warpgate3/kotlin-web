package info.m2sj.kotlinweb.bloodpressure

import com.fasterxml.jackson.annotation.JsonIgnore
import info.m2sj.kotlinweb.member.Member
import lombok.Setter
import lombok.Getter
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name="blood_pressure")
@Getter
@Setter
class BPEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column(name="reg_date") var regDate: LocalDate,
    @Column(name="systolic") var systolic: Int,
    @Column(name="diastolic") var diastolic: Int,

    @JsonIgnore @ManyToOne(fetch = FetchType.LAZY)  @JoinColumn(name = "member_id") var member: Member?
)