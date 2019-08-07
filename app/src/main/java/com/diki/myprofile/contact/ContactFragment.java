package com.diki.myprofile.contact;
// 17 MEI
// 10116352
// DIKI SUPRIADI
// IF-8

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.diki.myprofile.Model.Friend;
import com.diki.myprofile.R;

import java.util.List;

public class ContactFragment extends Fragment implements ContactContract.View {
    private ProgressBar progressBar;
    private ContactContract.Presenter mPresenter;
    private TextView telepon,email,instagram,twitter,facebook;
    private Button button1,button2,button3,button4,button5;


    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ContactPresenter(this);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_contact, container, false);
        telepon = view.findViewById(R.id.tv_contact_telepon);
        email = view.findViewById(R.id.tv_contact_email);
        instagram = view.findViewById(R.id.tv_contact_instagram);
        twitter = view.findViewById(R.id.tv_contact_twitter);
        facebook = view.findViewById(R.id.tv_contact_facebook);
        progressBar = view.findViewById(R.id.pb_contact);

        button1 = view.findViewById(R.id.telp);
        button2 = view.findViewById(R.id.email);
        button3 = view.findViewById(R.id.inst);
        button4 = view.findViewById(R.id.twitt);
        button5 = view.findViewById(R.id.fb);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:082316901623"));

                startActivity(callIntent);
            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Uri uri = Uri.parse("http://instagram.com/suara_hati69");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/suara_hati69")));
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","dikisupriadi021@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Uri uri = Uri.parse("http://m.twitter.com/suara_hati69");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.twitter.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://m.twitter.com/suara_hati69")));
                }
            }
        });


        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Uri uri = Uri.parse("http://m.facebook.com/diki.supriadi");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.facebook.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://m.facebook.com/diki.supriadi")));
                }
            }
        });



        return view;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public void showContact(List<Friend> friends) {
        telepon.setText(friends.get(0).getTelepon());
        email.setText(friends.get(0).getEmail());
        instagram.setText(friends.get(0).getInstagram());
        twitter.setText(friends.get(0).getTwitter());
        facebook.setText(friends.get(0).getFacebook());
    }

    @Override
    public void setPresenter(ContactContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }
}
