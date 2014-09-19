package com.dharmik.myhell;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpenseListActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_list);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_list, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
		
			View rootView = inflater.inflate(R.layout.fragment_expense_list,
					container, false);
			
			
			NowLayout nl=(NowLayout)rootView.findViewById(R.id.nowLayout);
			
			/*
			for(int l=0; l<=10; l++){
				nl.addView(RenderLayout());
			}
			*/
			HellSqlLiteHelper db=new HellSqlLiteHelper(getActivity());
		
			List<Expense> expenses = new LinkedList<Expense>();
			expenses = db.GetAllExpenses();
			
			if(!expenses.isEmpty())
			{
				
				for(int i=0; i<expenses.size(); i++){
					nl.addView(RenderLayout(expenses.get(i)));
				}
				
			}
			
				
			
			return rootView;
		}
		
		public LinearLayout RenderLayout(Expense expense)
		{
			int categoryImageId=R.drawable.ic_shopping;
			String categoryName="No Category";
			
			if(expense.getCategoryId() > 0){
			
			switch (expense.getCategoryId()) {
			case (1):
				categoryName = "Shopping!";
				categoryImageId=R.drawable.ic_shopping;
		    break;
		    case (2):
		    	categoryName = "Recharge!";
		    	categoryImageId=R.drawable.ic_recharge;
		    break;
		    case (3):
		    	categoryName = "Food And Drinks!";
		    	categoryImageId=R.drawable.ic_food;
		    break;
		    case (4):
		    	categoryName = "Travelling!";
		    	categoryImageId=R.drawable.ic_travelling;
		    break;
		    case (5):
		    	categoryName = "Others!";
		    	categoryImageId=R.drawable.ic_others;
		    break;
		    }
			
			}
			
			//Rs.
			TextView txtRs=new TextView(getActivity());
			txtRs.setText("Rs.");
			txtRs.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			txtRs.setTextSize(25);
		
			//Amount
			TextView txtAmount=new TextView(getActivity());
			txtAmount.setText(String.valueOf(expense.getAmount()).split("\\.")[0]);
			txtAmount.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			txtAmount.setTextSize(90);
			
			//amount linear
			LinearLayout amountLinear=new LinearLayout(getActivity());
			amountLinear.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			amountLinear.setOrientation(LinearLayout.HORIZONTAL);
			amountLinear.addView(txtRs);
			amountLinear.addView(txtAmount);
			
			//Line View
			View lineView=new View(getActivity());
			LinearLayout.LayoutParams lp1=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,1);
			lineView.setBackgroundColor(Color.DKGRAY);
			lineView.setLayoutParams(lp1);
			
			//Below bar
			ImageView imageView=new ImageView(getActivity());
			imageView.setLayoutParams(new LayoutParams(52, 40));
			imageView.setBackgroundResource(categoryImageId);
			
				//Shopping text
				TextView txtShopping=new TextView(getActivity());
				txtShopping.setText(String.valueOf(categoryName));
				txtShopping.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
				txtShopping.setTextSize(12);
				txtShopping.setGravity(Gravity.CENTER);
				
				//Simple View
				View view=new View(getActivity());
				LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
				lp.weight=1;
				view.setLayoutParams(lp);
				
				//Date text
				TextView txtDate=new TextView(getActivity());
				txtDate.setText("Hello");
				txtDate.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
				txtDate.setTextSize(12);
				txtDate.setGravity(Gravity.CENTER);
				
				//Below bar linear
				LinearLayout belowBarLinear=new LinearLayout(getActivity());
				belowBarLinear.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT));
				belowBarLinear.setOrientation(LinearLayout.HORIZONTAL);
				belowBarLinear.setPadding(5,5,5,5);
				belowBarLinear.addView(imageView);
				belowBarLinear.addView(txtShopping);
				belowBarLinear.addView(view);
				belowBarLinear.addView(txtDate);
				
				//main Linear
				LinearLayout mainLinear=new LinearLayout(getActivity());
				mainLinear.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
				mainLinear.setOrientation(LinearLayout.VERTICAL);
				mainLinear.setBackgroundResource(R.drawable.search_bg_shadow);
				mainLinear.setGravity(Gravity.CENTER);
			//	mainLinear.setLayoutParams(new LayoutParams(200, 200));
				mainLinear.addView(amountLinear);
				mainLinear.addView(lineView);
				mainLinear.addView(belowBarLinear);
				
				
				//Now layout Component
				//NowLayout nl=new NowLayout(getActivity());
				//nl.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
				//nl.setBackgroundColor(Color.parseColor("#f0f0f0"));
				//nl.setOrientation(NowLayout.VERTICAL);
				
				return mainLinear;
		
		}
		
		public LinearLayout RenderLayout()
		{
			//Rs.
			TextView txtRs=new TextView(getActivity());
			txtRs.setText("Rs.");
			txtRs.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			txtRs.setTextSize(25);
		
			//Amount
			TextView txtAmount=new TextView(getActivity());
			txtAmount.setText("555");
			txtAmount.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			txtAmount.setTextSize(90);
			
			//amount linear
			LinearLayout amountLinear=new LinearLayout(getActivity());
			amountLinear.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			amountLinear.setOrientation(LinearLayout.HORIZONTAL);
			amountLinear.addView(txtRs);
			amountLinear.addView(txtAmount);
			
			//Line View
			View lineView=new View(getActivity());
			LinearLayout.LayoutParams lp1=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,1);
			lineView.setBackgroundColor(Color.DKGRAY);
			lineView.setLayoutParams(lp1);
			
			//Below bar
			ImageView imageView=new ImageView(getActivity());
			imageView.setLayoutParams(new LayoutParams(52, 40));
			imageView.setBackgroundResource(R.drawable.ic_shopping);
			
				//Shopping text
				TextView txtShopping=new TextView(getActivity());
				txtShopping.setText("Shopping");
				txtShopping.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
				txtShopping.setTextSize(12);
				txtShopping.setGravity(Gravity.CENTER);
				
				//Simple View
				View view=new View(getActivity());
				LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
				lp.weight=1;
				view.setLayoutParams(lp);
				
				//Date text
				TextView txtDate=new TextView(getActivity());
				txtDate.setText("9:00 pm on 1 Aug,2014");
				txtDate.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
				txtDate.setTextSize(12);
				txtDate.setGravity(Gravity.CENTER);
				
				//Below bar linear
				LinearLayout belowBarLinear=new LinearLayout(getActivity());
				belowBarLinear.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT));
				belowBarLinear.setOrientation(LinearLayout.HORIZONTAL);
				belowBarLinear.setPadding(5,5,5,5);
				belowBarLinear.addView(imageView);
				belowBarLinear.addView(txtShopping);
				belowBarLinear.addView(view);
				belowBarLinear.addView(txtDate);
				
				//main Linear
				LinearLayout mainLinear=new LinearLayout(getActivity());
				mainLinear.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
				mainLinear.setOrientation(LinearLayout.VERTICAL);
				mainLinear.setBackgroundResource(R.drawable.search_bg_shadow);
				mainLinear.setGravity(Gravity.CENTER);
			//	mainLinear.setLayoutParams(new LayoutParams(200, 200));
				mainLinear.addView(amountLinear);
				mainLinear.addView(lineView);
				mainLinear.addView(belowBarLinear);
				
				
				//Now layout Component
				//NowLayout nl=new NowLayout(getActivity());
				//nl.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
				//nl.setBackgroundColor(Color.parseColor("#f0f0f0"));
				//nl.setOrientation(NowLayout.VERTICAL);
				
				return mainLinear;
		
		}
		
	}

}
