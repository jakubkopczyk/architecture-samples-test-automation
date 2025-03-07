package com.example.android.architecture.blueprints.todoapp.tasks.test

import androidx.compose.ui.test.junit4.createEmptyComposeRule
import io.cucumber.junit.WithJunitRule
import org.junit.Rule

@WithJunitRule
class ComposeRuleHolder {

    @get:Rule(order = 1)
    val composeRule = createEmptyComposeRule()
}