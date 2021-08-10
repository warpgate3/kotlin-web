package info.m2sj.kotlinweb.fps

import info.m2sj.kotlinweb.member.Member
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name="fasting_blood_sugar")
class FbsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column(name="reg_date") var regDate: LocalDate,
    @Column(name="value") var bloodSugar: Float,
    @ManyToOne  @JoinColumn(name = "member_id") var member: Member?
)