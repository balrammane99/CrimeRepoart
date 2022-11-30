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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.intellicloudpps.crimerepoart.Activitys.AdminChangePasswordActivity;
import com.intellicloudpps.crimerepoart.Activitys.AdminContactUsActivity;
import com.intellicloudpps.crimerepoart.Activitys.AdminUpdateProfileActivity;
import com.intellicloudpps.crimerepoart.Activitys.ChangePasswordActivity;
import com.intellicloudpps.crimerepoart.Activitys.ContactUsActivity;
import com.intellicloudpps.crimerepoart.Activitys.UpdateProfileActivity;
import com.intellicloudpps.crimerepoart.Healper.Constant;
import com.intellicloudpps.crimerepoart.Healper.SharedSession;
import com.intellicloudpps.crimerepoart.MainActivity;
import com.intellicloudpps.crimerepoart.R;
import com.squareup.picasso.BuildConfig;
import com.squareup.picasso.Picasso;

import java.io.IOException;


public class AdminMyAccountFragment extends Fragment {

    TextView admmyaccountfragmentemail,admmyaccountfragmentname;
    ProgressBar admpbLoading;
    ImageView admtakepic, admchooseprofilepic;
    CardView admprofileimgcard, admupdateprofilecard, admchangepasswordcard, admcontactuscard, admaboutuscard, admtermandconditionalcard, admprivacypolicycard, admsharecard, admfaqscard, admlogoutcard, admviewbokingcard;
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
        View view= inflater.inflate(R.layout.fragment_admin_my_account, container, false);

        admmyaccountfragmentname=view.findViewById(R.id.admmyaccountfragmentname);
        admmyaccountfragmentemail=view.findViewById(R.id.admmyaccountfragmentemail);
        admprofileimgcard = view.findViewById(R.id.admprofileimgcard);
        admupdateprofilecard = view.findViewById(R.id.admupdateprofilecard);
        admchangepasswordcard = view.findViewById(R.id.admchangepasswordcard);
        admcontactuscard = view.findViewById(R.id.admcontactuscard);

        admsharecard = view.findViewById(R.id. admsharecard);
        admlogoutcard = view.findViewById(R.id. admlogoutcard);
        admchooseprofilepic = view.findViewById(R.id. admchooseprofilepic);
        admtakepic=view.findViewById(R.id. admtakepic);
        admpbLoading=view.findViewById(R.id. admpbLoading);



        image= SharedSession.getStr(getActivity(), Constant.IMAGE);
        Picasso.get().load(Constant.IMAGE).into( admchooseprofilepic);

        admlogoutcard.setOnClickListener(new View.OnClickListener() {
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

        admsharecard.setOnClickListener(new View.OnClickListener() {
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

        admcontactuscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(getActivity(), AdminContactUsActivity.class);
                startActivity(n);

            }
        });

        admchangepasswordcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent K = new Intent(getActivity(), AdminChangePasswordActivity.class);
                startActivity(K);
            }
        });

        admupdateprofilecard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent j = new Intent(getActivity(), AdminUpdateProfileActivity.class);
                startActivity(j);
            }
        });

        admtakepic.setOnClickListener(new View.OnClickListener() {
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
                admchooseprofilepic.setImageBitmap(bitmap);
//                Toast.makeText(getActivity(),"welcome to CH1",Toast.LENGTH_SHORT).show();
//                uploadBitmap();
//                Toast.makeText(getActivity(),"welcome to CH2",Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (resultCode == RESULT_OK && requestCode == REQUEST_CAMERA) {
//onCaptureImageResult(data);
            bitmap = (Bitmap) data.getExtras().get("data");

//storeImage(bm);
            admchooseprofilepic.setImageBitmap(bitmap);
//            uploadBitmap();
        }
    }
}