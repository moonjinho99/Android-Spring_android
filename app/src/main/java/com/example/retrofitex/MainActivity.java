package com.example.retrofitex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText name,age;
    Button joinBtn;
    UserRetrofitInterface userRetrofitInterface;
    Call<UserDTO> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        age = (EditText) findViewById(R.id.age);
        name = (EditText) findViewById(R.id.name);
        joinBtn = (Button) findViewById(R.id.joinBtn);


        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        UserRetrofitInterface userRetrofitInterface = RetrofitClient.getUserRetrofitInterface();


        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserDTO userDTO = new UserDTO(name.getText().toString(),Integer.parseInt(age.getText().toString()));
                Gson gson = new Gson();
                String userInfo = gson.toJson(userDTO);

                Log.e("JSON",userInfo);

                Call<ResponseBody> call = userRetrofitInterface.saveUser(userDTO);;
                call.clone().enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if(response.isSuccessful())
                        {
                            try {

                                if(response.body().string().equals("success"))
                                {
                                    Toast.makeText(getApplicationContext(),"인증 완료",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, MemberActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"회원 정보를 다시 입력해주세요",Toast.LENGTH_SHORT).show();
                                }

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"실패",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}