package com.eduhive.Edu_Hive.entity

import com.eduhive.Edu_Hive.enums.Role
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime


@Entity
@Table(name = "course")
class Course(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?,

    name: String,

    price: Double,


    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    val creator: Creator,

    // Relationship with Customers (Many customers can buy one course)
    @ManyToMany
    @JoinTable(
        name = "customer_course",
        joinColumns = [JoinColumn(name = "course_id")],
        inverseJoinColumns = [JoinColumn(name = "customer_id")]
    )
    val customers: List<Customer> = emptyList(),


    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    val createdDate: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_date")
    val updatedDate: LocalDateTime? = null
)
