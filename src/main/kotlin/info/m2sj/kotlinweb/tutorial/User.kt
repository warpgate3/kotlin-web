package info.m2sj.kotlinweb.tutorial

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Entity
@Getter
@Setter
class User(
    @Id @GeneratedValue @Column(name = "member_id")  var id: Long?,
    var username: String,
    var age: Int,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "group_id")
    var group: Group
) {
    init {
        changeGroup(this.group)
    }
//    constructor(id: Long?, username: String, age: Int, group: Group): this(id, username, age) {
//        changeTeam(group)
//    }
    private fun changeGroup(group: Group) {
        this.group = group
        group.users.add(this)
    }
}