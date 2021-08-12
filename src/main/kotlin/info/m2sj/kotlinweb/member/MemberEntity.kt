package info.m2sj.kotlinweb.member

import info.m2sj.kotlinweb.bloodpressure.BPEntity
import info.m2sj.kotlinweb.fps.FbsEntity
import info.m2sj.kotlinweb.team.Team
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name="member")
@DynamicUpdate
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
    @ManyToOne
    @JoinColumn(name = "team_id")
    var team: Team?,

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    var fps: List<FbsEntity>,

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    var bps: List<BPEntity>
)
