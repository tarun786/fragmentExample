package com.example.sqlitepractice;


import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.fragmentexample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateContactFragment extends Fragment {

    private EditText Update_Id, Update_Name, Update_Email;
    private Button Update_btn;

    public UpdateContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_contact, container, false);
        Update_Id = view.findViewById(R.id.txt_update_id);
        Update_Name = view.findViewById(R.id.txt_update_name);
        Update_Email = view.findViewById(R.id.txt_update_email);
        Update_btn = view.findViewById(R.id.update_contact);

        Update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateContact();
            }
        });
        return view;
    }

    public void updateContact() {
        try {
            int id = Integer.parseInt(Update_Id.getText().toString());
            String name = Update_Name.getText().toString();
            String email = Update_Email.getText().toString();
            AddContactFrag addContactFrag = new AddContactFrag();
            if (addContactFrag.validateUserInputs(id, name, email)) {
                ContactDBHelper contactDBHelper = new ContactDBHelper(getActivity());
                SQLiteDatabase sqLiteDatabase = contactDBHelper.getWritableDatabase();
                String idInstr = Update_Id.getText().toString();
                if (CheckContactIdIsExistOrNot(ContactContract.ContactEntry.TABLE_NAME, ContactContract.ContactEntry.CONTACT_ID, idInstr, sqLiteDatabase)) {
                    contactDBHelper.updateContact(id, name, email, sqLiteDatabase);
                    contactDBHelper.close();

                    Toast.makeText(getActivity(), "Updated contact", Toast.LENGTH_SHORT).show();
                    Update_Id.setText("");
                    Update_Name.setText("");
                    Update_Email.setText("");
                } else {
                    Toast.makeText(getActivity(), "Contact Id is not exist", Toast.LENGTH_SHORT).show();
                    Update_Id.setText("");
                    Update_Name.setText("");
                    Update_Email.setText("");
                }
            } else {
                Update_Id.setText("");
                Update_Name.setText("");
                Update_Email.setText("");
                Toast.makeText(getActivity(), "Update contact details are not correct..Please try again", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.d("Incorrect inputs", "Failed to update contact details");
            Update_Id.setText("");
            Update_Name.setText("");
            Update_Email.setText("");
            Toast.makeText(getActivity(), "Update contact details are not correct..Please try again", Toast.LENGTH_SHORT).show();
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
