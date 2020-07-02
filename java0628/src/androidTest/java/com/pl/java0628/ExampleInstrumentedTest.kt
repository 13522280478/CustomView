package com.pl.java0628

import android.os.Parcel
import android.os.Parcelable
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Assert.*
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest() : Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.pl.java0628.test", appContext.packageName)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ExampleInstrumentedTest> {
        override fun createFromParcel(parcel: Parcel): ExampleInstrumentedTest {
            return ExampleInstrumentedTest(parcel)
        }

        override fun newArray(size: Int): Array<ExampleInstrumentedTest?> {
            return arrayOfNulls(size)
        }
    }
}
