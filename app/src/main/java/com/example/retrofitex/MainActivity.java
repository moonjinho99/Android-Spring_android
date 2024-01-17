package com.example.retrofitex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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


        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("키해시는 :", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

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