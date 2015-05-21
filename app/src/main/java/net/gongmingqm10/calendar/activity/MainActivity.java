package net.gongmingqm10.calendar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        String[] items = new String []{getString(R.string.title_activity_horizontal_calendar),
                getString(R.string.title_activity_vertical_calendar),
                getString(R.string.title_activity_depart_return)};
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));
    }

    @OnItemClick(R.id.calendar_list)
    protected void openActivity(int position) {
        Class clazz;
        switch (position) {
            case 0:
                clazz = HorizontalCalendarActivity.class;
                break;
            case 1:
                clazz = VerticalCalendarActivity.class;
                break;
            case 2:
                clazz = StartEndDateActivity.class;
                break;
            default:
                return;
        }
        startActivity(new Intent(this, clazz));
    }

}
