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

import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class DepartReturnActivity extends ActionBarActivity {

    @InjectView(R.id.depart_date)
    protected TextView departDateText;
    
    @InjectView(R.id.return_date)
    protected TextView returnDateText;
    
    @InjectView(R.id.calendar_list)
    protected ListView calendarList;
    
    private boolean isSelectingDepart = true;
    private LocalDate departDate = DateUtil.getTodaysDate();
    private LocalDate returnDate = DateUtil.getTodaysDate().plusDays(3);
    private CalendarListAdapter calendarListAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depart_return);
        ButterKnife.inject(this);
        
        updateDateView();
        calendarListAdapter = new CalendarListAdapter(this, listener, departDate, returnDate);
        calendarList.setAdapter(calendarListAdapter);
    }
    
    private void updateDateView() {
        if (departDate == null) {
            departDateText.setTextColor(getResources().getColor(R.color.grey_normal));
            departDateText.setText(getString(R.string.select_date));
        } else {
            departDateText.setTextColor(getResources().getColor(R.color.indigo_black));
            departDateText.setText(departDate.toString());
        }
        
        if (returnDate == null) {
            returnDateText.setTextColor(getResources().getColor(R.color.grey_normal));
            returnDateText.setText(R.string.select_date);
        } else {
            returnDateText.setTextColor(getResources().getColor(R.color.indigo_black));
            returnDateText.setText(returnDate.toString());
        }
    }
    
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LocalDate selectedDate = (LocalDate) view.getTag();

            if (isSelectingDepart) {
                departDate = selectedDate;
                returnDate = null;
                isSelectingDepart = false;
            } else {
                if (selectedDate.isBefore(departDate)) {
                    departDate = selectedDate;
                    returnDate = null;
                    isSelectingDepart = false;
                } else {
                    returnDate = selectedDate;
                    isSelectingDepart = true;
                }
            }
            updateDateView();
            calendarListAdapter.updateSelection(departDate, returnDate);
        }
    };
}
