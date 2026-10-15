package ca.nauticlebc.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ca.nauticlebc.app.R
import ca.nauticlebc.app.content.ContentBundle
import ca.nauticlebc.app.content.ContentRepository
import ca.nauticlebc.app.content.LessonCatalog
import ca.nauticlebc.app.progress.LessonProgress
import ca.nauticlebc.app.progress.ProgressTracker
import ca.nauticlebc.app.progress.SharedPreferencesProgressStore

/** Navigation routes. Lesson ids are lowercase letters, digits, dots and dashes, so they are safe in a path. */
object Routes {
    const val HOME = "home"
    const val ARG_MODULE = "module"
    const val ARG_LESSON = "lessonId"
    const val ARG_TICKET = "ticket"
    const val TICKET_PATTERN = "ticket/{$ARG_TICKET}"
    const val MODULE_PATTERN = "module/{$ARG_MODULE}?ticket={$ARG_TICKET}"
    const val LESSON_PATTERN = "lesson/{$ARG_LESSON}"
    const val QUIZ_PATTERN = "quiz/{$ARG_LESSON}"
    const val EXAM_FOCUS_PATTERN = "examfocus/{$ARG_MODULE}?ticket={$ARG_TICKET}"
    const val MODULE_QUIZ_PATTERN = "modulequiz/{$ARG_MODULE}?ticket={$ARG_TICKET}"
    const val ARG_EXAM = "examId"
    const val MOCKS_PATTERN = "mocks?ticket={$ARG_TICKET}"
    const val MOCK_PATTERN = "mock/{$ARG_EXAM}"
    const val DRILLS = "drills"
    const val CHARTS = "charts"

    fun ticket(ticketId: String) = "ticket/$ticketId"
    fun mocks(ticketId: String) = "mocks?ticket=$ticketId"
    fun module(moduleId: String, ticketId: String = "") = "module/$moduleId?ticket=$ticketId"
    fun lesson(lessonId: String) = "lesson/$lessonId"
    fun quiz(lessonId: String) = "quiz/$lessonId"
    fun examFocus(moduleId: String, ticketId: String = "") = "examfocus/$moduleId?ticket=$ticketId"
    fun moduleQuiz(moduleId: String, ticketId: String = "") = "modulequiz/$moduleId?ticket=$ticketId"
    fun mock(examId: String) = "mock/$examId"
}

