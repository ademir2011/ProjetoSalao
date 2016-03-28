package br.com.onedreams.projetosalao.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.io.ByteArrayOutputStream;

import br.com.onedreams.projetosalao.Classes.LibraryClass;
import br.com.onedreams.projetosalao.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    @Bind(R.id.ivImageCamera) ImageView ivImageCamera;
    @Bind(R.id.fabCamera) FloatingActionButton mFabCamera;
    @Bind(R.id.ivBackgroundCamera) ImageView ivBackgroundCamera;

    String base64String;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ButterKnife.bind(this);

        startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAMERA_REQUEST);

        firebase = LibraryClass.getFirebase();

    }

    @OnClick(R.id.fabCamera)
    public void fabCameraClick(View v) {

        Toast.makeText(getApplicationContext(), "Enviado", Toast.LENGTH_SHORT).show();
    }

    /**
     * Método chamado quado a foto é tirada
     * @param requestCode
     * @param resultCode
     * @param data
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            Bundle bundle = data.getExtras();
            if (bundle != null){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                ivImageCamera.setImageBitmap(bitmap);
                ivBackgroundCamera.setBackground( new BitmapDrawable(getResources(), bitmap) );

                base64String = bitmapToBase64(bitmap);

                Log.v("base64String", base64String);
            }
        }
    }

    /**
     * Converte o bitmap para base 64
     * @param bitmap
     * @return
     */

    private String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    /**
     * Converte a base64 para bitmap
     * @param b64
     * @return
     */

    private Bitmap base64ToBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }

}
