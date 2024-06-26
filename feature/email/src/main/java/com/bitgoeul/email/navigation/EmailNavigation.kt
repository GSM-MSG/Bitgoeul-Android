package com.bitgoeul.email.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.bitgoeul.email.EmailSendInformRoute
import com.bitgoeul.email.InputEmailRoute
import com.bitgoeul.email.InputNewPasswordRoute
import com.bitgoeul.email.PasswordChangeSuccessRoute

const val inputEmailRoute = "input_email_route"

const val emailSendInformRoute = "email_send_inform_route"

const val inputNewPasswordRoute = "input_new_password_route"

const val passwordChangeSuccessRoute = "password_change_success_route"

fun NavController.navigateToInputEmail(navOptions: NavOptions? = null) {
    this.navigate(inputEmailRoute, navOptions)
}

fun NavController.navigateToEmailSendInform(navOptions: NavOptions? = null) {
    this.navigate(emailSendInformRoute, navOptions)
}

fun NavController.navigateToInputNewPassword(navOptions: NavOptions? = null) {
    this.navigate(inputNewPasswordRoute, navOptions)
}

fun NavController.navigateToPasswordChangeSuccess(navOptions: NavOptions? = null) {
    this.navigate(passwordChangeSuccessRoute, navOptions)
}

fun NavGraphBuilder.inputEmailScreen(
    onBackClicked: () -> Unit,
    onNextClicked: () -> Unit,
) {
    composable(route = inputEmailRoute) {
        InputEmailRoute(
            onBackClicked = onBackClicked,
            onNextClicked = onNextClicked,
        )
    }
}

fun NavGraphBuilder.emailSendInformScreen(
    onBackClicked: () -> Unit,
    onMoveNewPasswordClicked: () -> Unit,
) {
    composable(route = emailSendInformRoute) {
        EmailSendInformRoute(
            onBackClicked = onBackClicked,
            onMoveNewPasswordClicked = onMoveNewPasswordClicked,
        )
    }
}

fun NavGraphBuilder.inputNewPasswordScreen(
    onBackClicked: () -> Unit,
    onNextClicked: () -> Unit
) {
    composable(route = inputNewPasswordRoute) {
        InputNewPasswordRoute(
            onBackClicked = onBackClicked,
            onNextClicked = onNextClicked
        )
    }
}

fun NavGraphBuilder.passwordChangeSuccessScreen(
    onBackClicked: () -> Unit
) {
    composable(route = passwordChangeSuccessRoute) {
        PasswordChangeSuccessRoute(
            onBackClicked = onBackClicked
        )
    }
}