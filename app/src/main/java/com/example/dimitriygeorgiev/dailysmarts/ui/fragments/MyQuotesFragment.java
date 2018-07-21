package com.example.dimitriygeorgiev.dailysmarts.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dimitriygeorgiev.dailysmarts.R;
import com.example.dimitriygeorgiev.dailysmarts.models.room.QuoteEntity;
import com.example.dimitriygeorgiev.dailysmarts.models.room.ViewModel;
import com.example.dimitriygeorgiev.dailysmarts.ui.adapters.MyQuotesAdapter;

import java.util.List;

public class MyQuotesFragment extends Fragment {

    private ViewModel viewModel;
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        View view = inflater.inflate(R.layout.fragment_my_quotes, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_my_quotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyQuotesAdapter adapter = new MyQuotesAdapter(getContext(), viewModel);
        recyclerView.setAdapter(adapter);

        viewModel.getAllQuotes().observe(this, new Observer<List<QuoteEntity>>() {
            @Override
            public void onChanged(@Nullable List<QuoteEntity> quoteEntities) {
                adapter.setmQuotes(quoteEntities);
            }
        });
        return view;
    }

}
