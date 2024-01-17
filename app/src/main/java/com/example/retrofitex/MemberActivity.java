package com.example.retrofitex;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.retrofitex.databinding.MemberBinding;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberActivity extends AppCompatActivity {

    EditText findusername;
    Button findBtn;

    UserRetrofitInterface userRetrofitInterface;
    Call<UserDTO> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member);

        findusername =(EditText) findViewById(R.id.findusername);
        findBtn = (Button) findViewById(R.id.findBtn);

        UserRetrofitInterface userRetrofitInterface = RetrofitClient.getUserRetrofitInterface();

        findBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                Call<ResponseBody> call = userRetrofitInterface.findUser(findusername.getText().toString());
//
//                call.clone().enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
////                        if(response.isSuccessful())
////                        {
////                            try {
////
////                            } catch (IOException e) {
////                                throw new RuntimeException(e);
////                            }
////                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                    }
//                });

                Intent intent = new Intent(MemberActivity.this, MapActivity.class);
                startActivity(intent);

            }
        });


    }


}