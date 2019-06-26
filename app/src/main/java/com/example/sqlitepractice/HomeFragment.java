package com.example.sqlitepractice;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.fragmentexample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button btnSave, btnView, btnDelete, btnUpdate;
    OnDbOpListner onDbOpListner;

    public HomeFragment() {
        // Required empty public constructor
    }

    public interface OnDbOpListner {
        public void dbOpPerform(int method);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnSave = view.findViewById(R.id.btn_add_contact);
        btnSave.setOnClickListener(this);
        btnView = view.findViewById(R.id.btn_view_contact);
        btnView.setOnClickListener(this);

        btnUpdate = view.findViewById(R.id.btn_update_contact);
        btnUpdate.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_add_contact:
                onDbOpListner.dbOpPerform(0);
                break;
            case  R.id.btn_view_contact:
                onDbOpListner.dbOpPerform(1);
                break;
            case R.id.btn_update_contact:
                onDbOpListner.dbOpPerform(2);
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        try {
            onDbOpListner = (OnDbOpListner) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement the interface method..");
        }
    }
}
