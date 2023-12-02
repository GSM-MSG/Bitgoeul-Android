package com.msg.network.datasource.club

import com.msg.model.remote.enumdatatype.HighSchool
import com.msg.model.remote.response.club.ClubListResponse
import kotlinx.coroutines.flow.Flow

interface ClubDataSource {
    suspend fun getClubList(highSchool: HighSchool): Flow<List<ClubListResponse>>
}