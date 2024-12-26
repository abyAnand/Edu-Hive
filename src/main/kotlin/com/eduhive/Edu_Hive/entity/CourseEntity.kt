package com.eduhive.Edu_Hive.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime


@Entity
@Table(name = "course")
class CourseEntity(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?,

    @Column(name = "title")
    val title: String,

    @Column(name = "description")
    val description: String,

    @Column(name = "price")
    val price: Double,

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    val creator: CreatorEntity,

    @ManyToMany
    @JoinTable(
        name = "customer_course",
        joinColumns = [JoinColumn(name = "course_id")],
        inverseJoinColumns = [JoinColumn(name = "customer_id")]
    )
    val customers: List<CustomerEntity> = emptyList(),

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    val createdDate: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_date")
    val updatedDate: LocalDateTime? = null
)