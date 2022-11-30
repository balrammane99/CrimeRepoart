package com.intellicloudpps.crimerepoart.Activitys;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.intellicloudpps.crimerepoart.Healper.Constant;
import com.intellicloudpps.crimerepoart.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminUpdateProfileActivity extends AppCompatActivity {

    EditText admupdateproffullname, admupdateprofmobil, admupdateprofaddress, admupdateprofemail, admupdateprofpass;
    Button admupdatesubmit;
    TextView admupdateprofile_errormessage;

    Matcher admupdatefirstnamematcher,admupdatemobilematcher,admupdateemailmatcher,admupdatepasswordmatcher;
    Pattern admupdatepasspattern,admupdateemailpatrren,admupdatefirstnamepattern,admupdatemobilepattern,admupdateaddresspattern;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_profile);
        admupdateproffullname = findViewById(R.id.admupdateprofullname);
        admupdateprofmobil = findViewById(R.id.admupdatprofmobile);
        admupdateprofaddress = findViewById(R.id.admupdateprofaddress);
        admupdateprofemail = findViewById(R.id.admupdateprofemail);
        admupdateprofpass = findViewById(R.id.admupdateprofpassword);
        admupdatesubmit = findViewById(R.id.admupdateprofilesubmit);
        admupdateprofile_errormessage=findViewById(R.id.admupdateprofile_errormessage);





        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        admupdatesubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String updateprofilefullname = admupdateproffullname.getText().toString();
                String updateprofilemobile = admupdateprofmobil.getText().toString();
                String updateprofileaddress = admupdateprofaddress.getText().toString();
                String updateprofileaccemail = admupdateprofemail.getText().toString();
                String updateprofileaccpassword = admupdateprofpass.getText().toString();


                admupdateemailpatrren= Pattern.compile(Constant.EMAILPATTERN);
                admupdateemailmatcher=admupdateemailpatrren.matcher(updateprofileaccemail);

                admupdatefirstnamepattern=Pattern.compile(Constant.NAMEPATTERN);
                admupdatefirstnamematcher=admupdatefirstnamepattern.matcher(updateprofilefullname);

                admupdatemobilepattern=Pattern.compile(Constant.MOBILEPATTERN);
                admupdatemobilematcher=admupdatemobilepattern.matcher(updateprofilemobile);



                if (isEmpty(updateprofilefullname)) {
//                    Toast.makeText(getApplicationContext(), "FullName should not be Empty", Toast.LENGTH_SHORT).show();
                    admupdateprofile_errormessage.setText(Constant.FULLNAMESHOULDNOTBEEMPTY);
                }
                else if (!admupdatefirstnamematcher.matches()) {
                    admupdateprofile_errormessage.setText(Constant.USEONLYALPHABETS);
                }
                else if (isEmpty(updateprofilemobile)) {
//                    Toast.makeText(getApplicationContext(), "Mobile should not be Empty", Toast.LENGTH_SHORT).show();
                    admupdateprofile_errormessage.setText(Constant.MOBILESHOULDNOTBEEMPTY);
                }
                else if (!admupdatemobilematcher.matches()) {
                    admupdateprofile_errormessage.setText(Constant.USEONLYNUMBERS);
                }else if (isEmpty(updateprofileaddress)) {
//                    Toast.makeText(getApplicationContext(), "Address should not be Empty", Toast.LENGTH_SHORT).show();
                    admupdateprofile_errormessage.setText(Constant.ADDRESSSHOULDNOTBEEMPTY);

                } else if (isEmpty(updateprofileaccemail)) {
//                    Toast.makeText(getApplicationContext(), "Email should not be Empty", Toast.LENGTH_SHORT).show();
                    admupdateprofile_errormessage.setText(Constant.EMAILSHOULDNOTBEEMPTY);

                }
                else if (!admupdateemailmatcher.matches()) {
                    admupdateprofile_errormessage.setText(Constant.INVALIDEMAILADDRESS);

                }else if (isEmpty(updateprofileaccpassword)) {
//                    Toast.makeText(getApplicationContext(), "Password should not be Empty", Toast.LENGTH_SHORT).show();
                    admupdateprofile_errormessage.setText(Constant.PASSWORDSHOULDNOTBEEMPTY);

                } else {
                    Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();

//                    userupdatedprofile();
                    admupdateprofile_errormessage.setText("");
                }


            }

//            private void userupdatedprofile() {
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, API.baseurl, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            JSONObject object = new JSONObject(response);
//                            {
//                                if (object.getString(Constant.RESULT).equals(Constant.SUCESS)) {
//                                    Toast.makeText(getApplicationContext(), Constant.PROFILESUCESSFULLYUPDATED, Toast.LENGTH_SHORT).show();
////                                    Intent g = new Intent(getApplicationContext(), MyAccountFragment.class);
////                                    startActivity(g);
//
////                                    createaccount_errormessage.setText("");
////                                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
//                                } else {
//                                    Toast.makeText(getApplicationContext(), Constant.PROFILESUCESSFULLYNOTUPDATED, Toast.LENGTH_LONG).show();
//
//                                }
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        VolleyLog.d(Constant.MAIN, "Error: " + error.getMessage());
//                        Log.d(Constant.MAIN, "" + error.getMessage() + "," + error.toString());
//                    }
//                }) {
//                    @Override
//                    public Map<String, String> getParams() {
//
//                        Map<String,String> params = new HashMap<String, String>();
//
//                        String updateprofilefullname = updateproffullname.getText().toString();
//                        String updateprofilemobile = updateprofmobil.getText().toString();
//                        String updateprofileaddress = updateprofaddress.getText().toString();
//                        String updateprofileaccemail = updateprofemail.getText().toString();
//
//
//                        params.put("playground_groundowner_updateprofile","1");
//                        params.put("accesskey",Constant.ACCESSKEY);
//                        params.put("email", updateprofileaccemail);
//                        params.put("fullname", updateprofilefullname);
//                        params.put("mobile",updateprofilemobile);
//                        params.put("city",updateprofileaddress);
//
//                        return params;
//                    }
//                };
//                VolleySingleton.getInstance(UpdateProfileActivity.this).addToRequestQueue(stringRequest);
//
//            }
        });
        admupdateprofpass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibilityoff, 0);
        admupdateprofpass.setTag(Constant.SHOW);

        admupdateprofpass.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (admupdateprofpass.getRight() - admupdateprofpass.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        if (admupdateprofpass.getTag().equals(Constant.SHOW)) {
                            admupdateprofpass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eyeopen, 0);
                            admupdateprofpass.setTransformationMethod(null);
                            admupdateprofpass.setTag(Constant.HIDE);
                        } else {
                            admupdateprofpass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibilityoff, 0);
                            admupdateprofpass.setTransformationMethod(new PasswordTransformationMethod());
                            admupdateprofpass.setTag(Constant.SHOW);
                        }
                        return true;
                    }
                }
                return false;
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}