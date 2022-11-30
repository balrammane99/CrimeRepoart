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

public class AdminChangePasswordActivity extends AppCompatActivity {

    TextView changepassback;
    EditText admoldppassword, admnewpassword, admconfirmpassword;
    Button admconformbutton;
    TextView admchangepassword_errormessage;


    Matcher oldpassmatcher, newpassmatcher, conformpassmatcher;
    Pattern oldpasspattern, newpasspattern, conformpasspattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_change_password);
        admoldppassword = findViewById(R.id.admchangepassold);
        admnewpassword = findViewById(R.id.admchangenewpass);
        admconfirmpassword = findViewById(R.id.admconfirmchangepass);
        admconformbutton=findViewById(R.id.admconformpasschange);
        admchangepassword_errormessage = findViewById(R.id.admchangepassword_errormessage);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        admoldppassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibilityoff, 0);
        admoldppassword.setTag(Constant.SHOW);

        admoldppassword.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (admoldppassword.getRight() - admoldppassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        if (admoldppassword.getTag().equals(Constant.SHOW)) {
                            admoldppassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eyeopen, 0);
                            admoldppassword.setTransformationMethod(null);
                            admoldppassword.setTag(Constant.HIDE);
                        } else {
                            admoldppassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibilityoff, 0);
                            admoldppassword.setTransformationMethod(new PasswordTransformationMethod());
                            admoldppassword.setTag(Constant.SHOW);
                        }
                        return true;
                    }
                }
                return false;
            }
        });

        admnewpassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibilityoff, 0);
        admnewpassword.setTag(Constant.SHOW);

        admnewpassword.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (admnewpassword.getRight() - admnewpassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        if (admnewpassword.getTag().equals(Constant.SHOW)) {
                            admnewpassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eyeopen, 0);
                            admnewpassword.setTransformationMethod(null);
                            admnewpassword.setTag(Constant.HIDE);
                        } else {
                            admnewpassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibilityoff, 0);
                            admnewpassword.setTransformationMethod(new PasswordTransformationMethod());
                            admnewpassword.setTag(Constant.SHOW);
                        }
                        return true;
                    }
                }
                return false;
            }
        });

        admconfirmpassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibilityoff, 0);
        admconfirmpassword.setTag(Constant.SHOW);

        admconfirmpassword.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (admconfirmpassword.getRight() - admconfirmpassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        if (admconfirmpassword.getTag().equals(Constant.SHOW)) {
                            admconfirmpassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eyeopen, 0);
                            admconfirmpassword.setTransformationMethod(null);
                            admconfirmpassword.setTag(Constant.HIDE);
                        } else {
                            admconfirmpassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibilityoff, 0);
                            admconfirmpassword.setTransformationMethod(new PasswordTransformationMethod());
                            admconfirmpassword.setTag(Constant.SHOW);
                        }
                        return true;
                    }
                }
                return false;
            }
        });





        admconformbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String changeoldpassword = admoldppassword.getText().toString().trim();
                String changenewpassword = admnewpassword.getText().toString().trim();
                String confirmchnagedpassword = admconfirmpassword.getText().toString().trim();
                //  String conformpassword = conformbutton.getText().toString();


                oldpasspattern = Pattern.compile(Constant.PASSWORDPATTERN);
                oldpassmatcher = oldpasspattern.matcher(changeoldpassword);

                newpasspattern = Pattern.compile(Constant.PASSWORDPATTERN);
                newpassmatcher = newpasspattern.matcher(changenewpassword);

                conformpasspattern = Pattern.compile(Constant.PASSWORDPATTERN);
                conformpassmatcher = conformpasspattern.matcher(confirmchnagedpassword);



                if (isEmpty(changeoldpassword)) {
                    //Toast.makeText(getApplicationContext(), "old password should not be Empty", Toast.LENGTH_SHORT).show();
                    admchangepassword_errormessage.setText(Constant.OLDPASSWORDSHOULDNOTBEEMPTY);
                }
//                else if (!oldpassmatcher.matches()) {
//                    admchangepassword_errormessage.setText(Constant.PASSWORDISTOOWEEK);
//                }
                else if (isEmpty(changenewpassword)) {
//                    Toast.makeText(getApplicationContext(), "newpassword should not be Empty", Toast.LENGTH_SHORT).show();
                    admchangepassword_errormessage.setText(Constant.NEWPASSWORDSHOULDNOTBEEMPTY);
                }
//                else if (!newpassmatcher.matches()) {
//                    admchangepassword_errormessage.setText(Constant.NEWPASSWORDTOWEEK);
//                }
                else if (isEmpty(confirmchnagedpassword)) {
//                    Toast.makeText(getApplicationContext(), "conformpassword should not be Empty", Toast.LENGTH_SHORT).show();
                    admchangepassword_errormessage.setText(Constant.CONFIRMPASSWORDSHOULDNOTBEEMPTY);
                }
                else if (!changenewpassword.equals(confirmchnagedpassword)) {
                    admchangepassword_errormessage.setText(Constant.NEWPASSWORDANDCONFORMPASSWORDDOESNOTMATCHED);
                } else {
//                    Toast.makeText(getApplicationContext(), "Password changed", Toast.LENGTH_SHORT).show();
//                    admchangepassword_errormessage.setText("");
                    adminchangepassword();
                }
            }

            private void adminchangepassword() {

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
                                    admoldppassword.setText("");
                                    admnewpassword.setText("");
                                    admconfirmpassword.setText("");
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

                        String changeoldpassword = admoldppassword.getText().toString();
                        String changenewpassword = admnewpassword.getText().toString();



                        params.put("crimereport_admin_changepassword","1");
                        params.put("accesskey",Constant.ACCESSKEY);
                        params.put("username", SharedSession.getStr(getApplicationContext(),Constant.EMAIL));
                        params.put("newpassword",changenewpassword);
                        params.put("oldpassword", changeoldpassword);

                        return params;
                    }
                };
                VolleySingleton.getInstance(AdminChangePasswordActivity.this).addToRequestQueue(stringRequest);
            }

        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;

    }
}