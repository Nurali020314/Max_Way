package com.skipissue.maxway.util;/*



import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A lifecycle-aware observable that sends only new updates after subscription, used for events like
 * navigation and Snackbar messages.
 * <p>
 * This avoids a common problem with events: on configuration change (like rotation) an update
 * can be emitted if the observer is active. This LiveData only calls the observable if there's an
 * explicit call to setValue() or call().
 * <p>
 * Note that only one observer is going to be notified of changes.
 */

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.atomic.AtomicBoolean;

public class SingleLiveEvent<T> extends MutableLiveData<T> {

    private LiveData<T> liveDataToObserve;
    private final AtomicBoolean mPending = new AtomicBoolean(false);

    public SingleLiveEvent() {
        final MediatorLiveData<T> outputLiveData = new MediatorLiveData<>();
        outputLiveData.addSource(this, currentValue -> {
            outputLiveData.setValue(currentValue);
            mPending.set(false);
        });
        liveDataToObserve = outputLiveData;
    }

    @MainThread
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        liveDataToObserve.observe(owner, t -> {
            if (mPending.get()) {
                observer.onChanged(t);
            }
        });
    }

    @MainThread
    public void setValue(T value) {
        mPending.set(true);
        super.setValue(value);
    }
}