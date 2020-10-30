package com.example.parse_tagram.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.parse_tagram.LoginActivity;
import com.example.parse_tagram.R;

public class LogoutFragment extends Fragment
{
    public Button btnlogout;

    public LogoutFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        btnlogout = view.findViewById(R.id.btnlogout);

        btnlogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra("login", "login info");
                startActivity(intent);

                Toast.makeText(getContext(), "Back to login!!!", Toast.LENGTH_SHORT).show();

            }
        });


    }
}