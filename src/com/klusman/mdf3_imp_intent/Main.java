package com.klusman.mdf3_imp_intent;



//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.ActivityNotFoundException;
//import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	TextView tv;
	TextView av;
	EditText etEmail;
	EditText etSub;
	EditText etMes;
	Button btnADD;
	Button btnSUB;
	Button btnANG_up;
	Button btnANG_dwn;
	Button btnSEND;
	ImageView img;
	Bitmap image;
	int alpha;
	float f = 0f;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);
		// Object obj = getIntent().getExtras().get(Intent.EXTRA_STREAM);

		img = (ImageView) findViewById(R.id.imageView1);
		Bundle extras = getIntent().getExtras();
		// File path =  get

		tv = (TextView)findViewById(R.id.textView1);
		av = (TextView)findViewById(R.id.angleTxt);
		btnADD = (Button)findViewById(R.id.btnPLUS);
		btnSUB = (Button)findViewById(R.id.btnMINUS);
		btnANG_up = (Button)findViewById(R.id.btnANG_UP);
		btnANG_dwn = (Button)findViewById(R.id.btnANG_DWN);
		btnSEND = (Button)findViewById(R.id.button1);
		etEmail = (EditText)findViewById(R.id.etAddress);
		etMes = (EditText)findViewById(R.id.etMessage);
		etSub = (EditText)findViewById(R.id.etSubject);

		//alpha = (int) ((img.getAlpha())*100);  
		alpha = (int) (img.getImageAlpha()); 
		String newTV = String.valueOf(alpha);
		tv.setText(String.valueOf(newTV));
		av.setText(String.valueOf(f));
		if(extras != null){
			img.setImageURI((Uri) getIntent().getExtras().get(Intent.EXTRA_STREAM));  // using the URI to get the image location


		} else {
			myToast("No Image Found");
		}


		btnADD.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				int myNum = 0;

				try {
					myNum = Integer.parseInt(tv.getText().toString());
					if(myNum < 255){
						myNum = myNum + 15;
						tv.setText(String.valueOf(myNum));
						img.setAlpha(myNum);

					}
				} catch(NumberFormatException nfe) {
					System.out.println("Could not parse " + nfe);
				} 

			}
		});


		btnSUB.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				int myNum = 0;

				try {
					myNum = Integer.parseInt(tv.getText().toString());
					if(myNum > 0){
						myNum = myNum - 15;
						tv.setText(String.valueOf(myNum));
						img.setAlpha(myNum);
					}
				} catch(NumberFormatException nfe) {
					System.out.println("Could not parse " + nfe);
				} 

			}
		});

		btnANG_dwn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				f = f - 15f;
				if(f == -360){
					f = 0f;
				}else if(f == 360){
					f = 0f;
				}
				av.setText(String.valueOf(f));
				Matrix matrix=new Matrix();
				img.setScaleType(ScaleType.MATRIX);   
				matrix.postRotate( f, img.getDrawable().getBounds().width()/2, img.getDrawable().getBounds().height()/2);
				img.setImageMatrix(matrix);


			}
		});

		btnANG_up.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				f = f + 15f;
				if(f == -360){
					f = 0f;
				}else if(f == 360){
					f = 0f;
				}

				av.setText(String.valueOf(f));
				Log.i("BUTTON-UP", "STEP 1 Hit");
				Matrix matrix=new Matrix();
				img.setScaleType(ScaleType.MATRIX);   
				matrix.postRotate( f, img.getDrawable().getBounds().width()/2, img.getDrawable().getBounds().height()/2);
				img.setImageMatrix(matrix);


			}
		});

		btnSEND.setOnClickListener(new View.OnClickListener() {  // COULDN'T GET it IN TIME  :(
			@Override
			public void onClick(View v) {
				myToast("SEND function still under construction");

/*
				String address = etEmail.getText().toString();
				File myFile;

				if(address.length()==0)  
				{
					AlertDialog.Builder ab=new AlertDialog.Builder(null);
					ab.setMessage("Email Address must not be empty!");
					ab.setPositiveButton("OK", new DialogInterface.OnClickListener(){

						@Override 
						public void onClick(DialogInterface dialog, int which) {

						}
					});
					ab.show();
				}
				else
				{

					ArrayList<Uri> uris = new ArrayList<Uri>();
					Uri u;
					Intent emailSession = new Intent(Intent.ACTION_SEND_MULTIPLE);
					emailSession.putExtra(Intent.EXTRA_SUBJECT,etSub.getText().toString());
					emailSession.setType("images/*");
					emailSession.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {address});
					emailSession.putExtra(android.content.Intent.EXTRA_TEXT,etMes.getText().toString());
					FileWriter fw;
					BufferedWriter bw;
					try{
						///  CAN'T FIGURE OUT HOW TO GET THE RIGH PATH TO THE IMAGE!
						myFile = new File( (Uri) getIntent().getExtras().get(Intent.EXTRA_STREAM));
						if(myFile.exists())
						{

							Uri u1 = Uri.fromFile(myFile);
							uris.add(u1);
							emailSession.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);

							startActivity(emailSession);
						}
					}
					catch (ActivityNotFoundException e)
					{
						Toast.makeText(getBaseContext(),  e.getMessage(), Toast.LENGTH_SHORT).show();
					}
				}*/
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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

