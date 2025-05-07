package com.example.quanlythe;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlythe.model.User;
import com.example.quanlythe.network.ApiService;
import com.example.quanlythe.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button btnLogin, btnRegister;
    TextView tvRegister; // đổi từ Button thành TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister); // ánh xạ TextView mới

        // ======= Thêm đoạn này để làm Hint Đậm =======
        SpannableString usernameHint = new SpannableString("Tên đăng nhập");
        usernameHint.setSpan(new StyleSpan(Typeface.BOLD), 0, usernameHint.length(), 0);
        username.setHint(usernameHint);

        SpannableString passwordHint = new SpannableString("Mật khẩu");
        passwordHint.setSpan(new StyleSpan(Typeface.BOLD), 0, passwordHint.length(), 0);
        password.setHint(passwordHint);
        // ============================================
        // Xử lý đăng nhập
        btnLogin.setOnClickListener(v -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();

            // Gọi API đăng nhập
            User loginUser = new User(user, pass);

            ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
            apiService.loginUser(loginUser).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        // Chuyển sang trang chính của ứng dụng sau khi đăng nhập thành công
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Lỗi mạng hoặc server: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Chuyển đến màn hình đăng ký
        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
