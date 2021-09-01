package info.m2sj.kotlinweb.tutorial

import lombok.Getter
import lombok.Setter
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.Query
import javax.persistence.*

@Entity
@Getter
@Setter
@NamedEntityGraph(name = "Member.all", attributeNodes = [NamedAttributeNode("group")])
class User(
    @Id @GeneratedValue @Column(name = "member_id")
    var id: Long?,

    var username: String,

    var age: Int,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "group_id")
    var group: Groups?
) {


    init {
        this.group?.let { it1 -> changeGroup(it1) }
    }
    constructor(username: String, age: Int, group: Groups): this(null, username, age, group)
    constructor(username: String, age: Int): this(null, username, age, null)
    constructor(username: String): this(null, username, 0, null)

    private fun changeGroup(group: Groups) {
        this.group = group
        group.users.add(this)
    }
}