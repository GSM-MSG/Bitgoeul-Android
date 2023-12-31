package com.msg.network.datasource.club

import com.msg.model.remote.enumdatatype.HighSchool
import com.msg.model.remote.response.club.ClubDetailResponse
import com.msg.model.remote.response.club.ClubListResponse
import com.msg.model.remote.response.club.StudentBelongClubResponse
import com.msg.network.api.ClubAPI
import com.msg.network.util.BitgoeulApiHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ClubDataSourceImpl @Inject constructor(
    private val clubAPI: ClubAPI,
) : ClubDataSource {
    override suspend fun getClubList(highSchool: HighSchool): Flow<List<ClubListResponse>> = flow {
        emit(
            BitgoeulApiHandler<List<ClubListResponse>>()
                .httpRequest { clubAPI.getClubList(highSchool = highSchool) }
                .sendRequest()
        )
    }

    override suspend fun getClubDetail(id: Long): Flow<ClubDetailResponse> = flow {
        emit(
            BitgoeulApiHandler<ClubDetailResponse>()
                .httpRequest { clubAPI.getClubDetail(id = id) }
                .sendRequest()
        )
    }

    override suspend fun getStudentBelongClubList(id: Long): Flow<List<StudentBelongClubResponse>> = flow {
        emit(
            BitgoeulApiHandler<List<StudentBelongClubResponse>>()
                .httpRequest { clubAPI.getStudentBelongClubList(id = id) }
                .sendRequest()
        )
    }
}