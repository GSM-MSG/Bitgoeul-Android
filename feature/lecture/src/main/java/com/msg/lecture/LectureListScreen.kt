package com.msg.lecture

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.R
import com.msg.design_system.component.bottomsheet.LectureFilterBottomSheet
import com.msg.design_system.component.icon.FilterIcon
import com.msg.design_system.component.icon.PlusIcon
import com.msg.lecture.component.LectureList
import com.msg.lecture.util.Event
import com.msg.model.remote.enumdatatype.ApproveStatus
import com.msg.model.remote.enumdatatype.Authority
import com.msg.model.remote.enumdatatype.LectureType
import com.msg.model.remote.response.lecture.LectureListResponse
import java.util.UUID

@Composable
fun LectureListRoute(
    onOpenClicked: () -> Unit,
    onItemClicked: () -> Unit,
    onBackClicked: () -> Unit,
    viewModel: LectureViewModel = hiltViewModel(),
    id: UUID? = null,
    status: ApproveStatus,
    type: LectureType,
) {
    val role = viewModel.role
    viewModel.getLectureList(
        role = role,
        page = 1,
        size = 10,
        status = status,
        type = type
    )
    LaunchedEffect(true) {
        getLectureList(
            viewModel = viewModel,
            onSuccess = {
                viewModel.lectureList.addAll(it)
            }
        )
    }
    LectureListScreen(
        data = viewModel.lectureList,
        onOpenClicked = onOpenClicked,
        onItemClicked = {
            onItemClicked()
            viewModel.selectedLectureId.value = it
        },
        onBackClicked = onBackClicked,
        role = role,
        status = status,
        type = type
    )
}

suspend fun getLectureList(
    viewModel: LectureViewModel,
    onSuccess: (data: List<LectureListResponse>) -> Unit,
) {
    viewModel.getLectureListResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!.toList())
            }

            else -> {}
        }
    }
}

@Composable
fun LectureListScreen(
    data: List<LectureListResponse>? = null,
    onOpenClicked: () -> Unit,
    onItemClicked: (UUID) -> Unit,
    onBackClicked: () -> Unit,
    role: Authority,
    status: ApproveStatus,
    type: LectureType,
) {
    var isFilterBottomSheetVisible = remember { mutableStateOf(false) }

    BitgoeulAndroidTheme { colors, typography ->
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colors.WHITE),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Spacer(modifier = Modifier.width(28.dp))

                    Text(
                        modifier = Modifier
                            .width(97.dp)
                            .height(31.dp),
                        text = stringResource(id = R.string.lecture_list),
                        color = colors.BLACK,
                        style = typography.titleMedium,
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    if (role == Authority.ROLE_PROFESSOR || role == Authority.ROLE_GOVERNMENT || role == Authority.ROLE_COMPANY_INSTRUCTOR) {
                        IconButton(
                            onClick = onOpenClicked,
                            modifier = Modifier.padding(top = 4.dp),
                            content = { PlusIcon() }
                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    FilterIcon(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .clickable {
                                isFilterBottomSheetVisible.value = !isFilterBottomSheetVisible.value
                            }
                    )

                    Text(
                        text = "필터",
                        color = colors.G1,
                        style = typography.bodySmall,
                    )

                    Spacer(modifier = Modifier.width(28.dp))

                }

                Spacer(modifier = Modifier.height(40.dp))

                if (data != null) {
                    LectureList(
                        data = data,
                        onClick = onItemClicked,
                        role = role,
                        status = status,
                        type = type
                    )
                }

                LectureFilterBottomSheet(
                    onQuit = { isFilterBottomSheetVisible.value = false },
                    isVisible = isFilterBottomSheetVisible.value
                )

            }
        }
    }
}

@Preview
@Composable
fun LectureListPagePreview() {
    LectureListScreen(
        onBackClicked = {},
        onItemClicked = {},
        onOpenClicked = {},
        role = Authority.ROLE_STUDENT,
        type = LectureType.MUTUAL_CREDIT_RECOGNITION_PROGRAM,
        status = ApproveStatus.APPROVED
    )
}