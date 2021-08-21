package info.m2sj.kotlinweb.tutorial

import lombok.Getter
import lombok.Setter
import lombok.ToString
import javax.persistence.*

@Entity
@Getter
@Setter
class Group(
    @Id @GeneratedValue @Column(name = "member_id")  var id: Long?,
    var name: String,

    @OneToMany(mappedBy = "group")
    var users: MutableList<User> = mutableListOf()
)