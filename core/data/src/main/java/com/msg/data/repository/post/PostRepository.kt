package com.msg.data.repository.post

import com.msg.model.entity.post.GetDetailPostEntity
import com.msg.model.entity.post.GetPostListEntity
import com.msg.model.enumdata.FeedType
import com.msg.model.param.post.WritePostParam
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface PostRepository {
    fun sendPost(body: WritePostParam): Flow<Unit>
    fun getPostList(type: FeedType, size: Int, page: Int): Flow<GetPostListEntity>
    fun getDetailPost(id: UUID): Flow<GetDetailPostEntity>
    fun editPost(id: UUID, body: WritePostParam): Flow<Unit>
    fun deletePost(id: UUID): Flow<Unit>
}