package com.intellicloudpps.crimerepoart.Fragments;

import static android.text.TextUtils.isEmpty;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.intellicloudpps.crimerepoart.Activitys.AdminPageActivity;
import com.intellicloudpps.crimerepoart.Activitys.CustomerPageActivity;
import com.intellicloudpps.crimerepoart.Healper.Constant;
import com.intellicloudpps.crimerepoart.Healper.SharedSession;
import com.intellicloudpps.crimerepoart.Network.API;
import com.intellicloudpps.crimerepoart.Network.VolleySingleton;
import com.intellicloudpps.crimerepoart.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminFragment extends Fragment {
    EditText adminlogin_Username,adminlogin_Password;
    TextView adminlogin_errormessage,adminlogin_forgotpassword,adminloading_msg;
    Button adminloginButton;

    Dialog dialog;
    ProgressBar loader;


    Matcher passwordmatcher,emailmatcher;
    Pattern passpattern,emailpatrren;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_info, container, false);
        adminlogin_Username=view.findViewById(R.id.adminuseremail);
        adminlogin_Password=view.findViewById(R.id.adminuserpassword);
        adminlogin_errormessage=view.findViewById(R.id.adminlogin_errormessage);
        adminloginButton=view.findViewById(R.id.adminloginButton);
        adminloading_msg=view.findViewById(R.id.loading_msg);
        loader=view.findViewById(R.id.loader);



        adminlogin_Password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lock, 0, R.drawable.visibilityoff, 0);
        adminlogin_Password.setTag("show");
        adminlogin_Password.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @java.lang.Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (adminlogin_Password.getRight() - adminlogin_Password.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        if (adminlogin_Password.getTag().equals("show")) {
                            adminlogin_Password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lock, 0, R.drawable.eyeopen, 0);
                            adminlogin_Password.setTransformationMethod(null);
                            adminlogin_Password.setTag("hide");
                        } else {
                            adminlogin_Password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lock, 0, R.drawable.visibilityoff, 0);
                            adminlogin_Password.setTransformationMethod(new PasswordTransformationMethod());
                            adminlogin_Password.setTag("show");
                        }
                        return true;
                    }
                }
                return false;
            }
        });

        adminloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginuser = adminlogin_Username.getText().toString().trim();
                String loginpass = adminlogin_Password.getText().toString().trim();


                passpattern = Pattern.compile(Constant.PASSWORDPATTERN);
                passwordmatcher = passpattern.matcher(loginpass);

                emailpatrren = Pattern.compile(Constant.EMAILPATTERN);
                emailmatcher = emailpatrren.matcher(loginuser);


                if (isEmpty(loginuser)) {
                    adminlogin_errormessage.setText(Constant.EMAILSHOULDNOTBEEMPTY);
                    //Toast.makeText(getApplicationContext(), "old password should not be Empty", Toast.LENGTH_SHORT).show();
                }
//                else if (!emailmatcher.matches()) {
//                    adminlogin_errormessage.setText(Constant.INVALIDEMAILADDRESS);
//                }
                else if (isEmpty(loginpass)) {
                    adminlogin_errormessage.setText(Constant.PASSWORDSHOULDNOTBEEMPTY);
//                    Toast.makeText(getApplicationContext(), "password should not be Empty", Toast.LENGTH_SHORT).show();

                }
//                else if (!passwordmatcher.matches()) {
//                    adminlogin_errormessage.setText(Constant.PASSWORDISTOOWEEK);
//                }
                else {
//                    Toast.makeText(getActivity(), "login succesfully", Toast.LENGTH_SHORT).show();
//                    Intent a = new Intent(getActivity(), AdminPageActivity.class);
////                    SharedSession.insertData(getApplicationContext(),Constant.EMAIL,loginuser);
//                    SharedSession.insertData(getActivity(), Constant.EMAIL, loginuser);
//                    SharedSession.insertData(getActivity(), Constant.USERTYPE, "admin");
//
//                    startActivity(a);
                    adminlogin_errormessage.setText("");

                    adminlogin();

                }
            }


            private void adminlogin() {




                StringRequest stringRequest = new StringRequest(Request.Method.POST, API.baseurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject object = new JSONObject(response);
                            {
                                if (object.getString("result").equals("success")) {

                                    Intent L = new Intent(getActivity(), AdminPageActivity.class);
//                    SharedSession.insertData(getApplicationContext(),Constant.EMAIL,loginuser);
                                    String loginuser = adminlogin_Username.getText().toString();
                                    JSONObject user=(JSONObject)object.getJSONArray("data").get(0);

                                    int id=user.getInt("id");
                                    String email=user.getString("username");




                                    SharedSession.insertData(getActivity(),Constant.EMAIL,loginuser);
                                    SharedSession.insertData(getActivity(), Constant.USERTYPE, "admin");

//                                    SharedSession.insertData(getActivity(),Constant.IMAGE,image);

                                    startActivity(L);
                                    adminlogin_errormessage.setText("");                                }
                                else {
                                    Toast.makeText(getActivity(), "login Not succesfully", Toast.LENGTH_LONG).show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("Main", "Error: " + error.getMessage());
                        Log.d("Main", "" + error.getMessage() + "," + error.toString());
                    }
                })

                {
                    @Override
                    public Map<String, String> getParams() {

                        Map<String,String> params = new HashMap<String, String>();


                        String loginuser = adminlogin_Username.getText().toString();
                        String loginpass = adminlogin_Password.getText().toString();


                        params.put("crimereport_admin_login","1");
                        params.put("accesskey","1234");
                        params.put("username", loginuser);
                        params.put("password",loginpass);
                        return params;
                    }
                };
                VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);

            }

        });


        return view;
    }
}