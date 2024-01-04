package com.msg.network.datasource.post

import com.msg.model.remote.enumdatatype.FeedType
import com.msg.model.remote.request.post.WritePostRequest
import com.msg.model.remote.response.post.GetPostListResponse
import kotlinx.coroutines.flow.Flow

interface PostDataSource {
    suspend fun sendPost(body: WritePostRequest): Flow<Unit>

    suspend fun getPostList(type: FeedType, size: Int, page: Int): Flow<GetPostListResponse>
}