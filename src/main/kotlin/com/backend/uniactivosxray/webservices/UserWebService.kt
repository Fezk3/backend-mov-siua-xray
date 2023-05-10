package com.backend.uniactivosxray.webservices

import com.backend.uniactivosxray.Role
import com.backend.uniactivosxray.User
import com.backend.uniactivosxray.UserDetails
import com.backend.uniactivosxray.services.UserService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/privileges")
class UserController(private val userService: UserService) {

    @GetMapping
    @ResponseBody
    fun getPrivileges(@PathVariable roles: MutableList<Role>): List<String> {
        return userService.getPrivileges(roles)
    }

    @PostMapping("/login")
    @ResponseBody
    fun userLogin(@PathVariable email: String, password: String): Optional<User> {
        return userService.userLogin(email, password)
    }

}