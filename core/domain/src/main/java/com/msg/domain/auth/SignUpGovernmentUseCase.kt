package com.msg.domain.auth

import com.msg.data.repository.auth.AuthRepository
import com.msg.model.remote.request.auth.SignUpGovernmentRequest
import javax.inject.Inject

class SignUpGovernmentUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: SignUpGovernmentRequest) = runCatching {
        authRepository.signUpGovernment(body = body)
    }
}
