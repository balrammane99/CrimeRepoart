package com.intellicloudpps.crimerepoart.Activitys;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class AdminCommentUpdateActivity extends AppCompatActivity {
    Spinner InvestgationUpdateSpinner;
    EditText InvestgationUpdateComment;
    Button InvestgationUpdateSubmitbtn;
    TextView InvestgationUpdateerrormsg;

    String crimeid;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_comment_update);
        InvestgationUpdateSpinner=findViewById(R.id.InvestgationUpdateSpinner);
        InvestgationUpdateComment=findViewById(R.id.InvestgationUpdateComment);
        InvestgationUpdateSubmitbtn=findViewById(R.id.InvestgationUpdateSubmitbtn);
        InvestgationUpdateerrormsg=findViewById(R.id.InvestgationUpdateerrormsg);

        crimeid=SharedSession.getStr(getApplicationContext(),Constant.CRIMEID);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        // spinner
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(getApplicationContext(), R.array.InvestagitionUpdate, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        InvestgationUpdateSpinner.setAdapter(adapter);

        InvestgationUpdateSubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updateinvestgation=InvestgationUpdateComment.getText().toString();

                 if (InvestgationUpdateSpinner.getSelectedItem().toString().trim().equals("Select")) {
                     InvestgationUpdateerrormsg.setText(Constant.SELECTCATEGORY);
                }
                else if (isEmpty(updateinvestgation)) {
                     InvestgationUpdateerrormsg.setText(Constant.COMMENTSHOULDNOTBEEMPTY);
                }
                 else {

                     InvestgationUpdateerrormsg.setText("");
                     giveupdate();
//                     Toast.makeText(getApplicationContext(), Constant.REPOARTUPDATEDSUCESSFULLY, Toast.LENGTH_SHORT).show();

                 }
            }

            private void giveupdate() {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, API.baseurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject object = new JSONObject(response);
                            {
                                if (object.getString("result").equals("success")) {
                                    Toast.makeText(getApplicationContext(), "Update succesfully", Toast.LENGTH_LONG).show();
                                    SharedSession.insertData(getApplicationContext(),Constant.STATUS,status);
                                    Intent L = new Intent(getApplicationContext(), Admin_CRV_Activity.class);
                                    startActivity(L);
                                    InvestgationUpdateerrormsg.setText("");                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Update Not succesfully", Toast.LENGTH_LONG).show();
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


                        status = InvestgationUpdateSpinner.getSelectedItem().toString();
                        String updatecmt = InvestgationUpdateComment.getText().toString();




                        params.put("crimereport_update_insert","1");
                        params.put("accesskey","1234");
                        params.put("crimeid",crimeid);
                        params.put("status", status);
                        params.put("description",updatecmt);
                        return params;
                    }
                };
                VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

            }

        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;

    }

}