package com.example.sqlitepractice;


import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fragmentexample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteContactFragment extends Fragment {

    private EditText contactID;
    private Button Delete_contact;

    public DeleteContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_contact, container, false);
        contactID = view.findViewById(R.id.delete_cont_id_label);
        Delete_contact = view.findViewById(R.id.delete_cont_btn);

        Delete_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteContact();
            }
        });
        return view;
    }

    public void deleteContact() {
        try {
            int id = Integer.parseInt(contactID.getText().toString());
            String idinStr = contactID.getText().toString();
            ContactDBHelper contactDBHelper = new ContactDBHelper(getActivity());
            SQLiteDatabase sqLiteDatabase = contactDBHelper.getWritableDatabase();
            if (CheckContactIdIsExistOrNot(ContactContract.ContactEntry.TABLE_NAME, ContactContract.ContactEntry.CONTACT_ID, idinStr, sqLiteDatabase)) {
                contactDBHelper.deleteContact(id, sqLiteDatabase);
                contactDBHelper.close();
                Toast.makeText(getActivity(), "Deleted Contact ID", Toast.LENGTH_SHORT).show();
                contactID.setText("");
            } else {
                Toast.makeText(getActivity(), "This contact id is not exist..", Toast.LENGTH_SHORT).show();
                contactID.setText("");
            }

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Incorrect id , Please try again", Toast.LENGTH_SHORT).show();
            contactID.setText("");
        }
    }

    private boolean CheckContactIdIsExistOrNot(String TableName,
                                               String contactIdColumn, String contactID, SQLiteDatabase sqLiteDatabase) {
        ContactDBHelper contactDBHelper = new ContactDBHelper(getActivity());
        contactDBHelper.getReadableDatabase();
        try {
            String Query = "Select * from " + TableName + " where " + contactIdColumn + " = " + contactID;
            Cursor cursor = sqLiteDatabase.rawQuery(Query, null);
            if (cursor.getCount() <= 0) {
                cursor.close();
                return false;
            }
            cursor.close();
        } catch (SQLException e) {
            Log.d("Database Info", "Database is not exist");
            Toast.makeText(getActivity(), "Database is not exist", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}
