package com.msg.domain.lecture

import com.msg.data.repository.lecture.LectureRepository
import java.util.UUID
import javax.inject.Inject

class RejectPendingLecture @Inject constructor(
    private val lectureRepository: LectureRepository
) {
    suspend operator fun invoke(id: UUID) = kotlin.runCatching {
        lectureRepository.rejectPendingLecture(id = id)
    }
}