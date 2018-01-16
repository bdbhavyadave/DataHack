package com.example.bhavya.datahack;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.bhavya.datahack.R;
import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.Unbinder;


/**
 * Created by bhavya on 3/10/17.
 */

public class CrimeFragment extends Fragment {

    View crimeView;
    WebView webinfo;
    private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        crimeView = inflater.inflate(R.layout.crime, container, false);
        unbinder = ButterKnife.bind(this, crimeView);
        webinfo = (WebView)crimeView.findViewById(R.id.webinfo);

        webinfo.getSettings().setJavaScriptEnabled(true);
        webinfo.loadUrl("192.168.1.107/Murders-During-2013");

        return crimeView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }
}
