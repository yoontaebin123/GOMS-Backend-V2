package com.goms.v2.domain.auth.exception.error

import com.goms.v2.common.error.ErrorProperty
import com.goms.v2.common.error.ErrorStatus

enum class AuthErrorCode(
    private val status: Int,
    private val message: String
): ErrorProperty {

    EXPIRED_CODE(ErrorStatus.UNAUTHORIZED, "만료된 코드입니다"),
    SECRET_MISMATCH(ErrorStatus.BAD_REQUEST, "클라이언트 시크릿 값이 일치하지 않습니다."),
    SERVICE_NOT_FOUND(ErrorStatus.NOT_FOUND, "서비스를 찾지 못했습니다."),
    INTERNAL_SERVER_ERROR(ErrorStatus.INTERNAL_SERVER_ERROR, "Interrupted File IO")
    ;

    override fun status(): Int = status

    override fun message(): String = message

}