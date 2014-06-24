package com.dharmik.myhell;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    
    public void getCategory(View view)
	{
		Context context = getApplicationContext();
		CharSequence text="Category";
		int duration = Toast.LENGTH_SHORT;

		switch (view.getId()) {
	    case (R.id.catRecharge):
	    	text = "Recharge!";
	    break;
	    case (R.id.catShopping):
	    	text = "Shopping!";
	    break;
	    case (R.id.catFoodAndDrinks):
	    	text = "Food And Drinks!";
	    break;
	    case (R.id.catTravelling):
	    	text = "Travelling!";
	    break;
	    case (R.id.catOthers):
	    	text = "Others!";
	    break;
	    }
		
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

}
