package com.example.dimitriygeorgiev.dailysmarts.ui.fragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<View> selected = new MutableLiveData<>();

    public void select(View view) {
        selected.setValue(view);
    }

    public LiveData<View> getSelected() {
        return selected;
    }
}