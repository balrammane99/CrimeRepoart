package com.intellicloudpps.crimerepoart.Fragments;

import static android.text.TextUtils.isEmpty;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
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
import com.intellicloudpps.crimerepoart.Healper.Constant;
import com.intellicloudpps.crimerepoart.Network.API;
import com.intellicloudpps.crimerepoart.Network.VolleySingleton;
import com.intellicloudpps.crimerepoart.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterFragment extends Fragment {
    EditText Registrationusername,Registrationpassword,Registrationmobile,Registrationemail,Registrationcity,Registrationaddress;
    Button Registrationsubmit;
    TextView crt_acc_login,createaccount_errormessage,loading_msg;

    Matcher regfirstnamematcher,regmobilematcher,regemailmatcher,regpasswordmatcher;
    Pattern regpasspattern,regemailpatrren,regfirstnamepattern,regmobilepattern;

    Dialog dialog;
    ProgressBar loader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        Registrationusername=view.findViewById(R.id.Registration_username);
        Registrationpassword=view.findViewById(R.id.Registration_password);
        Registrationmobile=view.findViewById(R.id.Registration_mobile);
        Registrationemail=view.findViewById(R.id.Registration_email);
        Registrationsubmit=view.findViewById(R.id.Registration_submit);
        Registrationcity=view.findViewById(R.id.Registrationcity);
        createaccount_errormessage=view.findViewById(R.id.createaccount_errormessage);
        Registrationaddress=view.findViewById(R.id.Registrationaddress);
        loading_msg=view.findViewById(R.id.loading_msg);
        loader=view.findViewById(R.id.loader);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.progress);
        dialog = builder.create();



        Registrationpassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lock, 0, R.drawable.visibilityoff, 0);
        Registrationpassword.setTag("show");
        Registrationpassword.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (Registrationpassword.getRight() - Registrationpassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        if (Registrationpassword.getTag().equals("show")) {
                            Registrationpassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lock, 0, R.drawable.eyeopen, 0);
                            Registrationpassword.setTransformationMethod(null);
                            Registrationpassword.setTag("hide");
                        } else {
                            Registrationpassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lock, 0, R.drawable.visibilityoff, 0);
                            Registrationpassword.setTransformationMethod(new PasswordTransformationMethod());
                            Registrationpassword.setTag("show");
                        }
                        return true;
                    }
                }
                return false;
            }
        });

        Registrationsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regusername = Registrationusername.getText().toString().trim();
                String regpass=Registrationpassword.getText().toString().trim();
                String regmobile= Registrationmobile.getText().toString().trim();
                String regemail= Registrationemail.getText().toString().trim();
                String regcity= Registrationcity.getText().toString().trim();
                String regaddress= Registrationaddress.getText().toString().trim();

                regpasspattern= Pattern.compile(Constant.PASSWORDPATTERN);
                regpasswordmatcher=regpasspattern.matcher(regpass);

                regemailpatrren=Pattern.compile(Constant.EMAILPATTERN);
                regemailmatcher=regemailpatrren.matcher(regemail);

                regfirstnamepattern=Pattern.compile(Constant.NAMEPATTERN);
                regfirstnamematcher=regfirstnamepattern.matcher(regusername);

                regmobilepattern=Pattern.compile(Constant.MOBILEPATTERN);
                regmobilematcher=regmobilepattern.matcher(regmobile);



                //  String conformpassword = conformbutton.getText().toString();


                if (isEmpty(regusername)) {
                    createaccount_errormessage.setText(Constant.FULLNAMESHOULDNOTBEEMPTY);

//                    Toast.makeText(getApplicationContext(), "FullName Should Not Be Empty", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), "old password should not be Empty", Toast.LENGTH_SHORT).show();
                }
                else if (!regfirstnamematcher.matches()) {
                    createaccount_errormessage.setText(Constant.USEONLYALPHABETS);
                }
                else if (isEmpty(regemail)) {
                    createaccount_errormessage.setText(Constant.EMAILSHOULDNOTBEEMPTY);
//                    Toast.makeText(getApplicationContext(), "Email Should Not Be Empty", Toast.LENGTH_SHORT).show();
                }
                else if (!regemailmatcher.matches()) {
                    createaccount_errormessage.setText(Constant.INVALIDEMAILADDRESS);
                }
                else if (isEmpty(regpass)) {
                    createaccount_errormessage.setText(Constant.PASSWORDSHOULDNOTBEEMPTY);
//                    Toast.makeText(getApplicationContext(), "Password Should Not Be Empty", Toast.LENGTH_SHORT).show();
                }
                else if (!regpasswordmatcher.matches()) {
                    createaccount_errormessage.setText(Constant.PASSWORDISTOOWEEK);
                }
                else if (isEmpty(regmobile)) {
                    createaccount_errormessage.setText(Constant.MOBILESHOULDNOTBEEMPTY);
//                    Toast.makeText(getApplicationContext(), "Mobile Number Should Not Be Empty", Toast.LENGTH_SHORT).show();
                }
                else if (!regmobilematcher.matches()) {
                    createaccount_errormessage.setText(Constant.USEONLYNUMBERS);
                }else if (!regemailmatcher.matches()) {
                    createaccount_errormessage.setText(Constant.INVALIDEMAILADDRESS);
                } else if (isEmpty(regcity)) {
                    createaccount_errormessage.setText(Constant.CITYSHOULDNOTBEEMPTY);
//                    Toast.makeText(getApplicationContext(), "City Should Not Be Empty", Toast.LENGTH_SHORT).show();
                }
                else if (isEmpty(regaddress)) {
                    createaccount_errormessage.setText(Constant.ADDRESSSHOULDNOTBEEMPTY);
//                    Toast.makeText(getApplicationContext(), "City Should Not Be Empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "Account Created Succesfully", Toast.LENGTH_SHORT).show();
//
//                    Intent L = new Intent(getApplicationContext(), LoginActivity.class);
//                    startActivity(L);
//                    createaccount_errormessage.setText("");

                    createaccount_errormessage.setText("");
                    userregister();
                }
            }

            private void userregister() {


//                dialog.show();


                StringRequest stringRequest = new StringRequest(Request.Method.POST, API.baseurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        dialog.dismiss();

                        try {
                            JSONObject object = new JSONObject(response);
                            {
                                if (object.getString("result").equals("success")) {
                                    Toast.makeText(getActivity(), "Account Created Succesfully", Toast.LENGTH_SHORT).show();

                                    Intent L = new Intent(getActivity(), LoginFragment.class);
                                    startActivity(L);
                                    createaccount_errormessage.setText("");
//                                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getActivity(), " Account Not Created ", Toast.LENGTH_LONG).show();

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
                }) {
                    @Override
                    public Map<String, String> getParams() {

                        Map<String,String> params = new HashMap<String, String>();

                        String regusername = Registrationusername.getText().toString();
                        String regpass=Registrationpassword.getText().toString();
                        String regmobile= Registrationmobile.getText().toString();
                        String regemail= Registrationemail.getText().toString();
                        String regcity= Registrationcity.getText().toString();
                        String regaddr=Registrationaddress.getText().toString();

                        params.put("playground_registration","1");
                        params.put("accesskey","1234");
                        params.put("email", regemail);
                        params.put("password",regpass);
                        params.put("fullname", regusername);
                        params.put("mobile",regmobile);
                        params.put("city",regcity);
                        params.put("address",regaddr);

                        return params;
                    }
                };
                VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);

            }
        });

        return view;
    }
}