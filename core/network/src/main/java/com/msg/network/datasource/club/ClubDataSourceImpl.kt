package com.msg.network.datasource.club

import com.msg.network.response.club.*
import com.msg.model.enumdata.HighSchool
import com.msg.network.api.ClubAPI
import com.msg.network.util.makeRequest
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class ClubDataSourceImpl @Inject constructor(
    private val clubAPI: ClubAPI,
) : ClubDataSource {
    override fun getClubList(highSchool: HighSchool): Flow<List<ClubListResponse>> =
        makeRequest { clubAPI.getClubList(highSchool = highSchool) }

    override fun getClubDetail(id: Long): Flow<ClubDetailResponse> =
        makeRequest { clubAPI.getClubDetail(id = id) }

    override fun getStudentBelongClubDetail(id: Long, studentId: UUID): Flow<StudentBelongClubResponse> =
        makeRequest { clubAPI.getStudentBelongClubDetail(id = id, studentId = studentId) }

    override fun getMyClubDetail(): Flow<ClubDetailResponse> =
        makeRequest { clubAPI.getMyClubDetail() }
}