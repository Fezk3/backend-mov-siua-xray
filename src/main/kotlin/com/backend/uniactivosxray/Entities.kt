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

    @OneToMany(mappedBy = "user")
    var scheduleList: Set<Schedule>,


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
@Entity
@Table(name = "college")
data class College(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var name: String = "",

    @OneToMany(mappedBy = "college")
    var classroomList: Set<Classroom>,

    ) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is College) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "College(id=$id, name='$name',classroomList=$classroomList)"
    }

}

@Entity
@Table(name = "classroom")
data class Classroom(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var classNumber: String,
    var peopleCapacity: Int,

    // Entity Relationship
    @ManyToOne
    @JoinColumn(name = "IdCollege")
    var college: College,

    @OneToMany(mappedBy = "classroom")
    var scheduleList: Set<Schedule>,

    ) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Classroom) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Classroom(id=$id, classNumber=$classNumber, peopleCapacity=$peopleCapacity, college=$college, scheduleList=$scheduleList)"
    }

}
@Entity
@Table(name = "schedule")
data class Schedule(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        var courseName: String,
        var date: Date,
        var startTime: String,
        var endTime: String,


        // Entity Relationship
        @ManyToOne
        @JoinColumn(name = "IdClassroom")
        var classroom: Classroom,

        @ManyToOne
        @JoinColumn(name = "IdUser")
        var user: User,

    ){

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Schedule) return false

            if (id != other.id) return false

            return true
        }

        override fun hashCode(): Int {
            return id?.hashCode() ?: 0
        }

        override fun toString(): String {
            return "Schedule(id=$id, classroom=$classroom, courseName=$courseName, date=$date, startTime=$startTime, endTime=$endTime, user=$user)"
        }

    }
@Entity
@Table(name = "form")
data class Form(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "IdClassroom")
    var classroom: Classroom,


){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Form) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Form(id=$id, classroom=$classroom)"
    }

}

@Entity
@Table(name = "formHistory")
data class FormHistory(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    // Entity Relationship with Form
    @ManyToOne
    @JoinColumn(name = "IdForm")
    var form: Form,

    @ManyToOne
    @JoinColumn(name = "IdUser")
    var user: User,

    var date: Date,

    @ManyToOne
    @JoinColumn(name = "IdState")
    var state: State
){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FormHistory) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "FormHistory(id=$id, form=$form, user=$user)"
    }

}

@Entity
@Table(name = "state")
data class State(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var description: String,

    // Entity Relationship with FormHistory
    @OneToMany(mappedBy = "state")
    var formHistoryList: Set<FormHistory>,
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
        return "State(id=$id, description='$description', formHistoryList=$formHistoryList)"
    }

}

@Entity
@Table(name = "assetType")
data class AssetType(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var description: String,

    // Entity Relationship
    @OneToMany(mappedBy = "assetType")
    var assetsList: Set<Assets>,

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
        return "AssetType(id=$id, description='$description', assetsList=$assetsList)"
    }
}

@Entity
@Table(name = "assets")
data class Assets(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var name: String,
    var quantity: Int,

    // Entity Relationship
    @ManyToOne
    @JoinColumn(name = "IdClassroom")
    var classroomlist: Classroom,

    @ManyToOne
    @JoinColumn(name = "IdAssetType")
    var assetType: AssetType,


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
        return "Assets(id=$id, name='$name', quantity=$quantity, classroomList=$classroomlist, assetType=$assetType)"
    }

}