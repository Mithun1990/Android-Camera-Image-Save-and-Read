package com.example.cameraapplication;

import java.io.File;
import java.io.FileOutputStream;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CameraActivity extends Activity {
	private static final int CAMERA_REQUEST = 1888;
	Button captureImage;
	ImageView getImage;

	CameraHelper camera;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		captureImage = (Button) findViewById(R.id.button1);
		getImage = (ImageView) findViewById(R.id.imageView1);
		

		camera = new CameraHelper();

		captureImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent cameraIntent = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(cameraIntent, CAMERA_REQUEST);

			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
			Bitmap photo = (Bitmap) data.getExtras().get("data");
			File file = camera.saveImage("New_Image", "Image",
					System.currentTimeMillis(), photo);
			if (file != null) {
				Bitmap bitmap = camera.readImage(file);
				if (bitmap != null) {
					getImage.setImageBitmap(bitmap);
				}
			}

		}
	}

}
