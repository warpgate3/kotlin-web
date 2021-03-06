package info.m2sj.kotlinweb.tutorial

import lombok.Getter
import lombok.Setter
import lombok.ToString
import javax.persistence.*

@Entity
@Getter
@Setter
class Groups(
    @Id @GeneratedValue @Column(name = "group_id")
    var id: Long?,

    var name: String,

    @OneToMany(mappedBy = "group")
    var users: MutableList<User> = mutableListOf()
) {
    constructor(name: String): this(null, name)
}