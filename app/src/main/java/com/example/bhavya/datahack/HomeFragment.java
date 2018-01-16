package com.example.bhavya.datahack;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by bhavya on 7/10/17.
 */

public class HomeFragment extends Fragment {
    Intent call = new Intent(Intent.ACTION_CALL);
    Intent alert = new Intent(Intent.ACTION_SENDTO);

    private String number = "8094689195";
    private String number2 = "";
    private String number3 = "";
    View homeView;
    private Unbinder unbinder;
    private static final int REQUEST_CALL = 1;
    private static final int REQUEST_SMS = 2;
    private String sms;
    GoogleApiClient mGoogleApiClient;
    String coordinates = " ";
    Location mLastLocation;

    private void getMyLocation(){
        if(mGoogleApiClient!=null) {

            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }

            if (mLastLocation != null) {


                coordinates=     "Lattitude: " +   String.valueOf(mLastLocation.getLatitude()) + "\nLongitude: "
                                + String.valueOf(mLastLocation.getLongitude());

            }else{
                coordinates = " ";
            }
    }

    public String getInfo(int code)
    {
        if(code==1)
        {
        SharedPreferences shared = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        if(shared.contains("Information")) {
            return shared.getString("Information", "");
        }
        }else if(code==2) {
            SharedPreferences shared = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
            if (shared.contains("Cont1")) {
                return shared.getString("Cont1", "");
            }
        }else if(code==3) {
            SharedPreferences shared = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
            if (shared.contains("Cont2")) {
                return shared.getString("Cont2", "");
            }
        }
            return "";
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CALL: {
                if (grantResults.equals(null) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(call);
                }
            }
            break;
            case REQUEST_SMS: {
                if (grantResults.equals(null) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getMyLocation();
                    number2 = getInfo(2);
                    number3 = getInfo(3);
                    Toast.makeText(getActivity(),"Alert Sent",Toast.LENGTH_LONG).show();
                    sms = getInfo(1) + "\n" + coordinates;
                    if (sms.equals("" + coordinates)) {

                        SmsManager.getDefault().sendTextMessage(number, null, "Help!!" + coordinates, null, null);
                        if(!(number2.equals(null)))
                        {
                            SmsManager.getDefault().sendTextMessage(number2,null,"Help!!" + coordinates, null, null);
                        }
                        if(!(number3.equals(null)))
                        {
                            SmsManager.getDefault().sendTextMessage(number3,null,"Help!!" + coordinates, null, null);
                        }
                    }
                    else {

                        SmsManager.getDefault().sendTextMessage(number, null, sms, null, null);
                        if(!(number2.equals("")))
                        {
                            SmsManager.getDefault().sendTextMessage(number2, null, sms, null, null);
                        }
                        if(!(number3.equals("")))
                        {
                            SmsManager.getDefault().sendTextMessage(number3, null, sms, null, null);
                        }

                    }
                }
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        homeView = inflater.inflate(R.layout.home, container, false);
        unbinder = ButterKnife.bind(this, homeView);



        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
        }
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
          ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS},REQUEST_SMS);

         }
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }
        Button Call = (Button)homeView.findViewById(R.id.call);
        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                call.setData(Uri.parse("tel:91-809-468-9195"));
                if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
                }else {
                    startActivity(call);
                }

            }
        });

         Button Alert =  (Button) homeView.findViewById(R.id.alert);
        Alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, REQUEST_CALL);
                } else {

                            getMyLocation();
                           number2 = getInfo(2);
                           number3 = getInfo(3);
                    Toast.makeText(getActivity(),"Alert Sent",Toast.LENGTH_LONG).show();
                        sms = getInfo(1) + "\n" + coordinates;
                        if (sms.equals(" "+coordinates)) {
                            sms = "Help!!" +coordinates;
                            SmsManager.getDefault().sendTextMessage(number, null, sms, null, null);
                            if(!(number2.equals("")))
                            {
                                SmsManager.getDefault().sendTextMessage(number2,null,sms, null, null);
                            }
                            if((!number3.equals("")))
                            {
                                SmsManager.getDefault().sendTextMessage(number3,null,sms, null, null);
                            }
                        } else {

                            SmsManager.getDefault().sendTextMessage(number, null, sms, null, null);
                            if(!number2.equals(""))
                            {
                                SmsManager.getDefault().sendTextMessage(number2, null, sms, null, null);
                            }
                            if(!(number3.equals("")))
                            {
                                SmsManager.getDefault().sendTextMessage(number3, null, sms, null, null);
                            }
                        }
                    }

                }
        });

        return homeView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }
}
