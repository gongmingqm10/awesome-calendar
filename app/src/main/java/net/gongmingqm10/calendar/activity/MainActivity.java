package net.gongmingqm10.calendar.activity;

import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import net.gongmingqm10.calendar.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.calendar_list)
    protected ListView listView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        
        setUpListView();
    }

    private void setUpListView() {
        Integer[] items = new Integer[]{R.string.title_activity_horizontal_calendar, 
                R.string.title_activity_vertical_calendar, 
                R.string.title_activity_select_date, 
                R.string.title_activity_depart_return, 
                R.string.title_activity_google_calendar};
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));
    }
    
    @OnItemClick(R.id.calendar_list)
    protected void openActivity(AdapterView adapterView, View view, int position) {
        PackageManager.
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
