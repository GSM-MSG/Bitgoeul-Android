package com.msg.bitgoeul_android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.bitgoeul.login.navigation.loginRoute
import com.bitgoeul.login.navigation.loginScreen
import com.msg.sign_up.navigation.navigateToSignUp
import com.msg.bitgoeul_android.ui.BitgoeulAppState
import com.msg.sign_up.navigation.signUpScreen
import com.msg.student_activity.navigation.navigateToAddActivity
import com.msg.student_activity.navigation.navigateToDetailSettingActivity
import com.msg.student_activity.navigation.navigateToStudentActivity
import com.msg.student_activity.navigation.navigateToStudentDetailActivity
import com.msg.student_activity.navigation.studentActivityScreen
import com.msg.student_activity.navigation.studentAddActivityScreen
import com.msg.student_activity.navigation.studentDetailActivityScreen
import com.msg.student_activity.navigation.studentDetailSettingActivityScreen

@Composable
fun BitgoeulNavHost(
    appState: BitgoeulAppState,
    modifier: Modifier = Modifier,
    startDestination: String = loginRoute
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        loginScreen(
            onSignUpClick = navController::navigateToSignUp
        )
        signUpScreen(
            onBackClick = navController::popBackStack
        )
        studentActivityScreen(
            onAddClick = navController::navigateToAddActivity,
            onItemClick = navController::navigateToStudentDetailActivity
        )
        studentDetailActivityScreen(
            onActionEnd = navController::popBackStack,
            onEditClicked = navController::navigateToAddActivity
        )
        studentAddActivityScreen(
            onActionClicked = navController::popBackStack,
            onSettingClicked = navController::navigateToDetailSettingActivity
        )
        studentDetailSettingActivityScreen(
            onCloseClick = navController::popBackStack,
            onApplyClicked = navController::popBackStack
        )
    }
}