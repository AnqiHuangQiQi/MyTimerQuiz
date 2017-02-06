package com.example.huanganqi.myquiz;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by huanganqi on 2016-11-18.
 */

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class Quiz_uiautomator {

    private static final String BASIC_SAMPLE_PACKAGE
            = "com.example.huanganqi.myquiz";

    private static final int LAUNCH_TIMEOUT = 5000;

    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        // Launch the blueprint app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void checkPreconditions() {

        //test if app has been started correctly
        assertThat(mDevice, notNullValue());
    }



    @Test
    public void QuizM_Test() {

        assertThat(mDevice, notNullValue());

        String abc="";
        try {
            //test add-user function
            UiObject IdItem = mDevice.findObject(new UiSelector()
                    .packageName(BASIC_SAMPLE_PACKAGE).
                            resourceId("com.example.huanganqi.myquiz:id/Id"));
            UiObject passItem = mDevice.findObject(new UiSelector()
                    .packageName(BASIC_SAMPLE_PACKAGE).
                            resourceId("com.example.huanganqi.myquiz:id/password"));




            IdItem.setText("huang");
            passItem.setText("anqi");
            System.out.println("set text success");
            abc = IdItem.getText().toString();
        }catch(UiObjectNotFoundException e){
            System.out.println("Id not found");
        }

        assertTrue(abc.equals("huang"));

        UiObject okButton = mDevice.findObject(new UiSelector()
                .text("Login").className("android.widget.Button"));


        try {
            if (okButton.exists() && okButton.isEnabled()) {
                okButton.click();
            }
        }catch(UiObjectNotFoundException e){
            System.out.println("Login not found");
            e.printStackTrace();
        }


        UiObject UserItem = mDevice.findObject(new UiSelector()
                .packageName(BASIC_SAMPLE_PACKAGE).
                        resourceId("com.example.huanganqi.myquiz:id/user_id"));
        try{
            abc = UserItem.getText().toString();
        }catch(UiObjectNotFoundException e){
            e.printStackTrace();
        }
        assertTrue(abc.equals("huan"));


    }

    @Test
    public void QuizT_Test() {

        assertThat(mDevice, notNullValue());

        String abc="";
        try {
            //test add-user function
            UiObject IdItem = mDevice.findObject(new UiSelector()
                    .packageName(BASIC_SAMPLE_PACKAGE).
                            resourceId("com.example.huanganqi.myquiz:id/Id"));
            UiObject passItem = mDevice.findObject(new UiSelector()
                    .packageName(BASIC_SAMPLE_PACKAGE).
                            resourceId("com.example.huanganqi.myquiz:id/password"));




            IdItem.setText("jueji");
            passItem.setText("yinchen");
            System.out.println("set text success");
            abc = IdItem.getText().toString();
        }catch(UiObjectNotFoundException e){
            System.out.println("Id not found");
        }

        assertTrue(abc.equals("jueji"));

        UiObject okButton = mDevice.findObject(new UiSelector()
                .text("Login").className("android.widget.Button"));


        try {
            if (okButton.exists() && okButton.isEnabled()) {
                okButton.click();
            }
        }catch(UiObjectNotFoundException e){
            System.out.println("Login not found");
            e.printStackTrace();
        }

        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT*5);

        UiObject saveButton = mDevice.findObject(new UiSelector()
                .text("Save and Close").className("android.widget.Button"));


        try {
            if (okButton.exists() && okButton.isEnabled()) {
                okButton.click();
            }
        }catch(UiObjectNotFoundException e){
            System.out.println("Save Button not found");
            e.printStackTrace();
        }

        assertThat(mDevice, notNullValue());

    }


}
