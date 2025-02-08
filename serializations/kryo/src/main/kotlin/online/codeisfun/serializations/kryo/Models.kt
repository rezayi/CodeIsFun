package online.codeisfun.serializations.kryo

enum class Gender {
    MALE, FEMALE
}

open class Person(
    open val name: String,
    open val age: Int,
    open val gender: Gender,
    open val father: Person?,
    open val mother: Person?,
)

open class Teacher(
    override val name: String,
    override val age: Int,
    override val gender: Gender,
    override val father: Person?,
    override val mother: Person?,
) : Person(
    name = name,
    age = age,
    gender = gender,
    father = father,
    mother = mother
)

data class Student(
    override val name: String,
    override val age: Int,
    override val gender: Gender,
    override val father: Person?,
    override val mother: Person?,
    val level: Int,
    val school: School,
    val teachers: Map<String, Teacher>
) : Person(
    name = name,
    age = age,
    gender = gender,
    father = father,
    mother = mother,
)

data class School(
    val name: String,
)
