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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;

public class calendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarView calendarView = findViewById(R.id.calendarView);
        final int cyear = Calendar.getInstance().get(Calendar.YEAR);
        final int cmonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        final int cday = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        String id_user=getIntent().getExtras().getString("id_user");
        Intent intent=new Intent(calendar.this,scheduler_user.class);
        intent.putExtra("id_user",id_user);

        final Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        final String todayString = formatter.format(currentTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            Date cdate = format.parse(todayString);
            //System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final int id = getIntent().getExtras().getInt("id");
        if (id == 0) {
            Toast.makeText(calendar.this, "yes", Toast.LENGTH_LONG).show();
        }
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int month1 = month + 1;
                String date = dayOfMonth + "/" + month1 + "/" + year;

                String day = "";
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                switch (dayOfWeek) {
                    case Calendar.SUNDAY:
                        day = "sunday";
                        break;
                    case Calendar.MONDAY:
                        day = "monday";
                        break;
                    case Calendar.TUESDAY:
                        day = "tuesday";
                        break;
                    case Calendar.WEDNESDAY:
                        day = "wednesday";
                        break;
                    case Calendar.THURSDAY:
                        day = "thursday";
                        break;
                    case Calendar.FRIDAY:
                        day = "friday";
                        break;
                    case Calendar.SATURDAY:
                        day = "saturday";
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


                if (day.equals("sunday") || day.equals("saturday")) {
                    Toast.makeText(calendar.this, "it's holiday", Toast.LENGTH_LONG).show();
                } else {
                    //Toast.makeText(calendar.this,che+Integer.toString(cmonth)+Integer.toString(monthof),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(calendar.this, scheduler_user.class);
                    intent.putExtra("date", date);
                    intent.putExtra("id", id);
                    if (che.equals("no")) {
                        Toast.makeText(calendar.this, "please choose a new date ", Toast.LENGTH_LONG).show();
                    } else {
                        startActivity(intent);
                    }
                    //startActivity(intent);}
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
        String id = i.getExtras().getString("id_user");
        if(id_m==R.id.db){Intent intent=new Intent(calendar.this,user.class);
            intent.putExtra("id",id);
            startActivity(intent);}
        if(id_m==R.id.lo){startActivity(new Intent(calendar.this,loginActivity.class));}
        if(id_m==R.id.set){}



        return super.onOptionsItemSelected(item);
    }

        //if(id==1){Toast.makeText(calendar.this,"yes",Toast.LENGTH_LONG).show();}
    }

