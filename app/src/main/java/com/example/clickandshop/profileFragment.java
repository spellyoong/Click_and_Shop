package com.example.clickandshop;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profileFragment extends Fragment {

    // Initiate variables
    private View profilePic;
    private View profileInfo;
    private View profileOrderContainer;
    private View profileDetailContainer;
    private View profileLocationContainer;
    private View profileVoucherContainer;
    private View profileChatContainer;
    private View profileHelpContainer;
    private View profileAboutContainer;
    private Toast prototypeToast;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public profileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static profileFragment newInstance(String param1, String param2) {
        profileFragment fragment = new profileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        // Navigate to different activities from fragment_profile
        profilePic = v.findViewById(R.id.profilePic);
        profileInfo = v.findViewById(R.id.profileInfo);
        profileOrderContainer = v.findViewById(R.id.profileOrderContainer);
        profileDetailContainer = v.findViewById(R.id.profileDetailContainer);
        profileLocationContainer = v.findViewById(R.id.profileLocationContainer);
        profileVoucherContainer = v.findViewById(R.id.profileVoucherContainer);
        profileChatContainer = v.findViewById(R.id.profileChatContainer);
        profileHelpContainer = v.findViewById(R.id.profileHelpContainer);
        profileAboutContainer = v.findViewById(R.id.profileAboutContainer);

        profilePic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                displayPrototypeMessage();
            }

        });

        profileInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }

        });

        profileOrderContainer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                startActivity(intent);
            }

        });

        profileDetailContainer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }

        });

        profileLocationContainer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddressActivity1.class);
                startActivity(intent);
            }

        });

        profileVoucherContainer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), VoucherActivity.class);
                startActivity(intent);
            }

        });

        profileChatContainer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChatActivity1.class);
                startActivity(intent);
            }

        });

        profileHelpContainer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
            }

        });

        profileAboutContainer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
            }

        });

        return v;
    }

    // Prototype Message
    private void displayPrototypeMessage(){
        if (prototypeToast != null)
            prototypeToast.cancel();
        prototypeToast = Toast.makeText(getActivity(), "Function not implemented in current prototype version", Toast.LENGTH_SHORT);
        prototypeToast.show();
    }
}