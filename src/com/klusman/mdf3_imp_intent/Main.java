package com.klusman.mdf3_imp_intent;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;

import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	TextView tv;
    Button btnADD;
    Button btnSUB;
    ImageView img;
    Bitmap image;
    int alpha;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        
        img = (ImageView) findViewById(R.id.imageView1);
        Bundle extras = getIntent().getExtras();
        
        
        
        tv = (TextView)findViewById(R.id.textView1);
        btnADD = (Button)findViewById(R.id.btnPLUS);
        btnSUB = (Button)findViewById(R.id.btnMINUS);
        
        alpha = (int) ((img.getAlpha())*100);
        String newTV = String.valueOf(alpha);
        tv.setText(String.valueOf(newTV));
        
        if(extras != null){
        	img.setImageURI((Uri) getIntent().getExtras().get(Intent.EXTRA_STREAM));  // using the URI to get the image location
        	
        	
        } else {
        	myToast("No Image Found");
        }
        
        
        btnADD.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int myNum = 0;
				Log.i("BUTTON-UP", "STEP 1 Hit");
				try {
					myNum = Integer.parseInt(tv.getText().toString());
					if(myNum < 255){
						myNum = myNum + 5;
						tv.setText(String.valueOf(myNum));
						float i = myNum * 0.01f;
						Log.i("FLOAT VALUE", String.valueOf(i));
						img.setAlpha(myNum);
						
					}
				} catch(NumberFormatException nfe) {
					System.out.println("Could not parse " + nfe);
				} 
				
			}
		});
        
        
        btnSUB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int myNum = 0;
				Log.i("BUTTON-DOWN", "STEP 1 Hit");
				try {
					myNum = Integer.parseInt(tv.getText().toString());
					if(myNum > 0){
					myNum = myNum - 5;
					tv.setText(String.valueOf(myNum));
					img.setAlpha(myNum);
					}
				} catch(NumberFormatException nfe) {
					System.out.println("Could not parse " + nfe);
				} 
				
			}
		});
        
        
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    public void myToast(String text){  // Toast Template
		CharSequence textIN = text;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(Main.this, textIN, duration);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	};// end myToast
	
	

	
}

//Uri imageUri = (Uri) getIntent().getExtras().get(Intent.EXTRA_STREAM);
//        	try {
//        	//	Bitmap bmp=BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
//				Bitmap bitIMG = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
//				img.setImageBitmap(bmp);
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//        	