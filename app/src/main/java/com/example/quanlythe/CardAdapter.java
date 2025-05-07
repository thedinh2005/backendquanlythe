package com.example.quanlythe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlythe.model.Card;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<Card> cardList;

    public CardAdapter(List<Card> cardList) {
        this.cardList = cardList;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Card card = cardList.get(position);
        holder.cardNumberText.setText(card.getCard_number());
        holder.cardTypeText.setText(card.getCard_type());
        holder.cardHolderNameText.setText(card.getCard_holder());
        // Thêm các trường khác nếu cần
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView cardNumberText, cardTypeText, cardHolderNameText;

        public CardViewHolder(View itemView) {
            super(itemView);
            cardNumberText = itemView.findViewById(R.id.cardNumber);
            cardTypeText = itemView.findViewById(R.id.cardType);
            cardHolderNameText = itemView.findViewById(R.id.cardHolderName);
        }
    }
}
