package net.gongmingqm10.calendar.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import net.gongmingqm10.calendar.R;
import net.gongmingqm10.calendar.adapter.CalendarListAdapter;
import net.gongmingqm10.calendar.util.DateUtil;

import org.joda.time.LocalDate;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

public class VerticalCalendarActivity extends ActionBarActivity {

    @InjectView(R.id.calendar_list)
    protected ListView calendarList;
    
    private CalendarListAdapter calendarListAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_calendar);
        ButterKnife.inject(this);
        
        calendarListAdapter = new CalendarListAdapter(this, listener, DateUtil.getTodaysDate(), null);
        calendarList.setAdapter(calendarListAdapter);
    }
    
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LocalDate selectedDate = (LocalDate) view.getTag();
            Toast.makeText(VerticalCalendarActivity.this, "Selected date: " + selectedDate.toString(), Toast.LENGTH_SHORT).show();
            calendarListAdapter.updateSelection(selectedDate, null);
        }
    };
    
}
