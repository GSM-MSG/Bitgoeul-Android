package com.msg.certification

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.certification.component.CertificationSection
import com.msg.certification.component.FinishedLectureSection
import com.msg.certification.component.StudentInfoSection
import com.msg.certification.util.Event
import com.msg.design_system.component.icon.HumanIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.model.remote.response.certification.CertificationListResponse
import com.msg.model.remote.response.club.StudentBelongClubResponse
import com.msg.model.remote.response.lecture.GetLectureSignUpHistoryResponse
import com.msg.model.remote.response.lecture.SignUpLectures
import com.msg.ui.DevicePreviews
import java.time.LocalDate
import java.util.UUID

@Composable
fun CertificationScreenRoute(
    viewModel: CertificationViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    onHumanIconClicked: () -> Unit,
    onEditClicked: () -> Unit
) {
    viewModel.getCertificationList()
    viewModel.getStudentBelong()
    viewModel.getLectureSignUpHistory()

    LaunchedEffect(true) {
        getCertificationList(
            viewModel = viewModel,
            onSuccess = {
                viewModel.certificationList.addAll(it)
            },
            onFailure = {}
        )
        getStudentData(
            viewModel = viewModel,
            onSuccess = {
                viewModel.studentData.value = it
            },
            onFailure = {}
        )
        getLectureData(
            viewModel = viewModel,
            onSuccess = {
                viewModel.lectureData.value = it
            },
            onFailure = {}
        )
    }

    CertificationScreen(
        onHumanIconClicked = onHumanIconClicked,
        onEditClicked = { id, title, date ->
            viewModel.selectedCertificationId.value = id
            viewModel.selectedTitle.value = title
            viewModel.selectedDate.value = date
            onEditClicked()
        },
        onPlusClicked = onEditClicked,
        studentData = viewModel.studentData.value,
        certificationData = viewModel.certificationList,
        lectureData = viewModel.lectureData.value
    )
}

suspend fun getCertificationList(
    viewModel: CertificationViewModel,
    onSuccess: (data: List<CertificationListResponse>) -> Unit,
    onFailure: () -> Unit
) {
    viewModel.getCertificationListResponse.collect { response ->
        when(response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }
            else -> onFailure()
        }
    }
}

suspend fun getStudentData(
    viewModel: CertificationViewModel,
    onSuccess: (data: StudentBelongClubResponse) -> Unit,
    onFailure: () -> Unit
) {
    viewModel.getStudentBelongResponse.collect { response ->
        when(response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }
            else -> onFailure()
        }
    }
}

suspend fun getLectureData(
    viewModel: CertificationViewModel,
    onSuccess: (data: GetLectureSignUpHistoryResponse) -> Unit,
    onFailure: () -> Unit
) {
    viewModel.getLectureSignUpHistoryResponse.collect { response ->
        when(response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }
            else -> onFailure()
        }
    }
}

@Composable
fun CertificationScreen(
    modifier: Modifier = Modifier,
    onHumanIconClicked: () -> Unit,
    onEditClicked: (id: UUID, title: String, date: LocalDate) -> Unit,
    onPlusClicked: () -> Unit,
    studentData: StudentBelongClubResponse,
    certificationData: List<CertificationListResponse>,
    lectureData: GetLectureSignUpHistoryResponse
) {
    BitgoeulAndroidTheme { colors, typography ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.WHITE)
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 28.dp)
            ) {
                Spacer(modifier = modifier.height(24.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "학생정보",
                        style = typography.titleMedium,
                        color = colors.BLACK
                    )
                    Spacer(modifier = modifier.weight(1f))
                    IconButton(
                        modifier = modifier.size(24.dp),
                        content = { HumanIcon() },
                        onClick = onHumanIconClicked
                    )
                }
                Spacer(modifier = modifier.height(24.dp))
                StudentInfoSection(data = studentData)
                Spacer(modifier = modifier.height(24.dp))
                HorizontalDivider(
                    modifier = modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = colors.G9
                )
                Spacer(modifier = modifier.height(24.dp))
                CertificationSection(
                    onPlusClicked = onPlusClicked,
                    onEditClicked =  { id, title, date ->
                        onEditClicked(id, title, date)
                    },
                    data = certificationData
                )
                Spacer(modifier = modifier.height(12.dp))
                FinishedLectureSection(data = lectureData)
            }
        }
    }
}

@DevicePreviews
@Composable
fun CertificationScreenPre() {
    CertificationScreen(
        onHumanIconClicked = {},
        onEditClicked = {_,_,_->},
        onPlusClicked = {},
        studentData = StudentBelongClubResponse(
            name = "채종인",
            phoneNumber = "010-1234-5678",
            email = "s22055@gsm.hs.kr",
            credit = 300
        ),
        certificationData = listOf(
            CertificationListResponse(
                certificationId = UUID.randomUUID(),
                name = "정보처리산업기사",
                acquisitionDate = LocalDate.now()
            )
        ),
        lectureData = GetLectureSignUpHistoryResponse(
            lectures = listOf(
                SignUpLectures(
                    id = UUID.randomUUID(),
                    name = "개쩌는 강의이름",
                    lectureType = "상호학점인정교육과정",
                    currentCompletedDate = LocalDate.now(),
                    lecturer = "채종인",
                    isComplete = true
                ),
                SignUpLectures(
                    id = UUID.randomUUID(),
                    name = "덜쩌는 강의이름",
                    lectureType = "상호학점인정교육과정",
                    currentCompletedDate = LocalDate.now(),
                    lecturer = "채종인",
                    isComplete = true
                )
            )
        )
    )
}