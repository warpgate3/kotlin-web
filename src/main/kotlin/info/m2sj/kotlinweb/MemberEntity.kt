package info.m2sj.kotlinweb

import javax.persistence.*

@Entity
@Table(name="member")
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column
    var name: String,
    @Column
    var age: Int,
    @Column
    var sex: String
)
