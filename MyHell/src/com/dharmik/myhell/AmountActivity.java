package com.dharmik.myhell;

import java.sql.Date;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

public class AmountActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_amount);
		
		/*
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		*/

		/*
		// Create the text view
	    TextView textView = new TextView(this);
	    textView.setTextSize(40);
	    textView.setText("Welcome to hell...."+ GlobalVariables.CategoryId);

	    // Set the text view as the activity layout
	    setContentView(textView);
	    */
		
		/*
		TextView textView=(TextView)findViewById(R.id.txtRemark);
		textView.setText("Welcome to hell...."+ GlobalVariables.CategoryId);
		*/

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.amount, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	//Hide Soft keyboard when user taps anywhere
	@Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                                                        INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }
	
	public void sendAmount(View view)
	{
		Context context = getApplicationContext();
		//CharSequence text="Category";
		int duration = Toast.LENGTH_LONG;
		
		TextView txtAmount=(TextView)findViewById(R.id.txtAmount);
		TextView txtRemarks=(TextView)findViewById(R.id.txtRemark);
		
		//text="CategoryId:"+ GlobalVariables.CategoryId +" || Amount:" + txtAmount.getText().toString() +  " || Remarks:" + txtRemarks.getText().toString();
		
		
		Expense expense=new Expense();
		expense.setCategoryId(GlobalVariables.CategoryId);
		
		if(txtAmount.getText().toString()  == null || txtAmount.getText().toString().length() > 0) {

			expense.setAmount(0.0);

			} 
			else {

				expense.setAmount(Double.parseDouble(txtAmount.getText().toString()));

			}
		
		expense.setRemark(txtRemarks.getText().toString());
		expense.setUserId(1);
		expense.setCreatedDate(new Date(0));
		
		
		HellSqlLiteHelper db=new HellSqlLiteHelper(context);
		
		db.InsertExpense(expense);
		
	 	 
		
	    Toast toast = Toast.makeText(context,"Saved...", duration);
		toast.show();
		
		txtAmount.setText("");
		txtRemarks.setText("");
		
		txtAmount.requestFocus();

	}
	

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_amount,
					container, false);
			return rootView;
		}
	}
	
	

}
