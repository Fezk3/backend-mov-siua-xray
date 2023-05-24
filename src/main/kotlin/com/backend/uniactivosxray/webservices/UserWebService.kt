package com.backend.uniactivosxray.webservices

import com.backend.uniactivosxray.*
import com.backend.uniactivosxray.services.UserService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("\${url.user}")
class UserController(private val userService: UserService) {

    @GetMapping("/privileges/{roles}")
    @ResponseBody
    fun getPrivileges(@PathVariable roles: MutableList<Role>): List<String> {
        return userService.getPrivileges(roles)
    }

    @PutMapping("/changePassword")
    fun userChangePassword(@RequestBody userChangePasswordInput: UserChangePasswordInput): ResponseEntity<Unit> {
        userService.userChangePassword(userChangePasswordInput.email, userChangePasswordInput.oldPassword, userChangePasswordInput.newPassword)
        return ResponseEntity.noContent().build()
    }
}