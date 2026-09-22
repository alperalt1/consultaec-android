package org.universalworldtechnologyec.cedulamovil

import android.graphics.Bitmap
import android.os.Environment
import androidx.test.platform.app.InstrumentationRegistry
import java.io.File
import java.io.FileOutputStream

object ScreenshotHelper {

    fun takeScreenshot(name: String) {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val bitmap: Bitmap = instrumentation.uiAutomation.takeScreenshot()
        val picturesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val screenshotsFolder = File(picturesDir, "AppScreenshots")

        if (!screenshotsFolder.exists()) {
            screenshotsFolder.mkdirs()
        }

        val file = File(screenshotsFolder, "$name.png")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
    }
}