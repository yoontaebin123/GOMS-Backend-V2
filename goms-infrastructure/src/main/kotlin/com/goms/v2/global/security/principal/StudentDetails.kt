package com.goms.v2.global.security.principal

import com.goms.v2.domain.account.Authority
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

class StudentDetails(
    private val studentIdx: UUID
): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(SimpleGrantedAuthority(Authority.ROLE_STUDENT.name))

    override fun getPassword(): String? = null

    override fun getUsername(): String = studentIdx.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}