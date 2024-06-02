package com.msg.data.repository.post

import com.msg.model.remote.enumdatatype.FeedType
import com.msg.model.remote.request.post.WritePostRequest
import com.msg.model.remote.response.post.GetDetailPostResponse
import com.msg.model.remote.response.post.GetPostListResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface PostRepository {
    fun sendPost(body: WritePostRequest): Flow<Unit>
    fun getPostList(type: FeedType, size: Int, page: Int): Flow<GetPostListResponse>
    fun getDetailPost(id: UUID): Flow<GetDetailPostResponse>
    fun editPost(id: UUID, body: WritePostRequest): Flow<Unit>
    fun deletePost(id: UUID): Flow<Unit>
}