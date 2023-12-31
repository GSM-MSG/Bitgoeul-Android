package com.msg.network.datasource.activity

import com.msg.model.remote.model.activity.StudentActivityModel
import com.msg.model.remote.response.activity.InquiryDetailStudentActivityInfoResponse
import com.msg.model.remote.response.activity.InquiryStudentActivityListResponse
import com.msg.network.api.ActivityAPI
import com.msg.network.util.BitgoeulApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.UUID
import javax.inject.Inject

class ActivityDataSourceImpl @Inject constructor(
    private val activityAPI: ActivityAPI
) : ActivityDataSource {
    override suspend fun addStudentActivityInfo(body: StudentActivityModel): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { activityAPI.addStudentActivityInfo(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun editStudentActivityInfo(id: UUID, body: StudentActivityModel): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { activityAPI.editStudentActivityInfo(id = id, body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun approveStudentActivityInfo(id: UUID): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { activityAPI.approveStudentActivityInfo(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun rejectStudentActivityInfo(id: UUID): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { activityAPI.rejectStudentActivityInfo(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteStudentActivityInfo(id: UUID): Flow<Unit> = flow {
        emit(
            BitgoeulApiHandler<Unit>()
                .httpRequest { activityAPI.deleteStudentActivityInfo(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun inquiryMyStudentActivityInfoList(
        page: Int,
        size: Int,
        sort: String
    ): Flow<InquiryStudentActivityListResponse> = flow {
        emit(
            BitgoeulApiHandler<InquiryStudentActivityListResponse>()
                .httpRequest { activityAPI.inquiryMyStudentActivityInfoList(page = page, size = size, sort = sort) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun inquiryStudentActivityInfoList(
        page: Int,
        size: Int,
        sort: String,
        id: UUID
    ): Flow<InquiryStudentActivityListResponse> = flow {
        emit(
            BitgoeulApiHandler<InquiryStudentActivityListResponse>()
                .httpRequest { activityAPI.inquiryStudentActivityInfoList(page = page, size = size, sort = sort, id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun inquiryEntireStudentActivityInfoList(
        page: Int,
        size: Int,
        sort: String
    ): Flow<InquiryStudentActivityListResponse> = flow {
        emit(
            BitgoeulApiHandler<InquiryStudentActivityListResponse>()
                .httpRequest { activityAPI.inquiryEntireStudentActivityInfoList(page = page, size = size, sort = sort) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun inquiryDetailStudentActivityInfo(id: UUID): Flow<InquiryDetailStudentActivityInfoResponse> = flow {
        emit(
            BitgoeulApiHandler<InquiryDetailStudentActivityInfoResponse>()
                .httpRequest { activityAPI.inquiryDetailStudentActivityInfo(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}