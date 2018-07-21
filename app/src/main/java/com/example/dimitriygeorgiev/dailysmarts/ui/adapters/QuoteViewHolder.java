package com.example.dimitriygeorgiev.dailysmarts.ui.adapters;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dimitriygeorgiev.dailysmarts.R;
import com.example.dimitriygeorgiev.dailysmarts.models.room.QuoteEntity;
import com.example.dimitriygeorgiev.dailysmarts.models.room.ViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

class QuoteViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_quote)
    TextView txtQuote;
    @BindView(R.id.img_btn_like)
    ImageButton imgBtnLike;
    @BindView(R.id.img_btn_share)
    ImageButton imgBtnShare;
    @BindView(R.id.txt_author_quote)
    TextView txtAuthor;

    ImageButton imgBtnLikeDaily;

    public QuoteViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setTxtQuote(String quote) {
        txtQuote.setText(quote);
    }

    public void setIconOfImageButton(int state, ViewModel viewModel, QuoteEntity quoteEntity) {
        if (state == 1) {
            imgBtnLike.setImageResource(R.drawable.ic_favorite_black_24px);
        } else if (state == 0) {
            imgBtnLike.setImageResource(R.drawable.ic_favorite_border_black_24px);
            viewModel.delete(quoteEntity);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setTxtAuthor(String author) {
        txtAuthor.setText(author);
    }
}
