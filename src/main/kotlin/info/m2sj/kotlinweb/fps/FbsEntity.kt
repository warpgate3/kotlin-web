package info.m2sj.kotlinweb.fps

import com.fasterxml.jackson.annotation.JsonIgnore
import info.m2sj.kotlinweb.member.Member
import lombok.Getter
import lombok.Setter
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name="fasting_blood_sugar")
@Getter
@Setter
class FbsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column(name="reg_date") var regDate: LocalDate,
    @Column(name="value") var bloodSugar: Float,
    @JsonIgnore  @ManyToOne(fetch = FetchType.EAGER)  @JoinColumn(name = "member_id") var member: Member?
)