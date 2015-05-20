package net.gongmingqm10.calendar.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
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

public class CalendarPagerAdapter extends PagerAdapter {
    private LocalDate startDate;
    private Context context;
    private View.OnClickListener listener;

    public CalendarPagerAdapter(Context context, LocalDate startDate, View.OnClickListener listener) {
        this.context = context;
        this.startDate = startDate;
        this.listener = listener;
    }
    
    public void updateSelectionDate(LocalDate startDate) {
        this.startDate = startDate;
        notifyDataSetChanged();
    }

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

        CalendarCard calendarView = (CalendarCard) LayoutInflater.from(context).inflate(R.layout.calendar_card_view, container, false);
        calendarView.populate(displayCalendar, startDate, null);
        calendarView.setOnClickListener(listener);

        container.addView(calendarView);
        return calendarView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((CalendarCard) object);
    }
}
