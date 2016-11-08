package com.example.puyed.planyourday;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView tvDatePlan;
    Button   btnChangeDatePlan;
    CustomDate mCurSelectedDate = new CustomDate();
    DatePickerDialog.OnDateSetListener mDatePickerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get needed controls object
        tvDatePlan = (TextView) findViewById(R.id.tvDatePlan);
        btnChangeDatePlan = (Button) findViewById(R.id.btnChangeDatePlan);

        // Set current date to tvDatePlan
        tvDatePlan.setText(getCurrentDate("vi", mCurSelectedDate));

        // Set the callback when user click ok:
        mDatePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //Change tvDatePlan if date changed from curent-selected-date
                if (mCurSelectedDate.getDayOfMonth() == dayOfMonth
                        && mCurSelectedDate.getMonth() == monthOfYear
                        && mCurSelectedDate.getYear() == year)
                    return;

                mCurSelectedDate.setDayOfMonth(dayOfMonth);
                mCurSelectedDate.setMonth(monthOfYear);
                mCurSelectedDate.setYear(year);

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                String day = getDayOfWeekInVi(calendar.get(Calendar.DAY_OF_WEEK));
                if (day == null)
                    tvDatePlan.setText(String.format("%d/%d/%d", dayOfMonth, (monthOfYear + 1), year));
                else
                    tvDatePlan.setText(String.format("%s, %d/%d/%d", day, dayOfMonth, (monthOfYear + 1), year));
            }
        };
    }

    // Get current date with format "d dd/MM/yyyy", with d is date-of-week show by language, which "vi" is Vietnamese
    private String getCurrentDate(String language, CustomDate customDate) {
        Calendar calendar = Calendar.getInstance();
        customDate.setDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
        customDate.setMonth(calendar.get(Calendar.MONTH));
        customDate.setYear(calendar.get(Calendar.YEAR));
        String curDate = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
        String day = null;
        if (language == "vi")
            day = getDayOfWeekInVi(calendar.get(Calendar.DAY_OF_WEEK));

        if (day == null)
            return curDate;
        else
            return day + ", " + curDate;
    }

    private String getDayOfWeekInVi(int dayOfWeek) {
        String dayInStr;
        switch (dayOfWeek) {
            case Calendar.MONDAY: {
                dayInStr = "Thứ hai";
                break;
            }
            case Calendar.TUESDAY: {
                dayInStr = "Thứ ba";
                break;
            }
            case Calendar.WEDNESDAY: {
                dayInStr = "Thứ tư";
                break;
            }
            case Calendar.THURSDAY: {
                dayInStr = "Thứ năm";
                break;
            }
            case Calendar.FRIDAY: {
                dayInStr = "Thứ sáu";
                break;
            }
            case Calendar.SATURDAY: {
                dayInStr = "Thứ bảy";
                break;
            }
            case Calendar.SUNDAY: {
                dayInStr = "Chủ nhật";
                break;
            }
            default: {
                dayInStr = null;
            }
        }
        return dayInStr;
    }

    public void onClickBtnChangeDatePlan(View view) {
        // Show calendar picker
        new DatePickerDialog(MainActivity.this, mDatePickerListener, mCurSelectedDate.getYear(), mCurSelectedDate.getMonth(), mCurSelectedDate.getDayOfMonth()).show();
    }
}
