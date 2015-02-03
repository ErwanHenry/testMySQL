package com.jades.testmysql;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erwan on 03/02/2015.
 */
public class NewUser extends Activity {



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);



    }

    public void onClick(View view) {
        User user = new User();
        EditText newUserFirstName = (EditText) findViewById(R.id.newUserFirstName);
        EditText newUserLastName = (EditText) findViewById(R.id.newUserLastName);
        EditText newUserPassword = (EditText) findViewById(R.id.newUserPassword);
        EditText newUserEmail = (EditText) findViewById(R.id.newUserEmail);
        switch (view.getId()) {
            case R.id.button_finish:
                user.setPrenom(newUserFirstName.getText().toString());
                Log.d(user.getPrenom(), "ok");
                user.setNom(newUserLastName.getText().toString());
                Log.d(user.getNom(), "ok");
                Intent j = new Intent(this, getUsers.class);
                startActivity(j);
                break;

        }

    }

}