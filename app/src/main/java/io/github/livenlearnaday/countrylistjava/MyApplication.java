package io.github.livenlearnaday.countrylistjava;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.squareup.leakcanary.LeakCanary;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;


//for Timber logging and others
public class MyApplication extends Application {


    public void onCreate() {
        super.onCreate();


        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                //Add the line number to the tag
                @Override
                protected String createStackElementTag(@NotNull StackTraceElement element) {
                    return super.createStackElementTag(element) + ": " + element.getLineNumber();
                }
            });
        } else {
            //Release mode
            Timber.plant(new ReleaseLogTree());
        }
        

    }


    private static class ReleaseLogTree extends Timber.Tree {

        @Override
        protected void log(int priority, String tag, @NonNull String message,
                           Throwable throwable) {
            if (priority == Log.DEBUG || priority == Log.VERBOSE || priority == Log.INFO) {
                return;
            }

            if (priority == Log.ERROR) {
                if (throwable == null) {
                    Timber.e(message);
                } else {
                    Timber.e(throwable, message);
                }
            }
        }
    }



}
