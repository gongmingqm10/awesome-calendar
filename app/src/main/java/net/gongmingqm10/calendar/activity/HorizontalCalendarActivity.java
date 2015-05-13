package net.gongmingqm10.calendar.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import net.gongmingqm10.calendar.R;
import net.gongmingqm10.calendar.adapter.CalendarPagerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HorizontalCalendarActivity extends ActionBarActivity {

    @InjectView(R.id.horizontal_calendar_pager)
    protected ViewPager calendarPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_calendar);
        ButterKnife.inject(this);

        calendarPager.setAdapter(new CalendarPagerAdapter(this));
    }

}
