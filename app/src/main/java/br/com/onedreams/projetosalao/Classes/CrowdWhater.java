package br.com.onedreams.projetosalao.Classes;

import com.firebase.client.Firebase;

/**
 * Created by root on 19/03/16.
 */
public class CrowdWhater extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
