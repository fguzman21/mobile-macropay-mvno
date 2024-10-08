package com.grupomacro.mvno

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.grupomacro.mvno.core.navigation.AppNavigationBar
import com.grupomacro.mvno.core.navigation.buildNavigationGraph
import com.grupomacro.mvno.core.ui.composables.LaunchLifecycleActions
import com.grupomacro.mvno.core.ui.composables.MainTopAppBar
import com.grupomacro.mvno.core.ui.theme.MvnoTheme
import com.grupomacro.mvno.screens.ContainerViewModel
import com.grupomacro.mvno.screens.container.presentation.model.ContainerAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ContainerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(window) {
            addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            enterTransition = Slide(Gravity.END)
            exitTransition = Slide(Gravity.START)
        }
        setContent {
            LaunchLifecycleActions(viewModel, ContainerAction.ScreenStartedAction, ContainerAction.ScreenStoppedAction)
            MvnoTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                val navController = rememberNavController()
                val navGraph = buildNavigationGraph(navController)
                Scaffold(
                    topBar = {
                        MainTopAppBar(
                            uiState,
                            navController,
                            createOpenNotificationsCallback(),
                        )
                    },
                    bottomBar = {
                        AppNavigationBar(navController)
                    }
                ) { contentPadding ->
                    NavHost(
                        navController = navController,
                        graph = navGraph,
                        modifier = Modifier.padding(contentPadding),
                    )
                }
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(android.R.id.content)
        ) { contentView, insets ->
            val bottomPadding = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            contentView.updatePadding(bottom = bottomPadding)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    private fun createOpenNotificationsCallback(): () -> Unit {
        val callback = { NotificationsActivity.launch(this) }
        return {
            viewModel.sendAction(ContainerAction.ShowNotificationsSection(callback))
        }
    }

    companion object {
        fun launch(launchingActivity: Activity, bundle: Bundle) {
            val intent = Intent(launchingActivity, MainActivity::class.java).apply {
                putExtras(bundle)
            }
            launchingActivity.finishAfterTransition()
            launchingActivity.startActivity(intent)
        }
    }
}
