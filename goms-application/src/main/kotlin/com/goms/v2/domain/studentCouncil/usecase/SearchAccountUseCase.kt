package com.goms.v2.domain.studentCouncil.usecase

import com.goms.v2.common.annotation.UseCaseWithReadOnlyTransaction
import com.goms.v2.domain.account.data.dto.StudentNumberDto
import com.goms.v2.domain.studentCouncil.data.dto.AccountDto
import com.goms.v2.domain.studentCouncil.data.dto.SearchAccountDto
import com.goms.v2.repository.account.AccountRepository
import com.goms.v2.repository.outing.OutingBlackListRepository
import kotlin.streams.asSequence

@UseCaseWithReadOnlyTransaction
class SearchAccountUseCase(
    private val accountRepository: AccountRepository,
    private val outingBlackListRepository: OutingBlackListRepository
) {

    fun execute(dto: SearchAccountDto): List<AccountDto> {
        val outingBlackLIstIdx = outingBlackListRepository.findAll().map { it.accountIdx }

        return accountRepository.findAccountByStudentInfo(dto.grade, dto.classNum, dto.name, dto.authority).stream().asSequence()
            .filter {
                if (dto.isBlackList != null && dto.isBlackList) outingBlackLIstIdx.contains(it.idx)
                else if (dto.isBlackList != null) outingBlackLIstIdx.contains(it.idx).not()
                else true
            }.map {
                AccountDto(
                    accountIdx = it.idx,
                    name = it.name,
                    studentNum = StudentNumberDto(it.studentNumber.grade, it.studentNumber.classNum, it.studentNumber.number),
                    profileUrl = it.profileUrl,
                    authority = it.authority,
                    isBlackList = outingBlackLIstIdx.contains(it.idx)
                )
            }.toList()
    }

}