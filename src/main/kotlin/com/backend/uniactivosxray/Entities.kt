package com.backend.uniactivosxray

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "role")
data class Role(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var name: String,

    // Entity Relationship
    @ManyToMany
    @JoinTable(
        name = "role_privilege",
        joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "privilege_id", referencedColumnName = "id")]
    )
    var privilegeList: Set<Privilege>,

){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Role) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Role(id=$id, name='$name', privilegeList=$privilegeList)"
    }

}


@Entity
@Table(name = "privilege")
data class Privilege(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var name: String,

    // Entity Relationship
    @ManyToMany(mappedBy = "privilegeList")
    var roleList: Set<Role>,
    @ManyToMany(mappedBy = "roleList", fetch = FetchType.LAZY)
    var userList: Set<User>,

){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Privilege) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Privilege(id=$id, name='$name', roleList=$roleList)"
    }

}


@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var firstName: String,
    var lastName: String,
    var password: String,
    var email: String,
    var tokenExpired: Boolean?,

    // Entity Relationship
    @ManyToMany
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roleList: Set<Role>,

){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false

        if (id != other.id) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + email.hashCode()
        return result
    }

    override fun toString(): String {
        return "User(id=$id, firstName='$firstName', lastName='$lastName', password='$password', email='$email', tokenExpired=$tokenExpired, roleList=$roleList)"
    }

}