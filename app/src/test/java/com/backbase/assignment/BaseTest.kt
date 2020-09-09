package com.backbase.assignment

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.backbase.assignment.ui.MyApplication
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
open class BaseTest : KoinTest {
    var context: Context? = ApplicationProvider.getApplicationContext<MyApplication>()

    @Before
    open fun beforeEach() {
        MockitoAnnotations.initMocks(this)
    }

    @After
    open fun afterEach() {
        stopKoin()
    }

    @Test
     fun runText(){
        Assert.assertEquals(2,2)
    }

}