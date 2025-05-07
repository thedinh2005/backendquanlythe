package com.example.quanlythe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlythe.model.Card;
import com.example.quanlythe.network.ApiService;
import com.example.quanlythe.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCardActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card); // Đảm bảo layout bạn tạo cho Activity

        // Tìm kiếm các view trong layout
        EditText etCardNumber = findViewById(R.id.et_card_number);
        EditText etCardHolder = findViewById(R.id.et_card_holder);
        EditText etCardType = findViewById(R.id.et_card_type);
        EditText etOpenedDate = findViewById(R.id.et_opened_date);
        Button btnSave = findViewById(R.id.btn_save_card);
        EditText etCvv = findViewById(R.id.et_cvv);
        progressBar = findViewById(R.id.progress_bar); // progress bar

        btnSave.setOnClickListener(v -> {
            String cardNumber = etCardNumber.getText().toString().trim();
            String holder = etCardHolder.getText().toString().trim();
            String type = etCardType.getText().toString().trim();
            String openedDate = etOpenedDate.getText().toString().trim();
            String cvv = etCvv.getText().toString().trim();

            // Kiểm tra lỗi đầu vào
            if (cardNumber.length() != 16 || !cardNumber.matches("\\d{16}")) {
                Toast.makeText(AddCardActivity.this, "Số thẻ phải gồm đúng 16 chữ số", Toast.LENGTH_SHORT).show();
                return;
            }

            if (holder.isEmpty() || type.isEmpty() || openedDate.isEmpty()) {
                Toast.makeText(AddCardActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (cvv.length() < 3 || cvv.length() > 4 || !cvv.matches("\\d{3,4}")) {
                Toast.makeText(AddCardActivity.this, "CVV phải là 3 hoặc 4 chữ số", Toast.LENGTH_SHORT).show();
                return;
            }

            Card card = new Card(cardNumber, holder, type, openedDate, cvv);

            // Hiển thị ProgressBar khi gửi yêu cầu
            progressBar.setVisibility(View.VISIBLE);

            ApiService api = RetrofitClient.getInstance().create(ApiService.class);
            Call<Card> call = api.createCard(card);

            call.enqueue(new Callback<Card>() {
                @Override
                public void onResponse(Call<Card> call, Response<Card> response) {
                    progressBar.setVisibility(View.GONE); // Ẩn ProgressBar khi có phản hồi

                    if (response.isSuccessful()) {
                        Toast.makeText(AddCardActivity.this, "Thêm thẻ thành công", Toast.LENGTH_SHORT).show();
                        // Làm sạch các trường nhập
                        etCardNumber.setText("");
                        etCardHolder.setText("");
                        etCardType.setText("");
                        etOpenedDate.setText("");
                        etCvv.setText("");
                    } else {
                        Toast.makeText(AddCardActivity.this, "Lỗi khi thêm: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Card> call, Throwable t) {
                    progressBar.setVisibility(View.GONE); // Ẩn ProgressBar khi có lỗi
                    Toast.makeText(AddCardActivity.this, "Lỗi mạng: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
