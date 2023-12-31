package com.msg.lecture.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.msg.design_system.R
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.description.ContentDescriptionText
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun LectureCard() {
    BitgoeulAndroidTheme { color, type ->
        Surface {

            Card(
                modifier = Modifier
                    .wrapContentSize(),
                colors = CardDefaults.cardColors(containerColor = color.WHITE)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "정찬우 교수", // 추후 넘어오는 리스폰스값으로 변경 예정
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        color = color.BLACK,
                        style = type.bodySmall,
                    )

                    Text(
                        text = "2023.11.10", // 추후 넘어오는 리스폰스값으로 변경 예정
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        color = color.G1,
                        style = type.bodySmall,
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "유저 리서치 - 사용자 경험 개선하기",  // 추후 넘어오는 리스폰스값으로 변경 예정
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        color = color.BLACK,
                        style = type.bodyLarge,
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    ContentDescriptionText(
                        maxLines = 2,
                        text = "청춘! 이는 듣기만 하여도 가슴이 설레는 말이다. 청춘! 너의 두 손을 가슴에 대고, 물방아 같은 심장이 박주홍 강민수 두근두근 연애" // 추후 넘어오는 리스폰스값으로 변경 예정
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "2023.10.30 ~ 2023.10.31", // 추후 넘어오는 리스폰스값으로 변경 예정
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight(),
                            color = color.G1,
                            style = type.labelMedium,
                        )

                        Image(
                            painterResource(id = R.drawable.ic_colon_separator),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp)
                                .align(alignment = Alignment.CenterVertically)
                        )

                        Text(
                            text = "50/100명",
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight(),
                            color = color.G1,
                            style = type.labelMedium,
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    LectureCategoryTag(
                        text = stringResource(id = R.string.mutual_credit_recognition_curriculum) // 추후 넘어오는 리스폰스값으로 변경 예정
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun LectureCardPre() {
    LectureCard()
}