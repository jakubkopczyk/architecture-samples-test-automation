package com.example.android.architecture.blueprints.todoapp.tasks.test

import android.app.Activity
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import io.cucumber.java.After

class ActivityScenarioHolder {

    private var scenario:ActivityScenario<*>? = null

    fun launch(intent:Intent){
        scenario = ActivityScenario.launch<Activity>(intent)
    }
    fun getScenario(): ActivityScenario<*>? {
        return scenario
    }

    /**
     *  Close activity after scenario
     */
    @After
    fun close(){
        scenario?.close()
    }
}