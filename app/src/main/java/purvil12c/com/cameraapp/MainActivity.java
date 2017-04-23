package purvil12c.com.cameraapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1;
    private File imageFile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button photoButton = (Button) this.findViewById(R.id.button1);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"ci.jpg");
                //Uri imageUri=Uri.fromFile(imageFile);
                Uri imageUri = android.support.v4.content.FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", imageFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
                startActivityForResult(intent, CAMERA_REQUEST);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==CAMERA_REQUEST){
            switch (resultCode){
                case Activity.RESULT_CANCELED:
                    Toast.makeText(getApplicationContext(),"Error saving image",Toast.LENGTH_LONG).show();
                    break;
                case Activity.RESULT_OK:

                    if(imageFile.exists()){
                        Toast.makeText(getApplicationContext(),"File saved at "+imageFile.getAbsolutePath(),Toast.LENGTH_LONG).show();
                    }

                    break;
                default:
                    break;
            }
        }

    }
}
