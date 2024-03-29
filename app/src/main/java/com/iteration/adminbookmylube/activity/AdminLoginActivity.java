package com.iteration.adminbookmylube.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iteration.adminbookmylube.R;
import com.iteration.adminbookmylube.model.MessageLogin;
import com.iteration.adminbookmylube.network.GetProductDataService;
import com.iteration.adminbookmylube.network.RetrofitInstance;
import com.iteration.adminbookmylube.network.SessionAdminManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminLoginActivity extends AppCompatActivity {

    EditText txtUName,txtPassword;
    SessionAdminManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        session = new SessionAdminManager(getApplicationContext());

        txtUName = (EditText)findViewById(R.id.txtUName);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        Button btnLogin = (Button)findViewById(R.id.btnLogin);

        final GetProductDataService productDataService = RetrofitInstance.getRetrofitInstance().create(GetProductDataService.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtUName.getText().toString();
                String password = txtPassword.getText().toString();

                final ProgressDialog dialog = new ProgressDialog(AdminLoginActivity.this);
                dialog.setMessage("Loading...");
                dialog.setCancelable(true);
                dialog.show();

                Call<MessageLogin> LoginListCall = productDataService.getLoginData(email,password);
                LoginListCall.enqueue(new Callback<MessageLogin>() {
                    @Override
                    public void onResponse(Call<MessageLogin> call, Response<MessageLogin> response) {
                        dialog.dismiss();
                        String status = response.body().getStatus();
                        String message = response.body().getMessage();
                        if (status.equals("1"))
                        {
                            Log.d("message",""+message);
                            String Email = response.body().getEmail();
                            session.createLoginSession(Email);
                            Intent i = new Intent(AdminLoginActivity.this, AdminBookingActivity.class);
                            startActivity(i);
                        }
                        else
                        {
                            Log.d("message",""+message);
                        }

                    }

                    @Override
                    public void onFailure(Call<MessageLogin> call, Throwable t) {
                        Toast.makeText(AdminLoginActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
