package com.kalashnyk.denys.qrscanner.presentation.activities.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.kalashnyk.denys.qrscanner.R;
import com.kalashnyk.denys.qrscanner.di.component.ViewModelComponent;
import com.kalashnyk.denys.qrscanner.presentation.base.BaseActivity;
import com.kalashnyk.denys.qrscanner.presentation.fragment.AllProductsFragment;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initMain();
    }

    private void initMain() {
        getSupportFragmentManager().beginTransaction().replace(R.id.v_main_container, AllProductsFragment.newInstance()).commit();
    }

    @Override
    protected void injectDependency(ViewModelComponent component) {
        //method override for all children of BaseActivity for dependence injection
    }
}
