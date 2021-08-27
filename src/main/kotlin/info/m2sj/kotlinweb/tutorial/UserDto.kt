package info.m2sj.kotlinweb.tutorial

data class UserDto(
    val id: Long?,
    val username: String?,
    val name: String?,
    val age: Int?,
) {
    constructor() : this(null, null, null, null)
}
