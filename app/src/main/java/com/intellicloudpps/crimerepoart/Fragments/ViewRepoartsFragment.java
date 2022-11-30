package com.intellicloudpps.crimerepoart.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.intellicloudpps.crimerepoart.Adapters.Adm_VAC_Adapter;
import com.intellicloudpps.crimerepoart.Adapters.ViewAllCrimeAdapter;
import com.intellicloudpps.crimerepoart.Beans.AdminCrimeBeans;
import com.intellicloudpps.crimerepoart.Beans.ViewAllCrimeBeans;
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

public class ViewRepoartsFragment extends Fragment  implements SearchView.OnQueryTextListener {
    RecyclerView viewallcrimerepoartrecycle;
//    ArrayList<ViewAllCrimeBeans>viewAllCrimeBeans = new ArrayList<>();
    List<ViewAllCrimeBeans> list=new ArrayList<>();
    SearchView search;
    ViewAllCrimeAdapter viewAllCrimeAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_crime, container, false);
        viewallcrimerepoartrecycle=view.findViewById(R.id.viewallcrimerepoartrecycle);
        search=view.findViewById(R.id.search);


        search.setOnQueryTextListener(ViewRepoartsFragment.this);
        search.setIconifiedByDefault(false);
        search.setIconified(false);




//
//        ViewAllCrimeBeans viewAllCrimeBeans5= new ViewAllCrimeBeans(10215,"Missing","In ordinary language, a crime is an unlawful act punishable by a state ; The notion that acts such as murder ; The state (government)  Usually, to be classified as","10-05-2022","pending","08-05-2022","10:50 AM","Missing","kphb","500072");
//        viewAllCrimeBeans.add(viewAllCrimeBeans5);
//        ViewAllCrimeBeans viewAllCrimeBeans1= new ViewAllCrimeBeans(22548,"car thift","In ordinary language, a crime is an unlawful act punishable by a state ; The notion ","10-05-2022","processing","08-05-2022","10:50 AM","Missing","moosapet","500082");
//        viewAllCrimeBeans.add(viewAllCrimeBeans1);
//        ViewAllCrimeBeans viewAllCrimeBeans2= new ViewAllCrimeBeans(31475,"home problem","In ordinary language, a crime is an unlawful act punishable by a state ; The notion that acts such as murder ; The state (government)  Usually, to be classified as","10-05-2022","pending","08-05-2022","10:50 AM","Missing","nampally","500068");
//        viewAllCrimeBeans.add(viewAllCrimeBeans2);
//        ViewAllCrimeBeans viewAllCrimeBeans3= new ViewAllCrimeBeans(12458,"Missing bycle","In ordinary language, a crime is an unlawful act punishable by a state ; The notion that acts such as murder ","10-05-2022","completed","08-05-2022","10:50 AM","Missing","secundrabad","500053");
//        viewAllCrimeBeans.add(viewAllCrimeBeans3);
//
//
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
//        viewallcrimerepoartrecycle.setLayoutManager(linearLayoutManager);
//        viewAllCrimeAdapter=new ViewAllCrimeAdapter(this.getActivity(),viewAllCrimeBeans);
//        viewallcrimerepoartrecycle.setAdapter(viewAllCrimeAdapter);

        userviewcrimes();
        
        return view;
    }
    private void userviewcrimes() {
        list.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API.baseurl+"?accesskey=1234&crimereport_crimes_viewby_email=1&email="+SharedSession.getStr(getActivity(),Constant.EMAIL), new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
//                Toast.makeText(getActivity(), ""+API.baseurl+"?accesskey=1234&crimereport_crimes_viewall=1&email="+SharedSession.getStr(getActivity(),Constant.EMAIL), Toast.LENGTH_SHORT).show();
                try {
                    JSONObject object = new JSONObject(response);
                    {
                        if (object.getString(Constant.RESULT).equals(Constant.SUCCESS)) {
                            JSONArray crimedata=object.getJSONArray("data");
                            int i;
//                           list = new ArrayList<PlaygroundBean>();

                            for (i=0; i<crimedata.length(); i++){
                                JSONObject mydataobj1= (JSONObject) crimedata.get(i);
                                int id=mydataobj1.getInt("id");
                                String title=mydataobj1.getString("title");
                                String description=mydataobj1.getString("description");
                                String cdate=mydataobj1.getString("cdate");
                                String crimetype=mydataobj1.getString("crimetype");
                                String area=mydataobj1.getString("area");
                                String pincode=mydataobj1.getString("pincode");
                                String ctime=mydataobj1.getString("ctime");
                                String status1=mydataobj1.getString("status");
                                String cmpdate=mydataobj1.getString("rdate");
//                                Toast.makeText(getActivity(),"image"+image,Toast.LENGTH_SHORT).show();

                                ViewAllCrimeBeans viewAllCrimeBeans= new ViewAllCrimeBeans();
                                viewAllCrimeBeans.setId(id);
                                viewAllCrimeBeans.setTitle(title);
                                viewAllCrimeBeans.setComplentDate(cmpdate);
                                viewAllCrimeBeans.setStatus(status1);
                                viewAllCrimeBeans.setDescription(description);
                                viewAllCrimeBeans.setCrimedate(cdate);
                                viewAllCrimeBeans.setCategory(crimetype);
                                viewAllCrimeBeans.setArea(area);
                                viewAllCrimeBeans.setPincode(pincode);
                                viewAllCrimeBeans.setTime(ctime);
                                list.add(viewAllCrimeBeans);
                            }

                            LinearLayoutManager ll=new LinearLayoutManager(getActivity());
                            ll.setOrientation(RecyclerView.VERTICAL);
                            viewallcrimerepoartrecycle.setLayoutManager(ll);
                            viewAllCrimeAdapter=new ViewAllCrimeAdapter(getActivity(),list);
//                            Log.e("image",list.toString());
                            viewallcrimerepoartrecycle.setAdapter(viewAllCrimeAdapter);

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
//                params.put("crimereport_crimes_viewall","1");
//                params.put("accesskey",Constant.ACCESSKEY);
//                params.put("email", SharedSession.getStr(getActivity(),Constant.EMAIL));
//                return params;
//            }
        };
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);



    }

//    private void userviewcrimes() {
//    }

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
        ArrayList<ViewAllCrimeBeans> filterlist=new ArrayList<>();
        for (ViewAllCrimeBeans p:list)
        {
            if(p.getTitle().toLowerCase().contains(newText.toLowerCase())||p.getCategory().toLowerCase().contains(newText.toLowerCase())||p.getArea().toLowerCase().contains(newText.toLowerCase())||p.getPincode().toLowerCase().contains(newText.toLowerCase())){
                filterlist.add(p);
            }
        }
        viewAllCrimeAdapter.filtered((ArrayList<ViewAllCrimeBeans>) filterlist);
    }
}