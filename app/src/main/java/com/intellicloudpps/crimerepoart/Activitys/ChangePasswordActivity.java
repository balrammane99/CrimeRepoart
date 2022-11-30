package com.intellicloudpps.crimerepoart.Activitys;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
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

public class ChangePasswordActivity extends AppCompatActivity {
    TextView changepassback;
    EditText oldppassword, newpassword, confirmpassword;
    Button conformbutton;
    TextView changepassword_errormessage;


    Matcher oldpassmatcher, newpassmatcher, conformpassmatcher;
    Pattern oldpasspattern, newpasspattern, conformpasspattern;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        oldppassword = findViewById(R.id.changepassold);
        newpassword = findViewById(R.id.changenewpass);
        confirmpassword = findViewById(R.id.confirmchangepass);
        conformbutton=findViewById(R.id.conformpasschange);
        changepassword_errormessage = findViewById(R.id.changepassword_errormessage);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        oldppassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibilityoff, 0);
        oldppassword.setTag(Constant.SHOW);

        oldppassword.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (oldppassword.getRight() - oldppassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        if (oldppassword.getTag().equals(Constant.SHOW)) {
                            oldppassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eyeopen, 0);
                            oldppassword.setTransformationMethod(null);
                            oldppassword.setTag(Constant.HIDE);
                        } else {
                            oldppassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibilityoff, 0);
                            oldppassword.setTransformationMethod(new PasswordTransformationMethod());
                            oldppassword.setTag(Constant.SHOW);
                        }
                        return true;
                    }
                }
                return false;
            }
        });

        newpassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibilityoff, 0);
        newpassword.setTag(Constant.SHOW);

        newpassword.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (newpassword.getRight() - newpassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        if (newpassword.getTag().equals(Constant.SHOW)) {
                            newpassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eyeopen, 0);
                            newpassword.setTransformationMethod(null);
                            newpassword.setTag(Constant.HIDE);
                        } else {
                            newpassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibilityoff, 0);
                            newpassword.setTransformationMethod(new PasswordTransformationMethod());
                            newpassword.setTag(Constant.SHOW);
                        }
                        return true;
                    }
                }
                return false;
            }
        });

        confirmpassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibilityoff, 0);
        confirmpassword.setTag(Constant.SHOW);

        confirmpassword.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (confirmpassword.getRight() - confirmpassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        if (confirmpassword.getTag().equals(Constant.SHOW)) {
                            confirmpassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eyeopen, 0);
                            confirmpassword.setTransformationMethod(null);
                            confirmpassword.setTag(Constant.HIDE);
                        } else {
                            confirmpassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibilityoff, 0);
                            confirmpassword.setTransformationMethod(new PasswordTransformationMethod());
                            confirmpassword.setTag(Constant.SHOW);
                        }
                        return true;
                    }
                }
                return false;
            }
        });





        conformbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String changeoldpassword = oldppassword.getText().toString().trim();
                String changenewpassword = newpassword.getText().toString().trim();
                String confirmchnagedpassword = confirmpassword.getText().toString().trim();
                //  String conformpassword = conformbutton.getText().toString();


                oldpasspattern = Pattern.compile(Constant.PASSWORDPATTERN);
                oldpassmatcher = oldpasspattern.matcher(changeoldpassword);

                newpasspattern = Pattern.compile(Constant.PASSWORDPATTERN);
                newpassmatcher = newpasspattern.matcher(changenewpassword);

                conformpasspattern = Pattern.compile(Constant.PASSWORDPATTERN);
                conformpassmatcher = conformpasspattern.matcher(confirmchnagedpassword);



                if (isEmpty(changeoldpassword)) {
                    //Toast.makeText(getApplicationContext(), "old password should not be Empty", Toast.LENGTH_SHORT).show();
                    changepassword_errormessage.setText(Constant.OLDPASSWORDSHOULDNOTBEEMPTY);
                }
                else if (!oldpassmatcher.matches()) {
                    changepassword_errormessage.setText(Constant.PASSWORDISTOOWEEK);
                }
                else if (isEmpty(changenewpassword)) {
//                    Toast.makeText(getApplicationContext(), "newpassword should not be Empty", Toast.LENGTH_SHORT).show();
                    changepassword_errormessage.setText(Constant.NEWPASSWORDSHOULDNOTBEEMPTY);
                }
                else if (!newpassmatcher.matches()) {
                    changepassword_errormessage.setText(Constant.NEWPASSWORDTOWEEK);
                }
                else if (isEmpty(confirmchnagedpassword)) {
//                    Toast.makeText(getApplicationContext(), "conformpassword should not be Empty", Toast.LENGTH_SHORT).show();
                    changepassword_errormessage.setText(Constant.CONFIRMPASSWORDSHOULDNOTBEEMPTY);
                }
                else if (!changenewpassword.equals(confirmchnagedpassword)) {
                    changepassword_errormessage.setText(Constant.NEWPASSWORDANDCONFORMPASSWORDDOESNOTMATCHED);
                } else {
//                    Toast.makeText(getApplicationContext(), "Password changed", Toast.LENGTH_SHORT).show();
                    changepassword_errormessage.setText("");
                    userchangepassword();
                }
            }

            private void userchangepassword() {

                StringRequest stringRequest = new StringRequest(Request.Method.PUT, API.baseurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);
                            {
                                if (object.getString(Constant.RESULT).equals(Constant.SUCCESS)) {
                                    Toast.makeText(getApplicationContext(), Constant.PASSWORDSUCESSFULLYUPDATED, Toast.LENGTH_SHORT).show();
//                                    Intent d = new Intent(getApplicationContext(), MyAccountFragment.class);
//                                    startActivity(d);


//                                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), Constant.PASSWORDSUCESSFULLYNOTUPDATED, Toast.LENGTH_LONG).show();

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

                        String changeoldpassword = oldppassword.getText().toString();
                        String changenewpassword = newpassword.getText().toString();

                        params.put("crimereport_customer_changepassword","1");
                        params.put("accesskey",Constant.ACCESSKEY);
                        params.put("email", SharedSession.getStr(getApplicationContext(),Constant.EMAIL));
                        params.put("newpassword",changenewpassword);
                        params.put("oldpassword", changeoldpassword);

                        return params;
                    }
                };
                VolleySingleton.getInstance(ChangePasswordActivity.this).addToRequestQueue(stringRequest);
            }

        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;

    }
}