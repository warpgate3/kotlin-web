package info.m2sj.kotlinweb.tutorial

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, Long> {
    fun findByUsernameAndAgeGreaterThan(username: String, age: Int): MutableList<User>
}