package com.example.quanlythe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class ScanCardActivity extends AppCompatActivity {

    private static final int SCAN_REQUEST_CODE = 101;
    private TextView tvCardInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_card);

        tvCardInfo = findViewById(R.id.tvCardInfo);

        // Mở camera để quét thẻ
        Intent scanIntent = new Intent(this, CardIOActivity.class);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); // quét ngày hết hạn
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false);   // không cần CVV
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false);
        startActivityForResult(scanIntent, SCAN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SCAN_REQUEST_CODE && data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
            CreditCard scannedCard = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

            String result = "Số thẻ: " + scannedCard.getRedactedCardNumber() + "\n";
            if (scannedCard.isExpiryValid()) {
                result += "Hết hạn: " + scannedCard.expiryMonth + "/" + scannedCard.expiryYear + "\n";
            }

            tvCardInfo.setText(result);
        } else {
            tvCardInfo.setText("Không có thẻ nào được quét.");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
