package net.gongmingqm10.calendar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.gongmingqm10.calendar.R;

import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CalendarCard extends RelativeLayout {

    private TextView cardTitle;
    private LinearLayout cardGrid;
    private ArrayList<CalendarCell> cells = new ArrayList<>();

    private OnClickListener listener;

    public CalendarCard(Context context) {
        super(context);
    }

    public CalendarCard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarCard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        cardTitle = (TextView) findViewById(R.id.card_title);
        cardGrid = (LinearLayout) findViewById(R.id.card_grid);
        initWeekTitle();
        initCellDay();
    }

    private void initCellDay() {
        for (int y = 0; y < cardGrid.getChildCount(); y++) {
            LinearLayout rowWeek = (LinearLayout) cardGrid.getChildAt(y);
            for (int x = 0; x < rowWeek.getChildCount(); x++) {
                final CalendarCell cellDay = (CalendarCell) rowWeek.getChildAt(x);
                cellDay.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (listener != null) {
                            listener.onClick(view);
                        }
                    }
                });
                cells.add(cellDay);
            }
        }
    }

    private void initWeekTitle() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        LinearLayout cardDays = (LinearLayout) findViewById(R.id.card_days);
        for (int index = 0; index < cardDays.getChildCount(); index++) {
            TextView child = (TextView) cardDays.getChildAt(index);
            child.setText(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ENGLISH));
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }
    }

    public void populate(Calendar dateDisplay, LocalDate departDate, LocalDate returnDate) {
        cardTitle.setText(new SimpleDateFormat("MMM yyyy", Locale.ENGLISH).format(dateDisplay.getTime()));
        updateCells(dateDisplay, departDate, returnDate);
    }

    private int getDaySpacing(int dayOfWeek) {
        return Calendar.SUNDAY == dayOfWeek ? 0 : dayOfWeek - 1;
    }

    private void updateCells(Calendar dateDisplay, LocalDate departDate, LocalDate returnDate) {
        Integer counter = 0;
        Calendar calendar = (Calendar) dateDisplay.clone();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int daySpacing = getDaySpacing(calendar.get(Calendar.DAY_OF_WEEK));
        if (daySpacing > 0) {
            for (int i = 0; i < daySpacing; i++) {
                CalendarCell cell = cells.get(counter++);
                cell.setVisibility(View.INVISIBLE);
            }
        }

        int firstDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        int lastDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH) + 1;
        for (int i = firstDayOfMonth; i < lastDayOfMonth; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            CalendarCell cell = cells.get(counter++);
            cell.populate(calendar, departDate, returnDate);
        }

        calendar = (Calendar) dateDisplay.clone();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        daySpacing = 7 * 6 - counter;
        cardGrid.getChildAt(cardGrid.getChildCount() - 1).setVisibility( daySpacing < 7 ? View.VISIBLE : View.GONE);
        for (int i = 0; i < daySpacing; i++) {
            CalendarCell cell = cells.get(counter++);
            cell.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
}
