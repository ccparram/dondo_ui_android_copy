package com.dondo.demo.test

import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule

abstract class TestBaseActivityTest {

    @Rule
    @JvmField
    val ruleActivity = ActivityScenarioRule(TestBaseActivity::class.java)

    fun restartActivity() {
        ruleActivity.scenario.apply {
            moveToState(Lifecycle.State.RESUMED)
            recreate()
        }
    }
}
