package info.m2sj.kotlinweb.member

import info.m2sj.kotlinweb.bloodpressure.BPEntity
import info.m2sj.kotlinweb.fps.FbsEntity
import info.m2sj.kotlinweb.team.Team
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name="member")
@DynamicUpdate //변경된 컬럼만 UPDATE 하기 위해서 선언
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column
    var name: String,
    @Column
    var age: Int,
    @Column
    var sex: String,
    @ManyToOne(fetch = FetchType.LAZY)   //N:1 이나 1:1 관계일 경우 DEFAULT FETCH JOIN 이 EAGER 이다 -> lAZY Specification 선언
    @JoinColumn(name = "team_id")
    var team: Team?,

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    var fps: MutableList<FbsEntity>,

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    var bps: MutableList<BPEntity>
)
