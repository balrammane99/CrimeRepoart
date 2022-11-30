package com.intellicloudpps.crimerepoart.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.intellicloudpps.crimerepoart.Adapters.Adm_VAC_Adapter;
import com.intellicloudpps.crimerepoart.Adapters.AdminCommentAdapter;
import com.intellicloudpps.crimerepoart.Beans.AdminCommentBeans;
import com.intellicloudpps.crimerepoart.Beans.AdminCrimeBeans;
import com.intellicloudpps.crimerepoart.Healper.Constant;
import com.intellicloudpps.crimerepoart.Healper.SharedSession;
import com.intellicloudpps.crimerepoart.Network.API;
import com.intellicloudpps.crimerepoart.Network.VolleySingleton;
import com.intellicloudpps.crimerepoart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Admin_CRV_Activity extends AppCompatActivity {
    RecyclerView Adm_Cmt_rv;
    Button Adm_update_cmt_btn;
    TextView complentPN,complentPE,complentPM,adm_update_status;
    List<AdminCommentBeans> list=new ArrayList<>();
    AdminCommentAdapter adminCommentAdapter;

    String crimeid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_crv);
        adm_update_status=findViewById(R.id.adm_update_status);
        complentPE=findViewById(R.id.complentPE);
        complentPM=findViewById(R.id.complentPM);
        complentPN=findViewById(R.id.complentPN);
        Adm_update_cmt_btn=findViewById(R.id.Adm_update_cmt_btn);
        Adm_Cmt_rv=findViewById(R.id.Adm_Cmt_rv);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        crimeid=SharedSession.getStr(getApplicationContext(),Constant.CRIMEID);
//        Toast.makeText(getApplicationContext(),crimeid,Toast.LENGTH_SHORT).show();

        adm_update_status.setText(SharedSession.getStr(getApplicationContext(),Constant.STATUS));



