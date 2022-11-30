package com.intellicloudpps.crimerepoart.Activitys;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.intellicloudpps.crimerepoart.Healper.Constant;
import com.intellicloudpps.crimerepoart.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminContactUsActivity extends AppCompatActivity {

    EditText admcontactusnam,admcontactusnumber,admcontactusmsg;
    Button admcontactussubmitButton;
    TextView admcontectus_errormessage;


    Matcher namematcher,numbermatcher;
    Pattern numberpatrren,namepattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_contact_us);
        admcontactusnam=findViewById(R.id.admcontactusname);
        admcontactusnumber=findViewById(R.id.admcontactusnumber);
        admcontactusmsg=findViewById(R.id.admcontactusmessage);
        admcontactussubmitButton=findViewById(R.id.admcontactussubmit);
        admcontectus_errormessage=findViewById(R.id.admcontectus_errormessage);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        admcontactussubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CONTACTUSNAME  = admcontactusnam.getText().toString().trim();
                String contactusnumbers = admcontactusnumber.getText().toString().trim();
                String contactumsg= admcontactusmsg.getText().toString().trim();


                numberpatrren=Pattern.compile(Constant.MOBILEPATTERN);
                numbermatcher=numberpatrren.matcher(contactusnumbers);

                namepattern=Pattern.compile(Constant.NAMEPATTERN);
                namematcher=namepattern.matcher(CONTACTUSNAME);



                if (isEmpty(CONTACTUSNAME)) {
//                    Toast.makeText(getApplicationContext(), "Name Should Not Be Empty", Toast.LENGTH_SHORT).show();
                    admcontectus_errormessage.setText(Constant.NAMESHOULDNOTBEEMPTY);

                }else if (!namematcher.matches()) {
                    admcontectus_errormessage.setText(Constant.USEONLYALPHABETS);
                }
                else if (isEmpty(contactusnumbers)) {
//                    Toast.makeText(getApplicationContext(), "Email Should Not Be Empty", Toast.LENGTH_SHORT).show();
                    admcontectus_errormessage.setText(Constant.MOBILESHOULDNOTBEEMPTY);

                }
                else if (!numbermatcher.matches()) {
                    admcontectus_errormessage.setText(Constant.USEONLYNUMBERS);
                }
                else if (isEmpty(contactumsg)) {
//                    Toast.makeText(getApplicationContext(), "Message Should Not Be Empty", Toast.LENGTH_SHORT).show();
                    admcontectus_errormessage.setText(Constant.MESSAGESHOULDNOTBEEMPTY);

                } else {

                    Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
                    admcontectus_errormessage.setText("");
//                    usercontectus();
                }

            }
//            private void usercontectus() {
//
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, API.baseurl, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            JSONObject object = new JSONObject(response);
//                            {
//                                if (object.getString("result").equals("success")) {
//                                    Toast.makeText(getApplicationContext(), "Message Send successfully", Toast.LENGTH_SHORT).show();
//                                    contactusmsg.setText(" ");
//
////                                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
//                                } else {
//                                    Toast.makeText(getApplicationContext(), " Message Send  not  successfully ", Toast.LENGTH_LONG).show();
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
//                        VolleyLog.d("Main", "Error: " + error.getMessage());
//                        Log.d("Main", "" + error.getMessage() + "," + error.toString());
//                    }
//                }) {
//                    @Override
//                    public Map<String, String> getParams() {
//
//                        Map<String,String> params = new HashMap<String, String>();
//
//                        String contactumsg= contactusmsg.getText().toString();
//
//
//                        params.put("playground_feedback_insert","1");
//                        params.put("accesskey","1234");
//                        params.put("name",SharedSession.getStr(getApplicationContext(),Constant.FULLNAME));
//                        params.put("mobile",SharedSession.getStr(getApplicationContext(),Constant.MOBILE));
//                        params.put("message", contactumsg);
//
//                        return params;
//                    }
//                };
//                VolleySingleton.getInstance(ContactusActivity.this).addToRequestQueue(stringRequest);
//
//            }

        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;

    }
}