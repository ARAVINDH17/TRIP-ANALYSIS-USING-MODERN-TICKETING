package com.example.smartbus;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Wallet extends Activity {
	
	TextView tv;
	Button out,pay,sub;
	String ramount,qr;
	int tamount;
	String from,username,qrvalue,amount;
	String to;
	String time,busno,cost;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wallet);
		
		tv=(TextView)findViewById(R.id.textView1);
		pay =(Button)findViewById(R.id.button2);
		out=(Button)findViewById(R.id.button3);
		
		
		
		ramount=getIntent().getStringExtra("amount");
		username=getIntent().getStringExtra("username");
		from=getIntent().getStringExtra("from");
		to=getIntent().getStringExtra("to");
		time=getIntent().getStringExtra("time");
		busno=getIntent().getStringExtra("busno");
		time=getIntent().getStringExtra("time");
		cost=getIntent().getStringExtra("cost");
		qrvalue=getIntent().getStringExtra("qrvalue");
		
		
		Toast.makeText(getApplicationContext(), cost, Toast.LENGTH_LONG).show();
		
		tamount=500;
		
		pay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				
				int tamount1=tamount-Integer.parseInt(cost);
				String amountresult=String.valueOf(tamount1);
				
				//int c=tamount-tamount1;
			tv.setText("Balance amount is  "+amountresult);
			Toast.makeText(getApplicationContext(), "Balance amount is "+amountresult , Toast.LENGTH_LONG).show();
				
				
			}
		});
		
		
		out.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				ActiveTask1 task1=new ActiveTask1();
				task1.execute();	
				
			}
		});
		
		
	}
	private class ActiveTask1 extends AsyncTask<String,Void,Void> {
//	String s1=username.getText().toString();
//		String s2=p.getText().toString();
//		String s4=e.getText().toString();
//		String s5=p.getText().toString();
	String res=null;
		@Override
		protected void onPreExecute() {
			
		}

		@Override
		protected Void doInBackground(String... params) {
			res=Callservice.bookService(qrvalue,username,busno,from,to,time,cost,"booked");
			return null;
			
		}
	
	protected void onPostExecute(Void result) {
	
		if(res.equals("success")) {

				
		
					Intent intent=new Intent(getBaseContext(),Userlogin.class);
					startActivity(intent);
			
		}
	}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wallet, menu);
		return true;
	}

}
