package com.intellicloudpps.crimerepoart.Fragments;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.intellicloudpps.crimerepoart.Activitys.ChangePasswordActivity;
import com.intellicloudpps.crimerepoart.Activitys.ContactUsActivity;
import com.intellicloudpps.crimerepoart.Activitys.UpdateProfileActivity;
import com.intellicloudpps.crimerepoart.Healper.Constant;
import com.intellicloudpps.crimerepoart.Healper.SharedSession;
import com.intellicloudpps.crimerepoart.MainActivity;
import com.intellicloudpps.crimerepoart.Network.API;
import com.intellicloudpps.crimerepoart.Network.VolleySingleton;
import com.intellicloudpps.crimerepoart.R;
import com.squareup.picasso.BuildConfig;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AccountFragment extends Fragment {
    TextView myaccountfragmentemail,myaccountfragmentname;
    ProgressBar pbLoading;
    ImageView takepic, chooseprofilepic;
    CardView profileimgcard, updateprofilecard, changepasswordcard, contactuscard, aboutuscard, termandconditionalcard, privacypolicycard, sharecard, faqscard, logoutcard, viewbokingcard;
    public static final String UPLOAD_KEY = "image";
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap;
    private Uri filePath;
    String image;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=  inflater.inflate(R.layout.fragment_account, container, false);

        myaccountfragmentname=view.findViewById(R.id.myaccountfragmentname);
        myaccountfragmentemail=view.findViewById(R.id.myaccountfragmentemail);
        profileimgcard = view.findViewById(R.id.profileimgcard);
        updateprofilecard = view.findViewById(R.id.updateprofilecard);
        changepasswordcard = view.findViewById(R.id.changepasswordcard);
        contactuscard = view.findViewById(R.id.contactuscard);

        sharecard = view.findViewById(R.id.sharecard);
        logoutcard = view.findViewById(R.id.logoutcard);
        chooseprofilepic = view.findViewById(R.id.chooseprofilepic);
        takepic=view.findViewById(R.id.takepic);
        pbLoading=view.findViewById(R.id.pbLoading);



        image= SharedSession.getStr(getActivity(),Constant.IMAGE);
        Picasso.get().load(Constant.IMAGE).into(chooseprofilepic);

        myaccountfragmentname.setText(SharedSession.getStr(getActivity(),Constant.FULLNAME));
        myaccountfragmentemail.setText(SharedSession.getStr(getActivity(),Constant.EMAIL));


        logoutcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(getActivity());
                builder.setMessage(Constant.DOYOUWONTTOLOGOUTTHISAPPLICATION).setIcon(R.drawable.circlesmall)
                        .setCancelable(false)
                        .setPositiveButton(Constant.YES, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedSession.deleteData(getActivity(), Constant.EMAIL);
                                Intent o = new Intent(getActivity(), MainActivity.class);
                                o.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                o.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                o.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                 startActivity(o);
                                 ///
                            }
                        })
                        .setNegativeButton(Constant.NO, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        });
                android.app.AlertDialog alert=builder.create();
                alert.setTitle(Constant.CRIMEREPOART);
                alert.show();

            }
        });

        sharecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   case R.id.sharecard:
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "News App");
                    String shareMessage = "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
                //  break;
            }
        });



        contactuscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(getActivity(), ContactUsActivity.class);
                startActivity(n);

            }
        });



        changepasswordcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent K = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(K);
            }
        });


        updateprofilecard.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent j = new Intent(getActivity(), UpdateProfileActivity.class);
                startActivity(j);
            }
        });



        takepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    if ((ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE))) {

                    } else {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 02);
                        selectImage();
                    }
                } else {
                    selectImage();
                }

            }
        });


        return view;
    }
    private void selectImage() {

        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = true;
                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        showFileChooser();
                } else if (items[item].equals("Cancel")) {
                    userChoosenTask = "Cancel";
                    if (result)
                        dialog.dismiss();
                }

            }

        });
        builder.show();


    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
//        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
//            startActivityForResult(intent, REQUEST_CAMERA);
//        }

    }

    //
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                chooseprofilepic.setImageBitmap(bitmap);
//                Toast.makeText(getActivity(),"welcome to CH1",Toast.LENGTH_SHORT).show();

                uploadBitmap();

//                Toast.makeText(getActivity(),"welcome to CH2",Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (resultCode == RESULT_OK && requestCode == REQUEST_CAMERA) {
//onCaptureImageResult(data);
            bitmap = (Bitmap) data.getExtras().get("data");

//storeImage(bm);
            chooseprofilepic.setImageBitmap(bitmap);
            uploadBitmap();
        }
    }
    private void uploadBitmap() {
        pbLoading.setVisibility(ProgressBar.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, API.baseurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(getActivity(),"welcome to uploadbitmap",Toast.LENGTH_SHORT).show();

                try {
                    JSONObject object = new JSONObject(response);
                    {


                        if (object.getString("result").equals("success")) {
                            Toast.makeText(getActivity(), "Successfully Updated", Toast.LENGTH_SHORT).show();
                            String imageupdated = object.getString("image");
                            SharedSession.insertData(getActivity(),Constant.IMAGE,imageupdated);
                            pbLoading.setVisibility(ProgressBar.INVISIBLE);

                        } else {
                            Toast.makeText(getActivity(), " Not Successfully Updated ", Toast.LENGTH_LONG).show();

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

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                params.put("update_image","1");
                params.put("accesskey","1234");
                params.put("email", SharedSession.getStr(getActivity(),Constant.EMAIL));
                params.put("image", imageString);
                return params;
            }
        };
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);


    }

}