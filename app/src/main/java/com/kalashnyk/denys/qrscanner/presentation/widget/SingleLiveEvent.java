package com.kalashnyk.denys.qrscanner.presentation.widget;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;

public class SingleLiveEvent<T> extends MutableLiveData<T> {

    private AtomicBoolean pending = new AtomicBoolean(false);

    @MainThread
    public void observe(LifecycleOwner owner, Observer<T> observer) {
        if (hasActiveObservers()) {

        }

        super.observe(owner, new Observer<T>() {
            @Override
            public void onChanged(@Nullable T t) {
                if (pending.compareAndSet(true, false)) {
                    observer.onChanged(t);
                }
            }
        });

    }

    @MainThread
    public void setValue(T t) {
        pending.set(true);
        super.setValue(t);
    }

    @MainThread
    public void call() {
        setValue(null);
    }
}
