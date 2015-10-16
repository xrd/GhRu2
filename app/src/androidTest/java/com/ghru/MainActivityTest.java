package com.ghru;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;
import org.hamcrest.Matchers.*;

import org.junit.Before;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by xrdawson on 10/16/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mainActivity;

    public MainActivityTest() {
        super( MainActivity.class );
    }

    public void testLoaded() {
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mainActivity = getActivity();
        onView( withId( R.id.login_status ))
                .check(matches(withText("Hello World!")));
    }
}
