package com.eduhive.Edu_Hive.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "customer")
class CustomerEntity(
    @Id
    @Column(name = "id")
    val id: Long?,

    @Column(name = "name")
    val name: String,

    @Column(name = "email")
    val email: String,


    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    val createdDate: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_date")
    val updatedDate: LocalDateTime? = null,

    @ManyToMany
    @JoinTable(
        name = "customer_course",
        joinColumns = [JoinColumn(name = "customer_id")],
        inverseJoinColumns = [JoinColumn(name = "course_id")]
    )
    val courses: MutableList<CourseEntity> = mutableListOf()
){
    fun addCourse(course: CourseEntity) {
        if (!this.courses.contains(course)) {
            this.courses.add(course)
            course.customers.add(this)
        }
    }
}