package net.gongmingqm10.calendar.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import net.gongmingqm10.calendar.R;
import net.gongmingqm10.calendar.util.DateUtil;

import org.joda.time.LocalDate;

import java.util.Calendar;

public class CalendarCell extends TextView {

    public CalendarCell(Context context) {
        super(context);
    }

    public CalendarCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    public void populate(Calendar calendar, LocalDate departDate, LocalDate returnDate) {
        LocalDate today = DateUtil.getTodaysDate();
        LocalDate calendarDate = DateUtil.getConvertedLocalDate(calendar);

        setTag(calendarDate);
        setVisibility(View.VISIBLE);
        setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));

        setEnabled(!(calendarDate.isBefore(today) || calendarDate.isAfter(DateUtil.getMaxShowDate())));
        setActivated(today.equals(calendarDate));

        updateSelection(departDate, returnDate);
    }
    
    public void updateSelection(LocalDate departDate, LocalDate returnDate) {
        
        LocalDate calendarDate = (LocalDate) getTag();

        if (DateUtil.localDateEquals(calendarDate, departDate) && DateUtil.localDateEquals(calendarDate, returnDate)) {
            updateCell(R.mipmap.calendar_selector_same_day, Typeface.BOLD, true);
        } else if (DateUtil.localDateEquals(calendarDate, departDate)) {
            int resId = returnDate == null ?
                    R.mipmap.calendar_selector_blue : R.mipmap.calendar_selector_blue_grey;
            updateCell(resId, Typeface.BOLD, true);
        } else if (DateUtil.localDateEquals(calendarDate, returnDate)) {
            int resId = departDate == null ?
                    R.mipmap.calendar_selector_green : R.mipmap.calendar_selector_green_grey;
            updateCell(resId, Typeface.BOLD, true);
        } else if (departDate != null &&
                returnDate != null &&
                calendarDate.isAfter(new LocalDate(departDate)) &&
                calendarDate.isBefore(new LocalDate(returnDate))) {
            updateCell(R.drawable.calendar_item_gray, Typeface.NORMAL, false);
        } else {
            updateCell(0, DateUtil.getTodaysDate().equals(calendarDate) ? Typeface.BOLD : Typeface.NORMAL, false);
        }        
    }

    private void updateCell(int resId, int textStyle, boolean selected) {
        setBackgroundResource(resId);
        setTypeface(null, textStyle);
        setSelected(selected);
    }
}
