package com.msg.design_system.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.design_system.component.icon.CancelIcon
import com.msg.design_system.component.icon.SeeableIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(
    modifier: Modifier,
    placeholder: String,
    errorText: String,
    linkText: String? = null,
    onValueChange: (String) -> Unit,
    onClickButton: () -> Unit,
    isError: Boolean,
    isLinked: Boolean,
    isDisabled: Boolean,
    onClickLink: () -> Unit
) {
    var text by remember { mutableStateOf("") }
    val isFocused = remember { mutableStateOf(false) }
    BitgoeulAndroidTheme { colors, typography ->
        Column {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                    onValueChange(it)
                },
                modifier = modifier
                    .border(
                        width = 1.dp,
                        color = when {
                            isDisabled -> colors.G1
                            isError -> colors.E5
                            text.isNotEmpty() -> colors.G1
                            isFocused.value -> colors.P5
                            else -> colors.G1
                        },
                        shape = RoundedCornerShape(8.dp)
                    )
                    .onFocusChanged {
                        isFocused.value = it.isFocused
                    }
                    .background(
                        color = if (isDisabled) colors.G9 else Color.Transparent
                    ),
                textStyle = typography.bodySmall,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    placeholderColor = colors.G2,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    textColor = if (isError) colors.E5 else colors.BLACK,
                    disabledTextColor = colors.G1,
                    cursorColor = colors.P5
                ),
                enabled = !isDisabled,
                placeholder = {
                    Text(text = placeholder, style = typography.bodySmall)
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            text = ""
                            onClickButton()
                        },
                        enabled = text.isNotEmpty()
                    ) {
                        if (text.isNotEmpty()) CancelIcon()
                    }
                }
            )
            if (isError||isLinked) {
                val isAll: Boolean = isError&&isLinked
                Row(
                    modifier = modifier.padding(4.dp),
                    horizontalArrangement = if (isAll) Arrangement.SpaceBetween else if (isError) Arrangement.Start else Arrangement.End
                ) {
                    if (isAll) {
                        ErrorText(text = errorText)
                        if (linkText != null) {
                            LinkText(text = linkText, onClickLink = onClickLink)
                        }
                    } else if (isError) {
                        ErrorText(text = errorText)
                    } else {
                        if (linkText != null) {
                            LinkText(text = linkText, onClickLink = onClickLink)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    modifier: Modifier,
    placeholder: String,
    errorText: String,
    linkText: String? = null,
    onValueChange: (String) -> Unit,
    onClickLink: () -> Unit,
    isError: Boolean,
    isLinked: Boolean,
    isDisabled: Boolean
) {
    var showPassword by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    val isFocused = remember { mutableStateOf(false) }
    BitgoeulAndroidTheme { colors, typography ->
        Column {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                    onValueChange(it)
                },
                placeholder = {
                    Text(text = placeholder, style = typography.bodySmall)
                },
                modifier = modifier
                    .border(
                        width = 1.dp,
                        color = when {
                            isDisabled -> colors.G1
                            isError -> colors.E5
                            text.isNotEmpty() -> colors.G1
                            isFocused.value -> colors.P5
                            else -> colors.G1
                        },
                        shape = RoundedCornerShape(8.dp)
                    )
                    .onFocusChanged {
                        isFocused.value = it.isFocused
                    }
                    .background(
                        color = if (isDisabled) colors.G9 else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    ),
                textStyle = typography.bodySmall,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    placeholderColor = colors.G2,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    textColor = if (isError) colors.E5 else colors.BLACK,
                    disabledTextColor = colors.G1,
                    cursorColor = colors.P5
                ),
                enabled = !isDisabled,
                visualTransformation = if (showPassword) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    if (showPassword) {
                        IconButton(onClick = { showPassword = false }) {
                            SeeableIcon(modifier = modifier, disable = !showPassword)
                        }
                    } else {
                        IconButton(onClick = { showPassword = true }) {
                            SeeableIcon(modifier = modifier, disable = !showPassword)
                        }
                    }
                }
            )
            if (isError || isLinked) {
                val isAll: Boolean = isError && isLinked
                Row(
                    modifier = modifier.padding(4.dp),
                    horizontalArrangement = if (isAll) Arrangement.SpaceBetween else if (isError) Arrangement.Start else Arrangement.End
                ) {
                    if (isAll) {
                        ErrorText(text = errorText)
                        if (linkText != null) {
                            LinkText(text = linkText, onClickLink = onClickLink)
                        }
                    } else if (isError) {
                        ErrorText(text = errorText)
                    } else {
                        if (linkText != null) {
                            LinkText(text = linkText, onClickLink = onClickLink)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorText(
    text: String
) {
    BitgoeulAndroidTheme { colors, typography ->
        Text(text = text, color = colors.E5, style = typography.labelMedium)
    }
}

@Composable
fun LinkText(
    text: String,
    onClickLink: () -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->  
        Text(modifier = Modifier.clickable(enabled = true, onClick = onClickLink),text = text, color = colors.P5, style = typography.labelMedium)
    }
}

@Preview
@Composable
fun TextFieldPre() {

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        EmailTextField(
            modifier = Modifier
                .width(320.dp)
                .height(52.dp),
            placeholder = "Put Email",
            errorText = "Incorrect Format",
            onValueChange = {},
            onClickButton = {},
            isError = false,
            isLinked = false,
            isDisabled = false
        ) {
        }

        PasswordTextField(
            modifier = Modifier
                .width(320.dp)
                .height(52.dp),
            placeholder = "Put Password",
            errorText = "Incorrect Password",
            onValueChange = {},
            onClickLink = {},
            isError = true,
            isLinked = true,
            linkText = "Sign Up",
            isDisabled = false
        )

        PasswordTextField(
            modifier = Modifier
                .width(320.dp)
                .height(52.dp),
            placeholder = "Put Password",
            errorText = "Incorrect Password",
            onValueChange = {},
            onClickLink = {},
            isError = false,
            isLinked = false,
            isDisabled = true
        )
    }
}