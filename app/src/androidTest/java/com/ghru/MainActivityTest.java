package com.ghru;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;
import org.hamcrest.Matchers.*;

import org.junit.Before;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by xrdawson on 10/16/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super( MainActivity.class );
    }

    public void testLoaded() {
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        MainActivity mainActivity = getActivity();
//           onView( withId( R.id.submit ))
//                .check(doesNotExist());
        onView( withId( R.id.username ))
                .perform(typeText("BurningOnUp"));
        String password = mainActivity.getString(R.string.github_helper_password);
        onView(withId(R.id.password))
                        .perform(typeText(password));
        onView(withId(R.id.login))
                .perform(click());
//        try {
//            Thread.sleep(3000);
//        }
//        catch( Exception e ) {
//
//        }
//
        onView( withId( R.id.submit ))
                .check( matches(isDisplayed()) );
    }
}
