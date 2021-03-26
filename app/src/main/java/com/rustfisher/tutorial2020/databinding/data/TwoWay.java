package com.rustfisher.tutorial2020.databinding.data;


import android.widget.CompoundButton;

import androidx.databinding.ObservableField;

public class TwoWay {
    public final ObservableField<Boolean> rememberMe = new ObservableField<>(false);

    public void rememberMeChanged(CompoundButton buttonView, boolean isChecked) {
        rememberMe.set(isChecked);
    }

}
