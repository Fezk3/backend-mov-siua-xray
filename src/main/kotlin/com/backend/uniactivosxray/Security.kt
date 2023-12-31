package com.backend.uniactivosxray

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerExceptionResolver
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

object SecurityConstants {
    const val TOKEN_TYPE = "JWT"
    const val TOKEN_ISSUER = "secure-api"
    const val TOKEN_AUDIENCE = "secure-app"
    const val TOKEN_LIFETIME: Long = 864000000
    const val TOKEN_PREFIX = "Bearer "
    const val APPLICATION_JSON = "application/json"
    const val UTF_8 = "UTF-8"
    val TOKEN_SECRET: String = Base64.getEncoder().encodeToString("secret".toByteArray())
}

class JwtAuthenticationFilter(authenticationManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {

    //@Value("\${url.login}")
    //var loginUrl: String? = null

    private val authManager: AuthenticationManager

    init {
        setFilterProcessesUrl("/v1/users/login")
        authManager = authenticationManager
    }

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ): Authentication {
        if (request.method != "POST") {
            throw AuthenticationServiceException("Authentication method not supported: $request.method")
        }
        return try {
            // UserLoginInput es el dto
            val userLoginInput: UserLoginInput = ObjectMapper()
                .readValue(request.inputStream, UserLoginInput::class.java)
            // autManager es el encargado de autenticar por debajo
            authManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    userLoginInput.email,
                    userLoginInput.password,
                    ArrayList())
            )
        } catch (exception: IOException) {
            throw RuntimeException(exception)
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest, response: HttpServletResponse,
        filterChain: FilterChain, authentication: Authentication,
    ) {

        val objectMapper = ObjectMapper()

        //Construimos el token
        val token = Jwts.builder()
            .signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET)
            .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
            .setIssuer(SecurityConstants.TOKEN_ISSUER)
            .setAudience(SecurityConstants.TOKEN_AUDIENCE)
            .setSubject((authentication.principal as org.springframework.security.core.userdetails.User).username)
            .setExpiration(Date(System.currentTimeMillis() + SecurityConstants.TOKEN_LIFETIME))
            .compact()

        //Agregamos el token al header
        response.addHeader(HttpHeaders.AUTHORIZATION, SecurityConstants.TOKEN_PREFIX + token)

        val out = response.writer
        response.contentType = SecurityConstants.APPLICATION_JSON
        response.characterEncoding = SecurityConstants.UTF_8
        out.print(objectMapper.writeValueAsString(authentication.principal))
        out.flush()
    }
}

//Clase encargada de autenticar las peticiones
class JwtAuthorizationFilter(authenticationManager: AuthenticationManager) :
    BasicAuthenticationFilter(authenticationManager) {

    @Throws(IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse,
        filterChain: FilterChain,
    ) {

        var authorizationToken = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (authorizationToken != null && authorizationToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {

            //Quitamos el bearer del token

            authorizationToken = authorizationToken.replaceFirst(SecurityConstants.TOKEN_PREFIX.toRegex(), "")
            val email: String = Jwts.parser()
                .setSigningKey(SecurityConstants.TOKEN_SECRET)
                .parseClaimsJws(authorizationToken)
                .getBody()
                .getSubject()
            LoggedUser.logIn(email)
            SecurityContextHolder.getContext().authentication =
                UsernamePasswordAuthenticationToken(email, null, emptyList())
        }

        filterChain.doFilter(request, response)
    }
}

/**
 * Object to holder the user information
 */
object LoggedUser {
    private val userHolder = ThreadLocal<String>()
    fun logIn(user: String) {
        userHolder.set(user)
    }

    fun logOut() {
        userHolder.remove()
    }

    fun get(): String {
        return userHolder.get()
    }
}
