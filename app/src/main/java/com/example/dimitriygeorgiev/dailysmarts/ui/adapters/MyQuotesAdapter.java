package com.example.dimitriygeorgiev.dailysmarts.ui.adapters;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dimitriygeorgiev.dailysmarts.R;
import com.example.dimitriygeorgiev.dailysmarts.models.room.QuoteEntity;
import com.example.dimitriygeorgiev.dailysmarts.models.room.ViewModel;

import java.util.List;


public class MyQuotesAdapter extends RecyclerView.Adapter<QuoteViewHolder> {

    private final LayoutInflater mInflater;
    private List<QuoteEntity> mQuotes; // Cached copy of words
    private ViewModel viewModel;
    private Context context;

    public MyQuotesAdapter(Context context, ViewModel viewModel) {
        mInflater = LayoutInflater.from(context);
        this.viewModel = viewModel;
        this.context = context;
    }

    @Override
    public QuoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_quote, parent, false);
        return new QuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuoteViewHolder viewHolder, int position) {
        if (mQuotes != null) {
            QuoteEntity current = mQuotes.get(position);
            viewHolder.setTxtQuote(current.getQuoteText());
            viewHolder.setTxtAuthor(current.getQuoteAuthor());
            if (current.isLiked()) {
                viewHolder.setIconOfImageButton(1, viewModel, current);
            } else {
                viewHolder.setIconOfImageButton(0, viewModel, current);
            }
            viewHolder.imgBtnLike.setOnClickListener(v -> onLikeClicked(current));
            viewHolder.imgBtnShare.setOnClickListener(v -> onShareIntent(current, context));
        } else {
        }
    }

    private void onShareIntent(QuoteEntity current, Context context) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = current.getQuoteText();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Quote");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    private void onLikeClicked(QuoteEntity current) {
        current.setLiked(!current.isLiked());
        viewModel.update(current);
        notifyDataSetChanged();
    }

    public void setmQuotes(List<QuoteEntity> quotes){
        mQuotes = quotes;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mQuotes != null)
            return mQuotes.size();
        else return 0;
    }

}
