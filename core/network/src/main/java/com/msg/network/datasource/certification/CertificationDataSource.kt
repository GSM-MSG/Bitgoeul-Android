package com.msg.network.datasource.certification

import com.msg.model.remote.request.certification.WriteCertificationRequest
import com.msg.model.remote.response.certification.CertificationListResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface CertificationDataSource {
    fun getCertificationListForTeacher(studentId: UUID): Flow<List<CertificationListResponse>>
    fun getCertificationListForStudent(): Flow<List<CertificationListResponse>>
    fun writeCertification(body: WriteCertificationRequest): Flow<Unit>
    fun editCertification(id: UUID, body: WriteCertificationRequest): Flow<Unit>
}