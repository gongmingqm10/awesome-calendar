package net.gongmingqm10.calendar.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import net.gongmingqm10.calendar.R;
import net.gongmingqm10.calendar.adapter.CalendarPagerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GoogleCalendarActivity extends ActionBarActivity {

    @InjectView(R.id.calendar_pager)
    protected ViewPager calendarPager;
    
    @InjectView(R.id.calendar_list)
    protected ListView calendarList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_calendar);
        ButterKnife.inject(this);
        
        calendarPager.setAdapter(new CalendarPagerAdapter(this));
    }

}
