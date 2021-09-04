package info.m2sj.kotlinweb

import info.m2sj.kotlinweb.tutorial.Groups
import lombok.Getter
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

@MappedSuperclass
open class JpaBaseEntity(
    @Column(updatable = false) open var createdDate: LocalDateTime? = null,
    open var updatedDate: LocalDateTime? = null

) {
    constructor(): this(null, null)

    @PrePersist
    fun prePersist() {
        var now = LocalDateTime.now()
        createdDate = now
        updatedDate = now
    }

    @PreUpdate
    fun preUpdate() {
        updatedDate = LocalDateTime.now()
    }
}