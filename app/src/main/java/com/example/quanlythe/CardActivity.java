package com.example.quanlythe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlythe.model.Card;
import com.example.quanlythe.network.ApiService;
import com.example.quanlythe.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private ApiService apiService;
    private Button btnAddCard,btnScanCard; // Khai báo nút "Thêm Thẻ"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        btnAddCard = findViewById(R.id.btnAddCard);
        btnScanCard = findViewById(R.id.btnScanCard);
        // Khởi tạo RecyclerView và ApiService
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiService = RetrofitClient.getInstance().create(ApiService.class);

        loadCards(); // Gọi hàm để lấy danh sách thẻ
        // Xử lý sự kiện khi nhấn nút "Thêm Thẻ"
        btnAddCard.setOnClickListener(v -> {
            // Chuyển sang AddCardActivity (hoặc CardFragment nếu bạn muốn dùng Fragment)
            Intent intent = new Intent(CardActivity.this, AddCardActivity.class);  // Replace with your activity name
            startActivity(intent);
        });

        btnScanCard.setOnClickListener(v -> {
            Intent intent = new Intent(CardActivity.this, ScanCardActivity.class);
            startActivity(intent);
        });

    }

    // Hàm tải danh sách thẻ từ API
    private void loadCards() {
        apiService.getCards().enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Card> cards = response.body();
                    cardAdapter = new CardAdapter(cards);  // Gán dữ liệu cho adapter
                    recyclerView.setAdapter(cardAdapter);  // Đưa adapter vào RecyclerView
                } else {
                    Log.e("CardActivity", "Lỗi: " + response.message());
                    Toast.makeText(CardActivity.this, "Không thể lấy danh sách thẻ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                Log.e("CardActivity", "Lỗi: " + t.getMessage());
                Toast.makeText(CardActivity.this, "Lỗi kết nối mạng", Toast.LENGTH_SHORT).show();
            }
        });

    }

}

