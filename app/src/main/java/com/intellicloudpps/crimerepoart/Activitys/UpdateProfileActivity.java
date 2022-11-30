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
import com.intellicloudpps.crimerepoart.Network.API;
import com.intellicloudpps.crimerepoart.Network.VolleySingleton;
import com.intellicloudpps.crimerepoart.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateProfileActivity extends AppCompatActivity {
    EditText updateproffullname, updateprofmobil, updateprofaddress, updateprofemail, updateprofcity;
    Button updatesubmit;
    TextView updateprofile_errormessage;

    Matcher updatefirstnamematcher,updatemobilematcher,updateemailmatcher,updatepasswordmatcher;
    Pattern updatepasspattern,updateemailpatrren,updatefirstnamepattern,updatemobilepattern,updateaddresspattern;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        updateproffullname = findViewById(R.id.updateprofullname);
        updateprofmobil = findViewById(R.id.updatprofmobile);
        updateprofaddress = findViewById(R.id.updateprofaddress);
        updateprofemail = findViewById(R.id.updateprofemail);
        updateprofcity = findViewById(R.id.updateprofcity);
        updatesubmit = findViewById(R.id.updateprofilesubmit);
        updateprofile_errormessage=findViewById(R.id.updateprofile_errormessage);





        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        updatesubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String updateprofilefullname = updateproffullname.getText().toString();
                String updateprofilemobile = updateprofmobil.getText().toString();
                String updateprofileaddress = updateprofaddress.getText().toString();
                String updateprofileaccemail = updateprofemail.getText().toString();
                String updateprofileacccity = updateprofcity.getText().toString();


                updateemailpatrren= Pattern.compile(Constant.EMAILPATTERN);
                updateemailmatcher=updateemailpatrren.matcher(updateprofileaccemail);

                updatefirstnamepattern=Pattern.compile(Constant.NAMEPATTERN);
                updatefirstnamematcher=updatefirstnamepattern.matcher(updateprofilefullname);

                updatemobilepattern=Pattern.compile(Constant.MOBILEPATTERN);
                updatemobilematcher=updatemobilepattern.matcher(updateprofilemobile);



                if (isEmpty(updateprofilefullname)) {
//                    Toast.makeText(getApplicationContext(), "FullName should not be Empty", Toast.LENGTH_SHORT).show();
                    updateprofile_errormessage.setText(Constant.FULLNAMESHOULDNOTBEEMPTY);
                }
                else if (!updatefirstnamematcher.matches()) {
                    updateprofile_errormessage.setText(Constant.USEONLYALPHABETS);
                }
                else if (isEmpty(updateprofileaccemail)) {
//                    Toast.makeText(getApplicationContext(), "Email should not be Empty", Toast.LENGTH_SHORT).show();
                    updateprofile_errormessage.setText(Constant.EMAILSHOULDNOTBEEMPTY);

                }
                else if (!updateemailmatcher.matches()) {
                    updateprofile_errormessage.setText(Constant.INVALIDEMAILADDRESS);

                }
                else if (isEmpty(updateprofilemobile)) {
//                    Toast.makeText(getApplicationContext(), "Mobile should not be Empty", Toast.LENGTH_SHORT).show();
                    updateprofile_errormessage.setText(Constant.MOBILESHOULDNOTBEEMPTY);
                }
                else if (!updatemobilematcher.matches()) {
                    updateprofile_errormessage.setText(Constant.USEONLYNUMBERS);
                }
                else if (isEmpty(updateprofileaddress)) {
//                    Toast.makeText(getApplicationContext(), "Address should not be Empty", Toast.LENGTH_SHORT).show();
                    updateprofile_errormessage.setText(Constant.ADDRESSSHOULDNOTBEEMPTY);

                }

                else if (isEmpty(updateprofileacccity)) {
//                    Toast.makeText(getApplicationContext(), "Password should not be Empty", Toast.LENGTH_SHORT).show();
                    updateprofile_errormessage.setText(Constant.CITYSHOULDNOTBEEMPTY);

                }
                else {
//                    Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();

                    userupdatedprofile();
                    updateprofile_errormessage.setText("");
                }


            }

            private void userupdatedprofile() {
                StringRequest stringRequest = new StringRequest(Request.Method.PUT, API.baseurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);
                            {
                                if (object.getString(Constant.RESULT).equals(Constant.SUCCESS)) {
                                    Toast.makeText(getApplicationContext(), Constant.PROFILESUCESSFULLYUPDATED, Toast.LENGTH_SHORT).show();
//                                    Intent g = new Intent(getApplicationContext(), MyAccountFragment.class);
//                                    startActivity(g);

//                                    createaccount_errormessage.setText("");
//                                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), Constant.PROFILESUCESSFULLYNOTUPDATED, Toast.LENGTH_LONG).show();

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

                        String updateprofilefullname = updateproffullname.getText().toString();
                        String updateprofilemobile = updateprofmobil.getText().toString();
                        String updateprofileaddress = updateprofaddress.getText().toString();
                        String updateprofileaccemail = updateprofemail.getText().toString();
                        String updateprofileacccity = updateprofcity.getText().toString();


                        params.put("crimereport_customer_update_profile","1");
                        params.put("accesskey",Constant.ACCESSKEY);
                        params.put("email", updateprofileaccemail);
                        params.put("fullname", updateprofilefullname);
                        params.put("mobile",updateprofilemobile);
                        params.put("address",updateprofileaddress);
                        params.put("city",updateprofileacccity);

                        return params;
                    }
                };
                VolleySingleton.getInstance(UpdateProfileActivity.this).addToRequestQueue(stringRequest);

            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}