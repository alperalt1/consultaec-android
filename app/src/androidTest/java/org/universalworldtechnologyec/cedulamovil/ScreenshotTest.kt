package org.universalworldtechnologyec.cedulamovil

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tools.fastlane.screengrab.Screengrab
import tools.fastlane.screengrab.cleanstatusbar.CleanStatusBar
import tools.fastlane.screengrab.locale.LocaleTestRule


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ScreenshotTest {

    @Test
    fun tomarCapturas() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(4000)
        ScreenshotHelper.takeScreenshot("01_pantalla_principal")
        scenario.close()
    }
}