//        AdminCommentBeans adminCommentBeans1=new AdminCommentBeans(121,"10/05/2022","jhbhafnjakajajifkkjbajkfhjkxcnajskvbkjvbajskvbakjvbxkxb abvahvbdahbkbisavjkavbai jhbjhvb vbajvbajbajvbdaiv");
//        adminCommentBeans.add(adminCommentBeans1);
//        AdminCommentBeans adminCommentBeans2=new AdminCommentBeans(122,"10/05/2022","jhbhafnjakajajifkkjbajkfhjkxcnajskvbkjvbajskvbakjvbxkxb abvahvbdahbkbisavjkavbai jhbjhvb vbajvbajbajvbdaiv jhhfajffjkjnkjanfsanfa jjadjkfakjabjkadjf jahfbasjfbadkj nbvajdfbd jhfgahfhb jxbjdvb jhbdvjhavb ");
//        adminCommentBeans.add(adminCommentBeans2);
//        AdminCommentBeans adminCommentBeans3=new AdminCommentBeans(121,"10/05/2022","jhbhafnjakajajifkkjbajkfhjkxcnajskvbkjvbajskvbakjvbxkxb abvahvbdahbkbisavjkavbai jhbjhvb vbajvbajbajvbdaivk  jfnajkanfjkdandajkgndajk kjdhjkfadkjfdanjka kjfnkjdh jnkjgnsdo ");
//        adminCommentBeans.add(adminCommentBeans3);
//        AdminCommentBeans adminCommentBeans4=new AdminCommentBeans(122,"10/05/2022","jhbhafnjakajajifkkjbajkfhjkxcnajskvbkjvbajskvbakjvbxkxb abvahvbdahbkbisavjkavbai jhbjhvb vbajvbajbajvbdaiv kjbakjgbgkjadvnfakdjgbkjxbvkjvbdvbvkjdsvbkjdbvidsv nnabjkvadhvj jhvbdhvb jhbajdh bnkjdvb");
//        adminCommentBeans.add(adminCommentBeans4);
//        AdminCommentBeans adminCommentBeans5=new AdminCommentBeans(121,"10/05/2022","jhbhafnjakajajifkkjbajkfhjkxcnajskvbkjvbajskvbakjvbxkxb abvahvbdahbkbisavjkavbai jhbjhvb vbajvbajbajvbdaiv kjhadkjhad ajvbjdav jhbfadjfhb jhzvbajdk jhjdvb jnvdsbdjbdjkfh jhbdfjjd jkgbkjds ");
//        adminCommentBeans.add(adminCommentBeans5);
//        AdminCommentBeans adminCommentBeans6=new AdminCommentBeans(122,"10/05/2022","jhbhafnjakajajifkkjbajkfhjkxcnajskvbkjvbajskvbakjvbxkxb abvahvbdahbkbisavjkavbai jhbjhvb vbajvbajbajvbdaiv kjfkajsbda javfbda jadfbakjdf jfbdaj ajfbkjd akjfbkjf ajfbdajkf jajfb kjafvkj kajdn kjfndakj kjvbsdkjv kdvbdsjkv  kjdsgnskdj sdvnsdk ,mdvnsd, ");
//        adminCommentBeans.add(adminCommentBeans6);
//        AdminCommentBeans adminCommentBeans7=new AdminCommentBeans(121,"10/05/2022","jhbhafnjakajajifkkjbajkfhjkxcnajskvbkjvbajskvbakjvbxkxb abvahvbdahbkbisavjkavbai jhbjhvb vbajvbajbajvbdaivk  jfnajkanfjkdandajkgndajk kjdhjkfadkjfdanjka kjfnkjdh jnkjgnsdo ");
//        adminCommentBeans.add(adminCommentBeans7);
//        AdminCommentBeans adminCommentBeans8=new AdminCommentBeans(122,"10/05/2022","jhbhafnjakajajifkkjbajkfhjkxcnajskvbkjvbajskvbakjvbxkxb abvahvbdahbkbisavjkavbai jhbjhvb vbajvbajbajvbdaiv kjbakjgbgkjadvnfakdjgbkjxbvkjvbdvbvkjdsvbkjdbvidsv nnabjkvadhvj jhvbdhvb jhbajdh bnkjdvb");
//        adminCommentBeans.add(adminCommentBeans8);
//        AdminCommentBeans adminCommentBeans9=new AdminCommentBeans(121,"10/05/2022","jhbhafnjakajajifkkjbajkfhjkxcnajskvbkjvbajskvbakjvbxkxb abvahvbdahbkbisavjkavbai jhbjhvb vbajvbajbajvbdaiv kjhadkjhad ajvbjdav jhbfadjfhb jhzvbajdk jhjdvb jnvdsbdjbdjkfh jhbdfjjd jkgbkjds ");
//        adminCommentBeans.add(adminCommentBeans9);
//        AdminCommentBeans adminCommentBeans10=new AdminCommentBeans(122,"10/05/2022","jhbhafnjakajajifkkjbajkfhjkxcnajskvbkjvbajskvbakjvbxkxb abvahvbdahbkbisavjkavbai jhbjhvb vbajvbajbajvbdaiv kjfkajsbda javfbda jadfbakjdf jfbdaj ajfbkjd akjfbkjf ajfbdajkf jajfb kjafvkj kajdn kjfndakj kjvbsdkjv kdvbdsjkv  kjdsgnskdj sdvnsdk ,mdvnsd, ");
//        adminCommentBeans.add(adminCommentBeans10);
//
//
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
//        Adm_Cmt_rv.setLayoutManager(linearLayoutManager);
//        adminCommentAdapter=new AdminCommentAdapter(this,adminCommentBeans);
//        Adm_Cmt_rv.setAdapter(adminCommentAdapter);

        getrepoartupdat();


        Adm_update_cmt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(getApplicationContext(),AdminCommentUpdateActivity.class);
                startActivity(j);
            }
        });


    }
    private void getrepoartupdat() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, API.baseurl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
//                Toast.makeText(getApplicationContext(), "response", Toast.LENGTH_SHORT).show();

                try {
                    JSONObject object1 = new JSONObject(response);
                    {
//                        Toast.makeText(getApplicationContext(), "object1", Toast.LENGTH_SHORT).show();
                        if (object1.getString(Constant.RESULT).equals(Constant.SUCCESS)) {
                            JSONArray adminreportdata1=object1.getJSONArray("data");

                            int i;
//                           list = new ArrayList<AdminCrimeBeans>();

                            for (i=0; i<adminreportdata1.length(); i++){
                                JSONObject mydataobj1= (JSONObject) adminreportdata1.get(i);
                                int id1=mydataobj1.getInt("id");
//                                String crimeid1=mydataobj1.getString("crimeid");
                                String description1=mydataobj1.getString("description");
                                String udatetime1=mydataobj1.getString("udatetime");


                                AdminCommentBeans adminCommentBeans= new AdminCommentBeans();
                                adminCommentBeans.setId(id1);
//                                adminCommentBeans.setCrimeid(crimeid1);
                                adminCommentBeans.setComment(description1);
                                adminCommentBeans.setDate(udatetime1);
                                list.add(adminCommentBeans);
                            }

                            LinearLayoutManager ll=new LinearLayoutManager(getApplicationContext());
                            ll.setOrientation(RecyclerView.VERTICAL);
                            Adm_Cmt_rv.setLayoutManager(ll);
                            adminCommentAdapter=new AdminCommentAdapter(getApplicationContext(),list);
//                            Log.e("image",list.toString());
                            Adm_Cmt_rv.setAdapter(adminCommentAdapter);


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
                params.put("crimereport_update_viewallby_crimeid","1");
                params.put("accesskey","1234");
                params.put("crimeid", crimeid);
                return params;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;

    }

}