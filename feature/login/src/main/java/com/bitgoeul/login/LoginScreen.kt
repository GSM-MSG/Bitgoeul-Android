package com.bitgoeul.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msg.design_system.R
import com.msg.design_system.component.button.BitgoeulButton
import com.msg.design_system.component.button.ButtonState
import com.msg.design_system.component.textfield.EmailTextField
import com.msg.design_system.component.textfield.LinkText
import com.msg.design_system.component.textfield.PasswordTextField
import com.msg.design_system.theme.BitgoeulAndroidTheme

@Composable
fun LoginScreen() {
    val isEmailErrorStatus = remember { mutableStateOf(false)}
    val isPasswordErrorStatus = remember { mutableStateOf(false)}
    val isErrorTextShow = remember { mutableStateOf(false) }
    var isTextStatus: String = ""
    BitgoeulAndroidTheme { color, type ->
        Box {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(48.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.width(28.dp))
                    Text(
                        modifier = Modifier
                            .width(104.dp)
                            .height(108.dp),
                        text = stringResource(id = R.string.project_name),
                        color = color.BLACK,
                        style = type.titleLarge,
                        fontSize = 30.sp,
                    )
                }

                Spacer(modifier = Modifier.height(80.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp),
                ) {
                    EmailTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        placeholder = stringResource(id = R.string.email),
                        errorText = stringResource(id = R.string.error_text_email),
                        onValueChange = {
                            isTextStatus = it
                        },
                        isError = isEmailErrorStatus.value,
                        onClickButton = {
                            isEmailErrorStatus.value = isTextStatus.isNullOrBlank()
                        },
                        isLinked = false,
                        isDisabled = false,
                    ) {
                        // Action
                    }
                }

                if (isErrorTextShow.value) {
                    Spacer(modifier = Modifier.height(0.dp))
                } else {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp),
                ) {
                    PasswordTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        placeholder = stringResource(id = R.string.password),
                        errorText = stringResource(id = R.string.wrong_password),
                        onValueChange = {},
                        onClickLink = {},
                        isError = isPasswordErrorStatus.value,
                        isLinked = true,
                        linkText = stringResource(id = R.string.find_password),
                        isDisabled = false,
                    )
                }

                Spacer(modifier = Modifier.height(180.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp),
                ) {
                    BitgoeulButton(
                        text = stringResource(id = R.string.login),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        state = ButtonState.Disable,
                    ) {
                        // Action
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    modifier = Modifier,
                    text = "또는",
                    style = type.labelMedium,
                    color = color.G1,
                    fontSize = 14.sp,
                )

                Spacer(modifier = Modifier.height(2.dp))

                LinkText(
                    text = stringResource(id = R.string.sign_up)
                ) {
                    // Action
                }
            }
        }
    }
}

@Preview
@Composable
fun preview() {
    LoginScreen()
}
