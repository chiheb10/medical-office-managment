package com.example.admin.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;

public class calendar_d extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_d);
        CalendarView calendarView=findViewById(R.id.calendarView);

        final int cyear = Calendar.getInstance().get(Calendar.YEAR);
        final int cmonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        final int cday = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        final String username=getIntent().getExtras().getString("d_username");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int month1=month+1;
                String date = dayOfMonth + "/" + month1 +  "/" + year;

                String day="";
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                switch (dayOfWeek) {
                    case Calendar.SUNDAY:
                        day="sunday";
                        break;
                    case Calendar.MONDAY:
                        day="monday";
                        break;
                    case Calendar.TUESDAY:
                        day="tuesday";
                        break;
                    case Calendar.WEDNESDAY:
                        day="wednesday";
                        break;
                    case Calendar.THURSDAY:
                        day="thursday";
                        break;
                    case Calendar.FRIDAY:
                        day="friday";
                        break;
                    case Calendar.SATURDAY:
                        day="saturday";
                        break;
                }
                int dayOf = calendar.get(Calendar.DAY_OF_MONTH);
                int monthof = calendar.get(Calendar.MONTH) + 1;
                int yearof = calendar.get(Calendar.YEAR);
                String che = "yes";
                if (cyear > yearof) {
                    che = "no";
                } else if (cyear == yearof) {
                    if (cmonth > monthof) {
                        che = "no";
                    } else if (cmonth == monthof) {
                        if (cday > dayOf) {
                            che = "no";
                        }
                    }

                }

                if(day.equals("sunday") || day.equals("saturday")){
                    Toast.makeText(calendar_d.this,"it's holiday",Toast.LENGTH_LONG).show();}
                else{
                    Intent intent=new Intent(calendar_d.this,scheduler_doctor.class);
                    intent.putExtra("date",date);
                    intent.putExtra("username",username);
                    if (che.equals("no")) {
                        Toast.makeText(calendar_d.this, "please choose a new date ", Toast.LENGTH_LONG).show();
                    } else {
                        startActivity(intent);
                    }
                    //startActivity(intent);
                    }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater() ;
        inflater.inflate(R.menu.drawermenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id_m = item.getItemId();
        Intent i = getIntent();
        String username = i.getExtras().getString("d_username");
        if(id_m==R.id.db){Intent intent=new Intent(calendar_d.this,doctorAreaActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);}
        if(id_m==R.id.lo){startActivity(new Intent(calendar_d.this,loginActivity.class));}
        if(id_m==R.id.set){}



        return super.onOptionsItemSelected(item);
    }
}
