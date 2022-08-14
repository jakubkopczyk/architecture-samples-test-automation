package com.example.android.architecture.blueprints.todoapp.tasks.test.steps

import android.content.Intent
import android.view.Gravity
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers
import androidx.test.espresso.contrib.NavigationViewActions.navigateTo
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.ServiceLocator
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.android.architecture.blueprints.todoapp.tasks.getToolbarNavigationContentDescription
import com.example.android.architecture.blueprints.todoapp.tasks.test.ActivityScenarioHolder
import com.example.android.architecture.blueprints.todoapp.tasks.test.ComposeRuleHolder
import com.example.android.architecture.blueprints.todoapp.util.deleteAllTasksBlocking
import com.example.android.architecture.blueprints.todoapp.util.saveTaskBlocking
import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsNot.not


class TasksKotlinSteps(
    val composeRuleHolder: ComposeRuleHolder,
    val scenarioHolder: ActivityScenarioHolder
) : SemanticsNodeInteractionsProvider by composeRuleHolder.composeRule {
    private lateinit var tasksRepository: TasksRepository

    @Before
    fun init() {
        ServiceLocator.createDataBase(ApplicationProvider.getApplicationContext())
        tasksRepository = ServiceLocator.provideTasksRepository(ApplicationProvider.getApplicationContext())
        tasksRepository.deleteAllTasksBlocking()

    }
    @After
    fun reset() {
        ServiceLocator.resetRepository()
    }

    @Given("I open the TODO app")
    fun I_have_a_CalculatorActivity() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        scenarioHolder.launch(
            Intent(
                instrumentation.targetContext,
                TasksActivity::class.java
            )
        )
    }

    @Given("I pre-add task with title {string} and description {string}")
    fun iPreAddTaskWithTitleAndDescription(taskTitle: String, taskDescription: String) {
        val task = Task(taskTitle, taskDescription)
        tasksRepository.saveTaskBlocking(task)
    }

    @Given("I pre-add completed task with title {string} and description {string}")
    fun iPreAddCompletedTaskWithTitleAndDescription(taskTitle: String, taskDescription: String) {
        val task = Task(taskTitle, taskDescription, isCompleted = true)
        tasksRepository.saveTaskBlocking(task)
    }

    @When("I open the drawer")
    fun iOpenTheDrawer() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(DrawerMatchers.isClosed(Gravity.START))) // Left Drawer should be closed.
            .perform(DrawerActions.open()) // Open Drawer
    }

    @And("I click the Statistics button")
    fun iClickTheStatisticsButton() {
        onView(withId(R.id.nav_view))
            .perform(navigateTo(R.id.statistics_fragment_dest))
    }

    @And("I click the Task List button")
    fun iClickTheTaskListButton() {
        onView(withId(R.id.nav_view))
            .perform(navigateTo(R.id.tasks_fragment_dest))
    }

    @Then("I check that statistics screen was opened")
    fun iCheckThatStatisticsScreenWasOpened() {
        onView(withId(R.id.statistics_layout)).check(ViewAssertions.matches
            (isDisplayed()))
    }

    @And("I check that tasks screen is opened")
    fun iCheckThatTasksScreenIsOpened() {
        onView(withId(R.id.tasks_container_layout)).check(ViewAssertions.matches
            (ViewMatchers.isDisplayed()))
    }

    @When("I open task with title {string}")
    fun iOpenTaskWithTitle(taskTitle: String) {
        onView(withText(taskTitle)).perform(click())
    }

    @And("I click the edit task button")
    fun iClickTheEditTaskButton() {
        onView(withId(R.id.edit_task_fab)).perform(click())
    }

    @And("I click the back button")
    fun iClickTheBackButton() {
        onView(
            withContentDescription(
                scenarioHolder.getScenario()
                    ?.getToolbarNavigationContentDescription()
            )
        ).perform(click())
    }

    @And("I click the physical back button")
    fun iClickThePhysicalBackButton() {
        pressBack()
    }

    @Then("I check if Task Details title is displayed")
    fun iCheckIfTaskDetailsTitleIsDisplayed() {
        onView(withId(R.id.task_detail_title_text)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Then("I check if task title in Task Details is {string}")
    fun taskTitleInTaskDetailsIs(taskTitle: String) {
        onView(withId(R.id.task_detail_title_text)).check(ViewAssertions
            .matches(withText(taskTitle)))
    }

    @And("I check if task description in Task Details is {string}")
    fun taskDescriptionInTaskDetailsIs(taskTitle: String) {
        onView(withId(R.id.task_detail_description_text)).check(ViewAssertions
            .matches(withText(taskTitle)))
    }

    @And("I check if task in Task Details is not completed")
    fun iCheckIfTaskInTaskDetailsIsNotCompleted() {
        onView(withId(R.id.task_detail_complete_checkbox)).check(ViewAssertions
            .matches(not(isChecked())))
    }

    @And("I enter the task title {string}")
    fun iEnterTheTaskTitle(taskTitle: String) {
        onView(withId(R.id.add_task_title_edit_text)).perform(replaceText(taskTitle))
    }

    @And("I enter the task description {string}")
    fun iEnterTheTaskDescription(taskDescription: String?) {
        onView(withId(R.id.add_task_description_edit_text)).perform(replaceText(taskDescription))
    }

    @And("I click the save button")
    fun iClickTheSaveButton() {
        onView(withId(R.id.save_task_fab)).perform(click())
    }

    @Then("I can see text {string}")
    fun iSeeText(textToCheck: String?) {
        onView(withText(textToCheck)).check(ViewAssertions.matches(isDisplayed()))
    }

    @And("I cannot see text {string}")
    fun iCannotSeeText(textToCheck: String) {
        onView(withText(textToCheck)).check(ViewAssertions.doesNotExist())
    }

    @Then("I can see no tasks element with text {string}")
    fun iCanSeeNoTasksElementWithText(textToCheck: String) {
        onView(withId(R.id.no_tasks_text)).check(ViewAssertions
            .matches(withText(textToCheck)))
    }

    @When("I click the add task button")
    fun iClickTheAddTaskButton() {
        onView(withId(R.id.add_task_fab)).perform(click())
    }

    @And("I click the task delete button in Task Details")
    fun iClickTheTaskDeleteButtonInTaskDetails() {
        onView(withId(R.id.menu_delete)).perform(click())
    }

    @And("I click the filter button")
    fun iClickTheFilterButton() {
        onView(withId(R.id.menu_filter)).perform(click())
    }

    @And("I click the button with text {string}")
    fun iClickTheWithText(buttonText: String) {
        onView(withText(buttonText)).perform(click())
    }

    @And("i click the completion checkbox in Task Details")
    fun iClickTheCompletionCheckboxInTaskDetails() {
        onView(withId(R.id.task_detail_complete_checkbox)).perform(click())
    }

    @Then("I check if task with title {string} is marked as completed")
    fun iCheckIfTaskWithTitleIsMarkedAsCompleted(taskTitle: String) {
        onView(allOf(withId(R.id.complete_checkbox), hasSibling(withText(taskTitle))))
            .check(ViewAssertions.matches(isChecked()))
    }

    @Then("I check if task with title {string} is marked as active")
    fun iCheckIfTaskWithTitleIsMarkedAsActive(taskTitle: String) {
        onView(allOf(withId(R.id.complete_checkbox), hasSibling(withText(taskTitle))))
            .check(ViewAssertions.matches(not(isChecked())))
    }
    @And("I open context menu")
    fun iOpenContextMenu(){
        openContextualActionModeOverflowMenu()
    }

    @And("I close the app")
    fun iCloseTheApp() {
        scenarioHolder.close()
    }

}