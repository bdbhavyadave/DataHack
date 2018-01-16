package com.example.bhavya.datahack;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by bhavya on 4/10/17.
 */

public class InfoFragment extends Fragment {
    View InfoView;
    @BindView(R.id.name)
    private Unbinder unbinder;
    private String nameinfo, adressinfo, bloodginfo, contact1info, contact2info,message="I'm in trouble, please help!!";

    public void putInfo(String Information, int code)
    {
        if(code==1) {
            SharedPreferences shared = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

            SharedPreferences.Editor edit = shared.edit();
            edit.putString("Information", Information);
            edit.apply();
        }
        else if(code==2)
        {

                SharedPreferences shared = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

                SharedPreferences.Editor edit = shared.edit();
                edit.putString("Cont1", Information);
                edit.apply();

        }
        else if(code == 3)
        {
            SharedPreferences shared = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

            SharedPreferences.Editor edit = shared.edit();
            edit.putString("Cont2", Information);
            edit.apply();

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        InfoView = inflater.inflate(R.layout.info, container, false);
        unbinder = ButterKnife.bind(this, InfoView);
        Button btnSyncInfo = (Button)InfoView.findViewById(R.id.btnSyncInfo);
        Button btnSyncContact= (Button)InfoView.findViewById(R.id.btnSyncContact);
        final EditText name = (EditText)InfoView.findViewById(R.id.name);
        final EditText adress = (EditText)InfoView.findViewById(R.id.adress);
        final EditText bloodg = (EditText)InfoView.findViewById(R.id.bloodg);
        final EditText contact1 = (EditText)InfoView.findViewById(R.id.contact1);
        final EditText contact2 = (EditText)InfoView.findViewById(R.id.contact2);



        btnSyncInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               String Info;
                if (name.getText().toString().trim().equals("") || adress.getText().toString().trim().equals("") || bloodg.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getApplicationContext(), "All information required", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(),"Sync sucessful",Toast.LENGTH_SHORT).show();
                    nameinfo = name.getText().toString().trim();
                    adressinfo = adress.getText().toString().trim();
                    bloodginfo = bloodg.getText().toString().trim();
                    Info = message+"\nName: "+nameinfo + "\nAdress: " + adressinfo + "\n Blood Group: " + bloodginfo;
                    putInfo(Info,1);
                }

            }
        });

        btnSyncContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "Sync Sucessful", Toast.LENGTH_SHORT).show();
                    contact1info = contact1.getText().toString().trim();
                    putInfo(contact1info, 2);

                    contact2info = contact2.getText().toString().trim();
                    putInfo(contact2info, 3);

            }
        });
        return InfoView;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
