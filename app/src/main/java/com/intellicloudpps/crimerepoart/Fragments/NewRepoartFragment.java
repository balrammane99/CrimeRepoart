package com.intellicloudpps.crimerepoart.Fragments;

import static android.text.TextUtils.isEmpty;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.intellicloudpps.crimerepoart.Activitys.UpdateProfileActivity;
import com.intellicloudpps.crimerepoart.Healper.Constant;
import com.intellicloudpps.crimerepoart.Healper.SharedSession;
import com.intellicloudpps.crimerepoart.Network.API;
import com.intellicloudpps.crimerepoart.Network.VolleySingleton;
import com.intellicloudpps.crimerepoart.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class NewRepoartFragment extends Fragment implements View.OnClickListener {
    EditText  nc_title,nc_decription,nc_ed_date,nc_ChooseTime,nc_area,nc_pincode ;
    Spinner nc_Categorie;
    Button nc_submitrepoart;
    TextView  nc_errormsg;

    //date picker
    private int year,month,day;

    //time picker
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=  inflater.inflate(R.layout.fragment_home, container, false);
        nc_title=view.findViewById(R.id.nc_title);
        nc_decription=view.findViewById(R.id.nc_decription);
        nc_ed_date=view.findViewById(R.id.nc_ed_date);
        nc_ChooseTime=view.findViewById(R.id.nc_ChooseTime);
        nc_Categorie=view.findViewById(R.id.nc_spinner);
        nc_area=view.findViewById(R.id.nc_area);
        nc_pincode=view.findViewById(R.id.nc_pincode);
        nc_errormsg=view.findViewById(R.id.nc_errormsg);
        nc_submitrepoart=view.findViewById(R.id.nc_submitrepoart);



        // spinner
        ArrayAdapter<CharSequence>adapter= ArrayAdapter.createFromResource(getActivity(), R.array.Crimes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        nc_Categorie.setAdapter(adapter);

        // time Picker
        nc_ChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        nc_ChooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });

        //date paicker
        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat sd=new SimpleDateFormat("dd-MM-YYYY", Locale.US);
        String currentdate=sd.format(new Date());
//        nc_ed_date.setText(currentdate);
        nc_ed_date.setOnClickListener(this);


        //submit repoart
        nc_submitrepoart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = nc_title.getText().toString();
                String description=nc_decription.getText().toString();
                String date=nc_ed_date.getText().toString();
                String time=nc_ChooseTime.getText().toString();
//                String categorie=nc_Categorie.getSelectedItem().toString();
                String area=nc_area.getText().toString();
                String pincode=nc_pincode.getText().toString();


                if (isEmpty(title)) {
                    nc_errormsg.setText(Constant.TITLESHOULDNOTBEEMPTY);
                }
                else if (isEmpty(description)) {
                    nc_errormsg.setText(Constant.DDESCRIPTIONSHOULDNOTBEEMPTY);
                }
                else if (isEmpty(date)) {
                    nc_errormsg.setText(Constant.SELECTDATE);
                }
                else if (isEmpty(time)) {
                    nc_errormsg.setText(Constant.SELECTTIME);
                }
                else if (nc_Categorie.getSelectedItem().toString().trim().equals("Select")) {
                    nc_errormsg.setText(Constant.SELECTCATEGORY);
                }
                else if (isEmpty(area)) {
                    nc_errormsg.setText(Constant.AREASHOULDDNOTBEEMPTY);
                }
                else if (isEmpty(pincode)) {
                    nc_errormsg.setText(Constant.PINCODESHOULDDNOTBEEMPTY);
                }
                else {
                    nc_errormsg.setText("");
//                    Toast.makeText(getActivity(), Constant.REPOARTSUBMITTEDSUCESSFULLY, Toast.LENGTH_SHORT).show();

                    insertrepoart();
                }
            }

            private void insertrepoart() {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, API.baseurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);
                            {
                                if (object.getString(Constant.RESULT).equals(Constant.SUCCESS)) {
                                    Toast.makeText(getActivity(), Constant.SUCCESSFULLYINSERTINGTHENEWS, Toast.LENGTH_SHORT).show();
                                    nc_title.setText("");
                                    nc_decription.setText("");
                                    nc_ed_date.setText("");
                                    nc_ChooseTime.setText("");
//                                    nc_Categorie.setTag().toString().equals("Select");
                                    nc_area.setText("");
                                    nc_pincode.setText("");
                                } else {
                                    Toast.makeText(getActivity(), Constant.NOTINSERTINGTHENEWS, Toast.LENGTH_LONG).show();

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(Constant.MAIN, "Error: " + error.getMessage());
                        Log.d(Constant.MAIN, "" + error.getMessage() + "," + error.toString());
                    }
                }) {
                    @Override
                    public Map<String, String> getParams() {

                        Map<String,String> params = new HashMap<String, String>();

                        String newcrimetitle = nc_title.getText().toString();
                        String newcrimedecription = nc_decription.getText().toString();
                        String newcrimedate = nc_ed_date.getText().toString();
                        String newcrimetime = nc_ChooseTime.getText().toString();
                        String newcrimecategory = nc_Categorie.getSelectedItem().toString();
                        String newcrimearea=nc_area.getText().toString();
                        String newcrimepincode=nc_pincode.getText().toString();


                        params.put("crimereport_crimes_insert","1");
                        params.put("accesskey",Constant.ACCESSKEY);
                        params.put("title", newcrimetitle);
                        params.put("description", newcrimedecription);
                        params.put("cdate",newcrimedate);
                        params.put("ctime",newcrimetime);
                        params.put("crimetype",newcrimecategory);
                        params.put("area",newcrimearea);
                        params.put("pincode",newcrimepincode);
                        params.put("email", SharedSession.getStr(getActivity(),Constant.EMAIL));

                        return params;
                    }
                };
                VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);

            }

        });

        return view;
    }

    @Override
    public void onClick(View view) {
        final Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                nc_ed_date.setText(dayOfMonth+"-"+(monthofyear+1)+"-"+year);
            }
        },year,month,day);
        datePickerDialog.show();
    }
}