package br.com.onedreams.projetosalao.Classes;

import android.content.Context;
import android.content.SharedPreferences;

import com.firebase.client.Firebase;

/**
 * Created by root on 21/03/16.
 */
public final class LibraryClass {
    public static String PREF = "br.com.onedreams.projetosalao.PREF";
    private static Firebase firebase;

    private LibraryClass(){}

    public static Firebase getFirebase(){
        if( firebase == null ){
            firebase = new Firebase("https://bookbeauty.firebaseio.com");
        }
        return( firebase );
    }

    static public void saveSP(Context context, String key, String value ){
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    static public String getSP(Context context, String key ){
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        String token = sp.getString(key, "");
        return( token );
    }
}
