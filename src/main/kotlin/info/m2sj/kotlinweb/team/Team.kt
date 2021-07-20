package info.m2sj.kotlinweb.team

import info.m2sj.kotlinweb.member.Member
import javax.persistence.*

@Entity
@Table(name="team")
class Team(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column
    var teamName: String
)