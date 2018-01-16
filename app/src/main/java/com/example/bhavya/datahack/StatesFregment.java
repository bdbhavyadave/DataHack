package com.example.bhavya.datahack;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by bhavya on 7/10/17.
 */

public class StatesFregment extends Fragment {
    View stateView;
    private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        stateView = inflater.inflate(R.layout.state, container, false);
        unbinder = ButterKnife.bind(this, stateView);
        return stateView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }
}
