package net.gongmingqm10.calendar.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.gongmingqm10.calendar.R;
import net.gongmingqm10.calendar.util.DateUtil;
import net.gongmingqm10.calendar.view.CalendarCard;
import net.gongmingqm10.calendar.view.CalendarCell;

import org.joda.time.LocalDate;
import org.joda.time.Months;

import java.util.Calendar;

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
        
        calendarPager.setAdapter(new CalendarAdapter());
    }
    
    private class CalendarAdapter extends PagerAdapter {

        LocalDate startDate = DateUtil.getTodaysDate();

        @Override
        public int getCount() {
            return Months.monthsBetween(DateUtil.getTodaysDate(), DateUtil.getMaxShowDate()).getMonths() + 1;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Calendar displayCalendar = Calendar.getInstance();
            displayCalendar.add(Calendar.MONTH, position);
            
            CalendarCard calendarView = (CalendarCard) LayoutInflater.from(HorizontalCalendarActivity.this).inflate(R.layout.calendar_card_view, container, false);
            calendarView.populate(displayCalendar, startDate, null);
            calendarView.setOnClickListener(calendarItemListener);

            container.addView(calendarView);
            return calendarView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((CalendarCard) object);
        }
        
        private View.OnClickListener calendarItemListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarCell calendarCell = (CalendarCell) view;
                LocalDate selectedDate = (LocalDate) calendarCell.getTag();
                startDate = selectedDate;
             
                CalendarAdapter.this.notifyDataSetChanged();
                Toast.makeText(HorizontalCalendarActivity.this, "Selected date: " + selectedDate.toString(), Toast.LENGTH_SHORT).show();
            }
        };
    }

}
