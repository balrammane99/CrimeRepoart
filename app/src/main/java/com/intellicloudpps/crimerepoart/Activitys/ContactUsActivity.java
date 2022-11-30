package com.intellicloudpps.crimerepoart.Activitys;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

public class ContactUsActivity extends AppCompatActivity {
    EditText contactusnam,contactusnumber,contactusmsg;
    Button contactussubmitButton;
    TextView contectus_errormessage;


    Matcher namematcher,numbermatcher;
    Pattern numberpatrren,namepattern;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        contactusnam=findViewById(R.id.contactusname);
        contactusnumber=findViewById(R.id.contactusnumber);
        contactusmsg=findViewById(R.id.contactusmessage);
        contactussubmitButton=findViewById(R.id.contactussubmit);
        contectus_errormessage=findViewById(R.id.contectus_errormessage);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        contactusnam.setText(SharedSession.getStr(getApplicationContext(),Constant.FULLNAME));
        contactusnumber.setText(SharedSession.getStr(getApplicationContext(),Constant.MOBILE));

        contactussubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contactusname  = contactusnam.getText().toString().trim();
                String contactusnumbers = contactusnumber.getText().toString().trim();
                String contactumsg= contactusmsg.getText().toString().trim();


                numberpatrren=Pattern.compile(Constant.MOBILEPATTERN);
                numbermatcher=numberpatrren.matcher(contactusnumbers);

                namepattern=Pattern.compile(Constant.NAMEPATTERN);
                namematcher=namepattern.matcher(contactusname);



                if (isEmpty(contactusname)) {
//                    Toast.makeText(getApplicationContext(), "Name Should Not Be Empty", Toast.LENGTH_SHORT).show();
                    contectus_errormessage.setText(Constant.NAMESHOULDNOTBEEMPTY);

                }else if (!namematcher.matches()) {
                    contectus_errormessage.setText(Constant.USEONLYALPHABETS);
                }
                else if (isEmpty(contactusnumbers)) {
//                    Toast.makeText(getApplicationContext(), "Email Should Not Be Empty", Toast.LENGTH_SHORT).show();
                    contectus_errormessage.setText(Constant.MOBILESHOULDNOTBEEMPTY);

                }
                else if (!numbermatcher.matches()) {
                    contectus_errormessage.setText(Constant.USEONLYNUMBERS);
                }
                else if (isEmpty(contactumsg)) {
                    contectus_errormessage.setText(Constant.MESSAGESHOULDNOTBEEMPTY);

                } else {
//                    contectus_errormessage.setText("");
                    usercontectus();
                }

            }
            private void usercontectus() {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, API.baseurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);
                            {
                                if (object.getString(Constant.RESULT).equals(Constant.SUCCESS)) {

                                    Toast.makeText(getApplicationContext(), "Successfully Sends Information", Toast.LENGTH_SHORT).show();
                                    contactusmsg.setText(" ");

                                } else {
                                    Toast.makeText(getApplicationContext(), " Information Not Sends Successfully ", Toast.LENGTH_LONG).show();

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


                        String contactumsg= contactusmsg.getText().toString();
                        String contactusname  = contactusnam.getText().toString();
                        String contactusnumbers = contactusnumber.getText().toString();

                        params.put("crimereport_contactus","1");
                        params.put("accesskey","1234");
                        params.put("name",contactusname);
                        params.put("mobile",contactusnumbers);
                        params.put("message", contactumsg);

                        return params;
                    }
                };
                VolleySingleton.getInstance(ContactUsActivity.this).addToRequestQueue(stringRequest);

            }

        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}