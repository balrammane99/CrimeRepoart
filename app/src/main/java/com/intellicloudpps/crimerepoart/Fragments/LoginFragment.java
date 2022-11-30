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

public class LoginFragment extends Fragment {
    EditText login_Username,login_Password;
    TextView login_errormessage,login_forgotpassword,loading_msg;
    Button loginButton;

    Dialog dialog;
    ProgressBar loader;


    Matcher passwordmatcher,emailmatcher;
    Pattern passpattern,emailpatrren;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        login_Username=view.findViewById(R.id.useremail);
        login_Password=view.findViewById(R.id.userpassword);
        login_errormessage=view.findViewById(R.id.login_errormessage);
        loginButton=view.findViewById(R.id.loginButton);
        loading_msg=view.findViewById(R.id.loading_msg);
        loader=view.findViewById(R.id.loader);



        login_Password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lock, 0, R.drawable.visibilityoff, 0);
        login_Password.setTag("show");
        login_Password.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @java.lang.Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (login_Password.getRight() - login_Password.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        if (login_Password.getTag().equals("show")) {
                            login_Password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lock, 0, R.drawable.eyeopen, 0);
                            login_Password.setTransformationMethod(null);
                            login_Password.setTag("hide");
                        } else {
                            login_Password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lock, 0, R.drawable.visibilityoff, 0);
                            login_Password.setTransformationMethod(new PasswordTransformationMethod());
                            login_Password.setTag("show");
                        }
                        return true;
                    }
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginuser = login_Username.getText().toString().trim();
                String loginpass = login_Password.getText().toString().trim();


                passpattern = Pattern.compile(Constant.PASSWORDPATTERN);
                passwordmatcher = passpattern.matcher(loginpass);

                emailpatrren = Pattern.compile(Constant.EMAILPATTERN);
                emailmatcher = emailpatrren.matcher(loginuser);


                if (isEmpty(loginuser)) {
                    login_errormessage.setText(Constant.EMAILSHOULDNOTBEEMPTY);
                    //Toast.makeText(getApplicationContext(), "old password should not be Empty", Toast.LENGTH_SHORT).show();
                } else if (!emailmatcher.matches()) {
                    login_errormessage.setText(Constant.INVALIDEMAILADDRESS);
                } else if (isEmpty(loginpass)) {
                    login_errormessage.setText(Constant.PASSWORDSHOULDNOTBEEMPTY);
//                    Toast.makeText(getApplicationContext(), "password should not be Empty", Toast.LENGTH_SHORT).show();

                } else if (!passwordmatcher.matches()) {
                    login_errormessage.setText(Constant.PASSWORDISTOOWEEK);
                } else {
//                    Toast.makeText(getActivity(), "login succesfully", Toast.LENGTH_SHORT).show();
//                    Intent L = new Intent(getActivity(), CustomerPageActivity.class);
////                    SharedSession.insertData(getApplicationContext(),Constant.EMAIL,loginuser);
//                    SharedSession.insertData(getActivity(), Constant.EMAIL, loginuser);
//                    SharedSession.insertData(getActivity(), Constant.USERTYPE, "user");
//
//                    startActivity(L);
                    login_errormessage.setText("");

                    userlogin();

                }
            }

            private void userlogin() {


//                dialog.show();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, API.baseurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        dialog.dismiss();

                        try {
                            JSONObject object = new JSONObject(response);
                            {
                                if (object.getString("result").equals("success")) {
//                                    Toast.makeText(getApplicationContext(), "login succesfully", Toast.LENGTH_SHORT).show();
                                    Intent L = new Intent(getActivity(), CustomerPageActivity.class);
//                    SharedSession.insertData(getApplicationContext(),Constant.EMAIL,loginuser);
                                    String loginuser = login_Username.getText().toString();
                                    JSONObject user=(JSONObject)object.getJSONArray("data").get(0);
                                    String fullname = user.getString("fullname");
                                    String email=user.getString("email");
                                    String mobile=user.getString("mobile");
                                    String city=user.getString("city");
                                    String address=user.getString("address");
                                    String image=user.getString("image");


                                    SharedSession.insertData(getActivity(),Constant.EMAIL,loginuser);
                                    SharedSession.insertData(getActivity(), Constant.USERTYPE, "user");
                                    SharedSession.insertData(getActivity(),Constant.FULLNAME,fullname);
                                    SharedSession.insertData(getActivity(),Constant.MOBILE,mobile);
                                    SharedSession.insertData(getActivity(),Constant.CITY,city);
                                    SharedSession.insertData(getActivity(),Constant.ADDRESS,address);
                                    SharedSession.insertData(getActivity(),Constant.IMAGE,image);

                                    startActivity(L);
                                    login_errormessage.setText("");                                }
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


                        String loginuser = login_Username.getText().toString();
                        String loginpass = login_Password.getText().toString();


                        params.put("crimereport_customer_login","1");
                        params.put("accesskey","1234");
                        params.put("email", loginuser);
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
