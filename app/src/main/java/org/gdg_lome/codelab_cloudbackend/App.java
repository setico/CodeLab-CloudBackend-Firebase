package org.gdg_lome.codelab_cloudbackend;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by setico on 26/12/2015.
 */
public class App extends Application {
    public static Firebase backend;
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
        backend = new Firebase(Config.BACKEND_URL);
    }
}
