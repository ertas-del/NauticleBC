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
    const val MODULE_PATTERN = "module/{$ARG_MODULE}"
    const val LESSON_PATTERN = "lesson/{$ARG_LESSON}"
    const val QUIZ_PATTERN = "quiz/{$ARG_LESSON}"
    const val EXAM_FOCUS_PATTERN = "examfocus/{$ARG_MODULE}"
    const val MODULE_QUIZ_PATTERN = "modulequiz/{$ARG_MODULE}"

    fun module(moduleId: String) = "module/$moduleId"
    fun lesson(lessonId: String) = "lesson/$lessonId"
    fun quiz(lessonId: String) = "quiz/$lessonId"
    fun examFocus(moduleId: String) = "examfocus/$moduleId"
    fun moduleQuiz(moduleId: String) = "modulequiz/$moduleId"
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
                onOpenModule = { navController.navigate(Routes.module(it)) },
            )
        }

        composable(
            route = Routes.MODULE_PATTERN,
            arguments = listOf(navArgument(Routes.ARG_MODULE) { type = NavType.StringType }),
        ) { entry ->
            val moduleId = entry.arguments?.getString(Routes.ARG_MODULE).orEmpty()
            BundleGate(
                result = bundleResult,
                title = moduleId,
                onBack = { navController.popBackStack() },
            ) { bundle ->
                ModuleScreen(
                    moduleId = moduleId,
                    bundle = bundle,
                    progress = progress,
                    onOpenLesson = { navController.navigate(Routes.lesson(it)) },
                    onOpenExamFocus = { navController.navigate(Routes.examFocus(moduleId)) },
                    onBack = { navController.popBackStack() },
                )
            }
        }

        composable(
            route = Routes.EXAM_FOCUS_PATTERN,
            arguments = listOf(navArgument(Routes.ARG_MODULE) { type = NavType.StringType }),
        ) { entry ->
            val moduleId = entry.arguments?.getString(Routes.ARG_MODULE).orEmpty()
            BundleGate(
                result = bundleResult,
                title = stringResource(R.string.exam_focus_title),
                onBack = { navController.popBackStack() },
            ) { bundle ->
                ExamFocusScreen(
                    moduleId = moduleId,
                    bundle = bundle,
                    onQuizModule = { navController.navigate(Routes.moduleQuiz(moduleId)) },
                    onBack = { navController.popBackStack() },
                )
            }
        }

        composable(
            route = Routes.MODULE_QUIZ_PATTERN,
            arguments = listOf(navArgument(Routes.ARG_MODULE) { type = NavType.StringType }),
        ) { entry ->
            val moduleId = entry.arguments?.getString(Routes.ARG_MODULE).orEmpty()
            BundleGate(
                result = bundleResult,
                title = stringResource(R.string.quiz_screen_title),
                onBack = { navController.popBackStack() },
            ) { bundle ->
                ModuleQuizScreen(
                    moduleId = moduleId,
                    bundle = bundle,
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
