package com.backend.uniactivosxray.services

import com.backend.uniactivosxray.*

import org.springframework.beans.factory.annotation.Autowired
/*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
*/
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.collections.ArrayList

interface UserService {
    fun findByEmail(email: String): Optional<User>

    fun getPrivileges(roles: MutableList<Role>): List<String>

    fun userLogin(email: String, password: String): Optional<User>

    fun userChangePassword(email: String, oldPassword: String, newPassword: String)

}

@Service
@Transactional
class UserServiceImpl(

    @Autowired
        private val userRepository: UserRepository,
    @Autowired
        val userMapper: UserMapper,
    @Autowired
        private val roleRepository: RoleRepository

) : UserService {

    //method findByEmail
    override fun findByEmail(email: String): Optional<User> {
        return userRepository.findByEmail(email)
    }

    override fun getPrivileges(roles: MutableList<Role>): List<String> {
        val privileges: MutableList<String> = ArrayList()
        val collection: MutableList<Privilege> = ArrayList()

        if (roles != null) {
            for (role in roles) {
                collection.addAll(role.privilegeList)
            }
        }

        for (item in collection) {
            privileges.add(item.name)
        }

        return privileges
    }

    override fun userChangePassword(email: String, oldPassword: String, newPassword: String) {
        val user = userRepository.findByEmail(email)
        if (user.isPresent && user.get().password == oldPassword) {
            val updatedUser = user.get().copy(password = newPassword)
            userRepository.save(updatedUser)
        } else {
            throw IllegalArgumentException("Invalid email or password")
        }
    }

    override fun userLogin(email: String, password: String): Optional<User> {
        if (email != null && password != null) {
            val user: Optional<User> = userRepository.findByEmail(email)

            if (user.isPresent) {
                if (user.get().password == password) {
                    return user
                }
            }
            return Optional.empty()
        }
        return Optional.empty()
    }
}