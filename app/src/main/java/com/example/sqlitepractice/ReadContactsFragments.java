package com.example.sqlitepractice;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.fragmentexample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadContactsFragments extends Fragment {

    private TextView Txt_display;

    public ReadContactsFragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read_contacts_fragments, container, false);
        Txt_display = view.findViewById(R.id.txt_display);
        readContactsFromDB();
        return view;
    }

    private void readContactsFromDB() {
        ContactDBHelper contactDBHelper = new ContactDBHelper(getActivity());
        SQLiteDatabase sqLiteDatabase = contactDBHelper.getReadableDatabase();
        Cursor cursor = contactDBHelper.readContacts(sqLiteDatabase);
        String info = "";
        while (cursor.moveToNext()) {
            String id = Integer.toString(cursor.getInt(cursor.getColumnIndex(ContactContract.ContactEntry.CONTACT_ID)));
            String name = cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.NAME));
            String email = cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.EMAIL));
            info = info + "\n\n" + "ID : " + id + "\nName : " + name + "\nEmail : " + email;

        }

        Txt_display.setText(info);
        contactDBHelper.close();
    }

}
