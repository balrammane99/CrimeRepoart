package com.intellicloudpps.crimerepoart.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.intellicloudpps.crimerepoart.Adapters.Adm_VAC_Adapter;
import com.intellicloudpps.crimerepoart.Beans.AdminCrimeBeans;
import com.intellicloudpps.crimerepoart.Healper.Constant;
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


public class AdminViewReportFragment extends Fragment implements SearchView.OnQueryTextListener{
    RecyclerView adm_viewallcrimerepoartrecycle;
    SearchView adm_vr_search;
//    ArrayList<AdminCrimeBeans> adminCrimeBeans=new ArrayList<>();

    List<AdminCrimeBeans> list=new ArrayList<>();
    Adm_VAC_Adapter adm_vac_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_admin_view_report, container, false);
        adm_viewallcrimerepoartrecycle=view.findViewById(R.id.adm_viewallcrimerepoartrecycle);
        adm_vr_search=view.findViewById(R.id.adm_vr_search);


        adm_vr_search.setOnQueryTextListener(AdminViewReportFragment.this);
        adm_vr_search.setIconifiedByDefault(false);
        adm_vr_search.setIconified(false);
//
//        AdminCrimeBeans adminCrimeBeans12=new AdminCrimeBeans(10215,"missing","jhafbadjfb bvjdf In ordinary language, a crime is an unlawful act punishable by a state ; The notion that acts such as murder ; The state (government)  Usually, to be classified as","08-05-2022","pending","06-05-2022","10:45 am","Missing","Kphb","500072","Phanvi kumar","phanvikumar@gmail.com","9850716866");
//        list.add(adminCrimeBeans12);
//        AdminCrimeBeans adminCrimeBeans13=new AdminCrimeBeans(10216,"chain thift","In ordinary language, a crime is an unlawful act punishable by a state ; The notion","09-05-2022","complet","08-05-2022","11:45 am","Thift","mosapet","500005","Phanvi kumar","phanvikumar@gmail.com","9850716866");
//        list.add(adminCrimeBeans13);
//        AdminCrimeBeans adminCrimeBeans17=new AdminCrimeBeans(10216,"chain thift","jhafbadjfb bvjdf In ordinary language, a crime is an unlawful act punishable by a state ; The notion that acts such as murder ; The state (government)  Usually, to be classified as","09-05-2022","complet","08-05-2022","11:45 am","Thift","bandlaguda","500005","Phanvi kumar","phanvikumar@gmail.com","9850716866");
//        list.add(adminCrimeBeans17);
//        AdminCrimeBeans adminCrimeBeans14=new AdminCrimeBeans(10217,"muder","jhafbadjfb bvjdf In ordinary language, a crime is an unlawful act punishable by a state ; The notion that acts such as murder","09-05-2022","parsuning","07-05-2022","05:45 am","muder","chandrayangutta","500053","Phanvi kumar","phanvikumar@gmail.com","9850716866");
//        list.add(adminCrimeBeans14);
//        AdminCrimeBeans adminCrimeBeans15=new AdminCrimeBeans(10218,"fightin","jhafbadjfb bvjdf In ordinary language, a crime is an unlawful act punishable by a state ; The notion that acts such as murder","10-05-2022","invastingating","07-05-2022","09:45 am","Missing","aliabad","500002","Phanvi kumar","phanvikumar@gmail.com","9850716866");
//        list.add(adminCrimeBeans15);
//
//
//        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
//        adm_viewallcrimerepoartrecycle.setLayoutManager(linearLayoutManager);
//        adm_vac_adapter=new Adm_VAC_Adapter(this.getActivity(),list);
//        adm_viewallcrimerepoartrecycle.setAdapter(adm_vac_adapter);

        view_all_crime_repoarts();

        return view;
    }

    private void view_all_crime_repoarts() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, API.baseurl+"?accesskey=1234&crimereport_crimes_viewall=1", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
//                Toast.makeText(getActivity(), "response", Toast.LENGTH_SHORT).show();

                try {
                    JSONObject object1 = new JSONObject(response);
                    {
//                        Toast.makeText(getActivity(), "object1", Toast.LENGTH_SHORT).show();
                        if (object1.getString(Constant.RESULT).equals(Constant.SUCCESS)) {
                            JSONArray adminreportdata=object1.getJSONArray("data");

                            int i;
//                           list = new ArrayList<AdminCrimeBeans>();

                            for (i=0; i<adminreportdata.length(); i++){
                                JSONObject mydataobj1= (JSONObject) adminreportdata.get(i);
                                int id1=mydataobj1.getInt("id");
                                String title1=mydataobj1.getString("title");
                                String description1=mydataobj1.getString("description");
                                String cdate1=mydataobj1.getString("cdate");
                                String crimetype1=mydataobj1.getString("crimetype");
                                String area1=mydataobj1.getString("area");
                                String pincode1=mydataobj1.getString("pincode");
                                String ctime1=mydataobj1.getString("ctime");
                                String status=mydataobj1.getString("status");
                                String email=mydataobj1.getString("email");
                                String cmptdate=mydataobj1.getString("rdate");

//                                Toast.makeText(getActivity(),"image"+image,Toast.LENGTH_SHORT).show();

                                AdminCrimeBeans adminCrimeBeans= new AdminCrimeBeans();
                                adminCrimeBeans.setId(id1);
                                adminCrimeBeans.setTitle(title1);
                                adminCrimeBeans.setDescription(description1);
                                adminCrimeBeans.setCrimedate(cdate1);
                                adminCrimeBeans.setCategory(crimetype1);
                                adminCrimeBeans.setArea(area1);
                                adminCrimeBeans.setPincode(pincode1);
                                adminCrimeBeans.setTime(ctime1);
                                adminCrimeBeans.setComplentDate(cmptdate);
                                adminCrimeBeans.setComplentpn("Sai kumar");
                                adminCrimeBeans.setComplentpe(email);
                                adminCrimeBeans.setComplentpm("9850716866");
                                adminCrimeBeans.setStatus(status);
                                list.add(adminCrimeBeans);
                            }

                            LinearLayoutManager ll=new LinearLayoutManager(getActivity());
                            ll.setOrientation(RecyclerView.VERTICAL);
                            adm_viewallcrimerepoartrecycle.setLayoutManager(ll);
                            adm_vac_adapter=new Adm_VAC_Adapter(getActivity(),list);
//                            Log.e("image",list.toString());
                            adm_viewallcrimerepoartrecycle.setAdapter(adm_vac_adapter);


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
//            @Override
//            public Map<String, String> getParams() {
//
//                Map<String,String> params = new HashMap<String, String>();
//
//
//                return params;
//            }
        };
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);



    }



    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        filter(newText);
        return false;
    }

    private void filter(String newText) {
        ArrayList<AdminCrimeBeans> filterlist=new ArrayList<>();
        for (AdminCrimeBeans p:list)
        {
            if (p.getTitle().toLowerCase().contains(newText.toLowerCase())||p.getCategory().toLowerCase().contains(newText.toLowerCase())||p.getArea().toLowerCase().contains(newText.toLowerCase())||p.getPincode().toLowerCase().contains(newText.toLowerCase()))
            {
                filterlist.add(p);
            }
        }
        adm_vac_adapter.filtered((ArrayList<AdminCrimeBeans>)filterlist);
    }
}