@Composable
fun NauticleApp() {
    val context = LocalContext.current
    val bundleResult by produceState<Result<ContentBundle>?>(initialValue = null) {
        value = ContentRepository.load(context)
    }
    val tracker = remember { ProgressTracker(SharedPreferencesProgressStore(context)) }
    var progress by remember { mutableStateOf(tracker.snapshot()) }
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(
                bundle = bundleResult,
                onOpenTicket = { navController.navigate(Routes.ticket(it)) },
            )
        }

        composable(
            route = Routes.TICKET_PATTERN,
            arguments = listOf(navArgument(Routes.ARG_TICKET) { type = NavType.StringType }),
        ) { entry ->
            val ticketId = entry.arguments?.getString(Routes.ARG_TICKET).orEmpty()
            BundleGate(
                result = bundleResult,
                title = ticketId,
                onBack = { navController.popBackStack() },
            ) { bundle ->
                TicketScreen(
                    ticketId = ticketId,
                    bundle = bundle,
                    onOpenModule = { navController.navigate(Routes.module(it, ticketId)) },
                    onOpenMocks = { navController.navigate(Routes.mocks(ticketId)) },
                    onOpenDrills = { navController.navigate(Routes.DRILLS) },
                    onOpenCharts = { navController.navigate(Routes.CHARTS) },
                    onBack = { navController.popBackStack() },
                )
            }
        }

        composable(Routes.CHARTS) {
            BundleGate(
                result = bundleResult,
                title = stringResource(R.string.chart_screen_title),
                onBack = { navController.popBackStack() },
            ) { bundle ->
                ChartScreen(bundle = bundle, onBack = { navController.popBackStack() })
            }
        }

        composable(Routes.DRILLS) {
            DrillScreen(onBack = { navController.popBackStack() })
        }

        composable(
            route = Routes.MOCKS_PATTERN,
            arguments = listOf(navArgument(Routes.ARG_TICKET) { type = NavType.StringType; defaultValue = "" }),
        ) { entry ->
            val ticketId = entry.arguments?.getString(Routes.ARG_TICKET).orEmpty()
            BundleGate(
                result = bundleResult,
                title = stringResource(R.string.mock_list_title),
                onBack = { navController.popBackStack() },
            ) { bundle ->
                MockExamListScreen(
                    bundle = bundle.forTicket(ticketId),
                    onOpen = { navController.navigate(Routes.mock(it)) },
                    onBack = { navController.popBackStack() },
                )
            }
        }

        composable(
            route = Routes.MOCK_PATTERN,
            arguments = listOf(navArgument(Routes.ARG_EXAM) { type = NavType.StringType }),
        ) { entry ->
            val examId = entry.arguments?.getString(Routes.ARG_EXAM).orEmpty()
            BundleGate(
                result = bundleResult,
                title = stringResource(R.string.mock_list_title),
                onBack = { navController.popBackStack() },
            ) { bundle ->
                MockExamScreen(examId = examId, bundle = bundle, onBack = { navController.popBackStack() })
            }
        }

        composable(
            route = Routes.MODULE_PATTERN,
            arguments = listOf(
                navArgument(Routes.ARG_MODULE) { type = NavType.StringType },
                navArgument(Routes.ARG_TICKET) { type = NavType.StringType; defaultValue = "" },
            ),
        ) { entry ->
            val moduleId = entry.arguments?.getString(Routes.ARG_MODULE).orEmpty()
            val ticketId = entry.arguments?.getString(Routes.ARG_TICKET).orEmpty()
            BundleGate(
                result = bundleResult,
                title = moduleId,
                onBack = { navController.popBackStack() },
            ) { bundle ->
                ModuleScreen(
                    moduleId = moduleId,
                    bundle = bundle.forTicket(ticketId),
                    progress = progress,
                    onOpenLesson = { navController.navigate(Routes.lesson(it)) },
                    onOpenExamFocus = { navController.navigate(Routes.examFocus(moduleId, ticketId)) },
                    onBack = { navController.popBackStack() },
                )
            }
        }

        composable(
            route = Routes.EXAM_FOCUS_PATTERN,
            arguments = listOf(
                navArgument(Routes.ARG_MODULE) { type = NavType.StringType },
                navArgument(Routes.ARG_TICKET) { type = NavType.StringType; defaultValue = "" },
            ),
        ) { entry ->
            val moduleId = entry.arguments?.getString(Routes.ARG_MODULE).orEmpty()
            val ticketId = entry.arguments?.getString(Routes.ARG_TICKET).orEmpty()
            BundleGate(
                result = bundleResult,
                title = stringResource(R.string.exam_focus_title),
                onBack = { navController.popBackStack() },
            ) { bundle ->
                ExamFocusScreen(
                    moduleId = moduleId,
                    bundle = bundle.forTicket(ticketId),
                    onQuizModule = { navController.navigate(Routes.moduleQuiz(moduleId, ticketId)) },
                    onBack = { navController.popBackStack() },
                )
            }
        }

        composable(
            route = Routes.MODULE_QUIZ_PATTERN,
            arguments = listOf(
                navArgument(Routes.ARG_MODULE) { type = NavType.StringType },
                navArgument(Routes.ARG_TICKET) { type = NavType.StringType; defaultValue = "" },
            ),
        ) { entry ->
            val moduleId = entry.arguments?.getString(Routes.ARG_MODULE).orEmpty()
            val ticketId = entry.arguments?.getString(Routes.ARG_TICKET).orEmpty()
            BundleGate(
                result = bundleResult,
                title = stringResource(R.string.quiz_screen_title),
                onBack = { navController.popBackStack() },
            ) { bundle ->
                ModuleQuizScreen(
                    moduleId = moduleId,
                    bundle = bundle.forTicket(ticketId),
                    onBack = { navController.popBackStack() },
                )
            }
        }

        composable(
            route = Routes.LESSON_PATTERN,
            arguments = listOf(navArgument(Routes.ARG_LESSON) { type = NavType.StringType }),
        ) { entry ->
            val lessonId = entry.arguments?.getString(Routes.ARG_LESSON).orEmpty()
            BundleGate(
                result = bundleResult,
                title = stringResource(R.string.lesson_screen_title),
                onBack = { navController.popBackStack() },
            ) { bundle ->
                val lesson = LessonCatalog.findLesson(bundle.lessons, lessonId)
                if (lesson == null) {
                    MessageScreen(
                        title = stringResource(R.string.lesson_screen_title),
                        message = stringResource(R.string.error_lesson_not_found),
                        onBack = { navController.popBackStack() },
                    )
                } else {
                    LessonScreen(
                        lesson = lesson,
                        bundle = bundle,
                        progress = progress[lessonId] ?: LessonProgress(),
                        onMarkComplete = { progress = tracker.markCompleted(lessonId) },
                        onStartQuiz = { navController.navigate(Routes.quiz(lessonId)) },
                        onBack = { navController.popBackStack() },
                    )
                }
            }
        }

        composable(
            route = Routes.QUIZ_PATTERN,
            arguments = listOf(navArgument(Routes.ARG_LESSON) { type = NavType.StringType }),
        ) { entry ->
            val lessonId = entry.arguments?.getString(Routes.ARG_LESSON).orEmpty()
            BundleGate(
                result = bundleResult,
                title = stringResource(R.string.quiz_screen_title),
                onBack = { navController.popBackStack() },
            ) { bundle ->
                val lesson = LessonCatalog.findLesson(bundle.lessons, lessonId)
                if (lesson == null) {
                    MessageScreen(
                        title = stringResource(R.string.quiz_screen_title),
                        message = stringResource(R.string.error_lesson_not_found),
                        onBack = { navController.popBackStack() },
                    )
                } else {
                    val questions = remember(bundle, lessonId) {
                        LessonCatalog.questionsForLesson(bundle.questions, lessonId)
                    }
                    QuizScreen(
                        titleText = lesson.title,
                        stateKey = lesson.id,
                        questions = questions,
                        best = (progress[lessonId] ?: LessonProgress()).best,
                        onRecordScore = { score -> progress = tracker.recordQuiz(lessonId, score) },
                        onBackToLesson = { navController.popBackStack() },
                        onBackToLessons = { navController.backToModuleList() },
                        backLabel = stringResource(R.string.quiz_back_to_lesson),
                        doneLabel = stringResource(R.string.quiz_back_to_lessons),
                    )
                }
            }
        }
    }
}

/** From the quiz, return to the module's lesson list (quiz -> lesson -> module). */
private fun NavHostController.backToModuleList() {
    popBackStack() // quiz
    popBackStack() // lesson
}
