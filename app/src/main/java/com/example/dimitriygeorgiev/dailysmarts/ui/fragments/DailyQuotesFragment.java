package com.example.dimitriygeorgiev.dailysmarts.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dimitriygeorgiev.dailysmarts.R;
import com.example.dimitriygeorgiev.dailysmarts.models.Quote;
import com.example.dimitriygeorgiev.dailysmarts.models.retrofit.QuoterDataRepository;
import com.example.dimitriygeorgiev.dailysmarts.models.retrofit.RetrofitService;
import com.example.dimitriygeorgiev.dailysmarts.models.retrofit.RetrofitWrapper;
import com.example.dimitriygeorgiev.dailysmarts.models.room.QuoteEntity;
import com.example.dimitriygeorgiev.dailysmarts.models.room.ViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dimitriygeorgiev.dailysmarts.R.drawable.ic_favorite_border_black_24px;

public class DailyQuotesFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.txt_author_quote_daily)
    TextView txtAuthor;
    @BindView(R.id.txt_quote_daily)
    TextView txtQuote;
    @BindView(R.id.img_btn_like_daily)
    ImageButton imgBtnLike;
    @BindView(R.id.img_btn_share_daily)
    ImageButton imgBtnShare;

    private SharedViewModel sharedViewModel;
    private ViewModel viewModel;
    private RetrofitWrapper wrapper;
    private Quote quoteOfTheDay;
    boolean clicked = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_quotes, container, false);
        ButterKnife.bind(this, view);
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        imgBtnLike.setOnClickListener(v -> sharedViewModel.select(v));
        wrapper = RetrofitWrapper.getInstance();
        getQuote();
        imgBtnLike.setOnClickListener(this::onClick);
        imgBtnShare.setOnClickListener(this::onClick);
        setHasOptionsMenu(true);
        return view;
    }

    private void getQuote() {
        wrapper.getQuote(new QuoterDataRepository.QuoteListener() {
            @Override
            public void onQuoteReady(Quote quoteOfTheDay) {
                txtAuthor.setText(quoteOfTheDay.getQuoteAuthor());
                txtQuote.setText(quoteOfTheDay.getQuoteText());
                imgBtnLike.setImageResource(R.drawable.ic_favorite_border_black_24px);
                clicked = false;
            }

            @Override
            public void onQuoteFail() {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == imgBtnLike) {
            if (clicked) {
                viewModel.delete(new QuoteEntity(txtQuote.getText().toString(), txtAuthor.getText().toString()));
                imgBtnLike.setImageResource(R.drawable.ic_favorite_border_black_24px);
                clicked = false;
            } else {
                viewModel.insert(new QuoteEntity(txtQuote.getText().toString(), txtAuthor.getText().toString()));
                imgBtnLike.setImageResource(R.drawable.ic_favorite_black_24px);
                clicked = true;
            }
        } else if (v == imgBtnShare) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            startActivity(intent.createChooser(intent, "Share using... "));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.quotes_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                update();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void update() {
        getQuote();
    }
}
