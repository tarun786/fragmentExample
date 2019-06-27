package com.example.sqlitepractice;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.common.utils.Validator;
import com.example.fragmentexample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddContactFrag extends Fragment {

    private Button btnSave;
    private EditText Id, Name, Email;

    public AddContactFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_contact_fragment, container, false);
        btnSave = view.findViewById(R.id.btn_save);
        Id = view.findViewById(R.id.txt_contact_id);
        Name = view.findViewById(R.id.txt_name);
        Email = view.findViewById(R.id.txt_email);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String id = Id.getText().toString();
                    String name = Name.getText().toString();
                    String email = Email.getText().toString();
                    int idinInt = Integer.parseInt(id);
                    if (validateUserInputs(idinInt, name, email)) {
                        ContactDBHelper contactDBHelper = new ContactDBHelper(getActivity());
                        //here we are putting this database task in main thread that is not good for user interface always put db task in background thread
                        SQLiteDatabase sqLiteDatabase = contactDBHelper.getWritableDatabase();
                        contactDBHelper.addContact(Integer.parseInt(id), name, email, sqLiteDatabase);
                        contactDBHelper.close();
                        Id.setText("");
                        Name.setText("");
                        Email.setText("");
                        Toast.makeText(getActivity(), "Contact Saved Successfully..", Toast.LENGTH_SHORT).show();
                    } else {
                        Id.setText("");
                        Name.setText("");
                        Email.setText("");
                        Toast.makeText(getActivity(), "Add contact details are not correct..Please try again", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Id.setText("");
                    Name.setText("");
                    Email.setText("");
                    Toast.makeText(getActivity(), "Add contact details are not correct..Please try again", Toast.LENGTH_SHORT).show();

                }
            }


        });
        return view;
    }

    private boolean validateUserInputs(int id, String name, String email) {
        boolean status = false;
        try {
            if ((id > 0) && Validator.isNumeric(String.valueOf(id)) && Validator.verifyName(name) && Validator.isValidEmail(email)) {
                status = true;
            }
        } catch (Exception e) {
            Log.d("Validate Users ", "add contact details is not valid..");
            status = false;
        }
        return status;
    }

}
