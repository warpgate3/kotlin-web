package info.m2sj.kotlinweb

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
    var sex: String
)
