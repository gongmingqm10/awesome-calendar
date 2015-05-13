package net.gongmingqm10.calendar.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import net.gongmingqm10.calendar.R;
import net.gongmingqm10.calendar.adapter.CalendarListAdapter;
import net.gongmingqm10.calendar.util.DateUtil;

import org.joda.time.LocalDate;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class StartEndDateActivity extends ActionBarActivity {

    @InjectView(R.id.depart_date)
    protected TextView startDateText;
    
    @InjectView(R.id.return_date)
    protected TextView endDateText;
    
    @InjectView(R.id.calendar_list)
    protected ListView calendarList;
    
    private boolean isSelectingStart = true;
    private LocalDate startDate = DateUtil.getTodaysDate();
    private LocalDate endDate = DateUtil.getTodaysDate().plusDays(3);
    private CalendarListAdapter calendarListAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_end);
        ButterKnife.inject(this);
        
        updateDateView();
        calendarListAdapter = new CalendarListAdapter(this, listener, startDate, endDate);
        calendarList.setAdapter(calendarListAdapter);
    }
    
    private void updateDateView() {
        if (startDate == null) {
            startDateText.setTextColor(getResources().getColor(R.color.grey_normal));
            startDateText.setText(getString(R.string.select_date));
        } else {
            startDateText.setTextColor(getResources().getColor(R.color.grey_black));
            startDateText.setText(startDate.toString());
        }
        
        if (endDate == null) {
            endDateText.setTextColor(getResources().getColor(R.color.grey_normal));
            endDateText.setText(R.string.select_date);
        } else {
            endDateText.setTextColor(getResources().getColor(R.color.grey_black));
            endDateText.setText(endDate.toString());
        }
    }
    
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LocalDate selectedDate = (LocalDate) view.getTag();

            if (isSelectingStart) {
                startDate = selectedDate;
                endDate = null;
                isSelectingStart = false;
            } else {
                if (selectedDate.isBefore(startDate)) {
                    startDate = selectedDate;
                    endDate = null;
                    isSelectingStart = false;
                } else {
                    endDate = selectedDate;
                    isSelectingStart = true;
                }
            }
            updateDateView();
            calendarListAdapter.updateSelection(startDate, endDate);
        }
    };
}
