package com.example.cameraapplication;

import java.io.File;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class CameraHelper {
	
	public CameraHelper() {
	
	}
	
	public File saveImage(String directoryName, String imageName,
			long randomUniquePattern, Bitmap imageBitmap) {

		String root = Environment.getExternalStorageDirectory().toString();
		File myDir = new File(root + "/"+ directoryName);
		if (myDir.isDirectory()) {
			System.out.println("C");
		} else {
			myDir.mkdirs();
			System.out.println("D");
		}
		String fname = imageName + randomUniquePattern;
		File file = new File(myDir, fname + ".jpg");

		try {
			FileOutputStream out = new FileOutputStream(file);
			imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
			return file;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	public Bitmap readImage(File file){
		if(file.exists()){
			Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
			return bitmap;
		}else{
			return null;
		}
		
	}
}
