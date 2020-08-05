package com.example.android.devbyteviewer.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.widget.TextView
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.*
import com.example.android.devbyteviewer.R
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UiAutomatorTests {
    @get:Rule
    val activityRule = ActivityTestRule<DevByteActivity>(DevByteActivity::class.java)
    private lateinit var uiDevice: UiDevice

    @Before
    fun setup() {
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        // Start from the home screen
        uiDevice.pressHome();

        uiDevice.wait(Until.hasObject(By.pkg(getLauncherPackageName()).depth(0)), 1000);
    }

    private fun getLauncherPackageName(): String? {
        // Create launcher Intent
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)

        // Use PackageManager to get the launcher package name
        val pm: PackageManager = InstrumentationRegistry.getInstrumentation().context.packageManager
        val resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)

        return resolveInfo.activityInfo.packageName
    }

    @Test
    @Throws(UiObjectNotFoundException::class)
    fun checkDevByteApp() {
        // Simulate a short press on the HOME button.
        uiDevice.pressHome()

        // We’re now in the home screen. Next, we want to simulate
        // a user bringing up the All Apps screen.
        // If you use the uiautomatorviewer tool to capture a snapshot
        // of the Home screen, notice that the All Apps button’s
        // content-description property has the value “Apps”. We can
        // use this property to create a UiSelector to find the button.
        val allAppsButton: UiObject = uiDevice.findObject(UiSelector().description("Apps"))

        // Simulate a click to bring up the All Apps screen.
        allAppsButton.clickAndWaitForNewWindow()

        // In the All Apps screen, the Settings app is located in
        // the Apps tab. To simulate the user bringing up the Apps tab,
        // we create a UiSelector to find a tab with the text
        // label “Apps”.
//        val appsTab: UiObject = uiDevice.findObject(UiSelector().text("Apps"))

        // Simulate a click to enter the Apps tab.
//        appsTab.click()

        // Next, in the apps tabs, we can simulate a user swiping until
        // they come to the Settings app icon. Since the container view
        // is scrollable, we can use a UiScrollable object.
        var appViews = UiScrollable(
                UiSelector().scrollable(true))

        // Set the swiping mode to horizontal (the default is vertical)
        appViews.setAsHorizontalList()

        // create a UiSelector to find the Settings app and simulate
        // a user click to launch the app.
        val devByteViewerApp = appViews
                .getChildByText(UiSelector()
                        .className(TextView::class.java.name),
                        activityRule.activity.resources.getString(R.string.app_name))
        devByteViewerApp.clickAndWaitForNewWindow()

        // Validate that the package name is the expected one
        val devByteViewerValidation = uiDevice
                .findObject(
                        UiSelector()
                                .packageName(activityRule.activity.applicationContext.packageName)
                )

        assertThat(devByteViewerValidation.exists(), equalTo(true))

        val recyclerView = uiDevice.findObject(
                UiSelector()
                        .className("androidx.recyclerview.widget.RecyclerView"))

        recyclerView.swipeDown(7)

        val frameToClick = uiDevice.findObject(
                UiSelector()
                        .className("android.widget.FrameLayout")
                        .childSelector(
                                UiSelector().className("android.view.ViewGroup")
                        ))

        frameToClick.clickAndWaitForNewWindow()
        uiDevice.pressHome()
        uiDevice.findObject(UiSelector().description("Apps"))
                .clickAndWaitForNewWindow()

        appViews = UiScrollable(UiSelector().scrollable(true))
                .setAsVerticalList()

        val sleepTracker = appViews
                .getChildByText(
                        UiSelector()
                                .className(TextView::class.java.name),
                        "Track My Sleep Quality")

        sleepTracker.clickAndWaitForNewWindow()

        var stopButton = uiDevice.findObject(UiSelector().text("STOP"))

        assertThat(stopButton.isEnabled, equalTo(false))

        val startButton = uiDevice.findObject(UiSelector().text("START"))

        startButton.clickAndWaitForNewWindow()
        assertThat(startButton.isEnabled, equalTo(false))

        stopButton = uiDevice.findObject(UiSelector().text("STOP"))

        stopButton.clickAndWaitForNewWindow()
        uiDevice.findObject(UiSelector().description("Sleep Quality 0"))
                .clickAndWaitForNewWindow()
        uiDevice.findObject(UiSelector().text("CLEAR")).clickAndWaitForNewWindow()
    }

}
