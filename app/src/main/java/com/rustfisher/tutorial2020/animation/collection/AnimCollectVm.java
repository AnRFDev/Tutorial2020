package com.rustfisher.tutorial2020.animation.collection;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * 2020-12-1
 */
public class AnimCollectVm extends AndroidViewModel {

    public MutableLiveData<Void> playCurAnimation = new MutableLiveData<>();

    public AnimCollectVm(@NonNull Application application) {
        super(application);
    }

    public void playCurAni(View view) {
        playCurAnimation.postValue(null);
    }
}
