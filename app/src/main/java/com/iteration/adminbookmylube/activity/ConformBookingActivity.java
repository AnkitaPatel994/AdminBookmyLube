package com.iteration.adminbookmylube.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iteration.adminbookmylube.R;
import com.iteration.adminbookmylube.network.GetProductDataService;
import com.iteration.adminbookmylube.network.RetrofitInstance;
import com.iteration.adminbookmylube.model.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConformBookingActivity extends AppCompatActivity {

    TextView txtCBookingName,txtRDate,txtCBookingVanplateno,txtCBookingEnginetype,txtCBookingMsgyear,txtCBookingModel,txtCBookingMake,txtCBookingEmail,txtCBookingPhoneNo,txtCBookingAddress,txtCServiceName,txtCBookingDate,txtCBookingVINno,txtCComment,txtCTimeSlott;
    Button btnDone,btnCancel;
    EditText txtPrice,txtConComment;
    String tvRDate;
    LinearLayout llBookingName,llBookingEmail,llBookingPhoneNo,llBookingTimeSlot,llBookingComment,llBookingVanplateno,llBookingEnginetype,llBookingMsgyear,llBookingModel,llBookingAddress,llServiceName,llBookingDate,llBookingMake;
    Calendar c = Calendar.getInstance();
    int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH);
    int mDay = c.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conform_booking);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        llBookingName = (LinearLayout) findViewById(R.id.llBookingName);
        llBookingEmail = (LinearLayout) findViewById(R.id.llBookingEmail);
        llBookingPhoneNo = (LinearLayout) findViewById(R.id.llBookingPhoneNo);
        llBookingAddress = (LinearLayout) findViewById(R.id.llBookingAddress);
        llServiceName = (LinearLayout) findViewById(R.id.llServiceName);
        llBookingDate = (LinearLayout) findViewById(R.id.llBookingDate);
        llBookingMake = (LinearLayout) findViewById(R.id.llBookingMake);
        llBookingModel = (LinearLayout) findViewById(R.id.llBookingModel);
        llBookingMsgyear = (LinearLayout) findViewById(R.id.llBookingMsgyear);
        llBookingEnginetype = (LinearLayout) findViewById(R.id.llBookingEnginetype);
        llBookingVanplateno = (LinearLayout) findViewById(R.id.llBookingVanplateno);
        llBookingComment = (LinearLayout) findViewById(R.id.llBookingComment);
        llBookingTimeSlot = (LinearLayout) findViewById(R.id.llBookingTimeSlot);

        txtCBookingName = (TextView)findViewById(R.id.txtCBookingName);
        txtCBookingEmail = (TextView)findViewById(R.id.txtCBookingEmail);
        txtCBookingPhoneNo = (TextView)findViewById(R.id.txtCBookingPhoneNo);
        txtCBookingAddress = (TextView)findViewById(R.id.txtCBookingAddress);
        txtCServiceName = (TextView)findViewById(R.id.txtCServiceName);
        txtCBookingDate = (TextView)findViewById(R.id.txtCBookingDate);
        txtCBookingMake = (TextView)findViewById(R.id.txtCBookingMake);
        txtCBookingModel = (TextView)findViewById(R.id.txtCBookingModel);
        txtCBookingMsgyear = (TextView)findViewById(R.id.txtCBookingMsgyear);
        txtCBookingEnginetype = (TextView)findViewById(R.id.txtCBookingEnginetype);
        txtCBookingVanplateno = (TextView)findViewById(R.id.txtCBookingVanplateno);
        txtCComment = (TextView)findViewById(R.id.txtCComment);
        txtCTimeSlott = (TextView)findViewById(R.id.txtCTimeSlott);

        txtPrice = (EditText) findViewById(R.id.txtPrice);
        txtRDate = (TextView)findViewById(R.id.txtRDate);
        txtConComment = (EditText) findViewById(R.id.txtConComment);

        btnDone = (Button)findViewById(R.id.btnDone);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        final GetProductDataService productDataService = RetrofitInstance.getRetrofitInstance().create(GetProductDataService.class);

        final String booking_id = getIntent().getExtras().getString("booking_id");
        String booking_name = getIntent().getExtras().getString("booking_name");
        String booking_email = getIntent().getExtras().getString("booking_email");
        String booking_phone = getIntent().getExtras().getString("booking_phone");
        String booking_address = getIntent().getExtras().getString("booking_address");
        String Service_name = getIntent().getExtras().getString("booking_service_name");
        String booking_date = getIntent().getExtras().getString("booking_date");
        //String booking_vinno = getIntent().getExtras().getString("booking_vinno");
        String booking_make = getIntent().getExtras().getString("booking_make");
        String booking_model = getIntent().getExtras().getString("booking_model");
        String booking_msgyear = getIntent().getExtras().getString("booking_msgyear");
        String booking_enginetype = getIntent().getExtras().getString("booking_enginetype");
        String booking_vanplateno = getIntent().getExtras().getString("booking_vanplateno");
        String booking_comment = getIntent().getExtras().getString("booking_comment");
        String t_timeslot = getIntent().getExtras().getString("t_timeslot");

        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
        txtRDate.setText(sdfDate.format(new Date()));
        tvRDate = txtRDate.getText().toString();

        txtRDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(ConformBookingActivity.this,new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        selectedmonth = selectedmonth + 1;

                        if(selectedmonth < 10 && selectedday < 10)
                        {
                            txtRDate.setText("0"+selectedday + "-" + "0"+selectedmonth + "-" + selectedyear);

                        }
                        else if(selectedmonth < 10)
                        {
                            txtRDate.setText(selectedday + "-" + "0"+selectedmonth + "-" + selectedyear);

                        }
                        else if(selectedday < 10)
                        {
                            txtRDate.setText("0"+selectedday + "-" + selectedmonth + "-" + selectedyear);

                        }
                        else
                        {
                            txtRDate.setText(selectedday + "-" + selectedmonth + "-" + selectedyear);

                        }
                        tvRDate = txtRDate.getText().toString();
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.getDatePicker().setMinDate(c.getTimeInMillis());
                mDatePicker.show();

            }
        });

        if (booking_name.equals(""))
        {
            llBookingName.setVisibility(View.GONE);
        }

        if (booking_email.equals(""))
        {
            llBookingEmail.setVisibility(View.GONE);
        }

        if (booking_phone.equals(""))
        {
            llBookingPhoneNo.setVisibility(View.GONE);
        }

        if (booking_address.equals(""))
        {
            llBookingAddress.setVisibility(View.GONE);
        }

        if (Service_name.equals(""))
        {
            llServiceName.setVisibility(View.GONE);
        }

        if (booking_date.equals(""))
        {
            llBookingDate.setVisibility(View.GONE);
        }

        if (booking_make.equals(""))
        {
            llBookingMake.setVisibility(View.GONE);
        }

        if (booking_model.equals(""))
        {
            llBookingModel.setVisibility(View.GONE);
        }

        if (booking_msgyear.equals(""))
        {
            llBookingMsgyear.setVisibility(View.GONE);
        }

        if (booking_enginetype.equals(""))
        {
            llBookingEnginetype.setVisibility(View.GONE);
        }

        if (booking_vanplateno.equals(""))
        {
            llBookingVanplateno.setVisibility(View.GONE);
        }

        if (booking_comment.equals(""))
        {
            llBookingComment.setVisibility(View.GONE);
        }

        if (t_timeslot.equals(""))
        {
            llBookingTimeSlot.setVisibility(View.GONE);
        }

        txtCBookingName.setText(booking_name);
        txtCBookingEmail.setText(booking_email);
        txtCBookingPhoneNo.setText(booking_phone);
        txtCBookingAddress.setText(booking_address);
        txtCServiceName.setText(Service_name);
        txtCBookingDate.setText(booking_date);
        txtCBookingMake.setText(booking_make);
        txtCBookingModel.setText(booking_model);
        txtCBookingMsgyear.setText(booking_msgyear);
        txtCBookingEnginetype.setText(booking_enginetype);
        txtCBookingVanplateno.setText(booking_vanplateno);
        txtCComment.setText(booking_comment);
        txtCTimeSlott.setText(t_timeslot);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConformBookingActivity.super.onBackPressed();
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String booking_price = txtPrice.getText().toString();
                String booking_admin_comment = txtConComment.getText().toString();

                final ProgressDialog d = new ProgressDialog(ConformBookingActivity.this);
                d.setMessage("Loading...");
                d.setCancelable(true);
                d.show();

                Call<Message> ConformBookingCall = productDataService.getConformBookingData(booking_id,"Conform",booking_price,tvRDate,booking_admin_comment);
                ConformBookingCall.enqueue(new Callback<Message>() {
                    @Override
                    public void onResponse(Call<Message> call, Response<Message> response) {
                        d.dismiss();
                        String status = response.body().getStatus();
                        String message = response.body().getMessage();
                        if (status.equals("1"))
                        {
                            Intent i = new Intent(ConformBookingActivity.this, AdminBookingActivity.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(ConformBookingActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Message> call, Throwable t) {
                        Toast.makeText(ConformBookingActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

}
