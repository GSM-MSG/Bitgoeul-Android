package com.msg.network.datasource.club

import com.msg.model.remote.enumdatatype.HighSchool
import com.msg.model.remote.response.club.ClubDetailResponse
import com.msg.model.remote.response.club.ClubListResponse
import com.msg.model.remote.response.club.StudentBelongClubResponse
import kotlinx.coroutines.flow.Flow

interface ClubDataSource {
    suspend fun getClubList(highSchool: HighSchool): Flow<List<ClubListResponse>>
    suspend fun getClubDetail(id: Long): Flow<ClubDetailResponse>
    suspend fun getStudentBelongClubList(id:Long): Flow<List<StudentBelongClubResponse>>
}