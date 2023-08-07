package com.goms.v2.domain.auth.exception

import com.goms.v2.common.exception.ErrorCode
import com.goms.v2.common.exception.GomsException

class ExpiredRefreshTokenException: GomsException(ErrorCode.EXPIRED_REFRESH_TOKEN)