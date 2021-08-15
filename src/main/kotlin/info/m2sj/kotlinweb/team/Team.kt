package info.m2sj.kotlinweb.team

import info.m2sj.kotlinweb.member.Member
import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Entity
@Table(name="team")
@Getter
@Setter
class Team(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column
    var teamName: String,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    var member: MutableList<Member> = mutableListOf()
)