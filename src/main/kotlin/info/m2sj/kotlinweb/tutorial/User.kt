package info.m2sj.kotlinweb.tutorial

import lombok.Getter
import lombok.Setter
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
@Getter
@Setter
class User(
    @Id @GeneratedValue  var id: Long?,
    var username: String
)