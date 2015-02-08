package com.jades.testmysql;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by erwan on 03/02/2015.
 */
public class NewUserActivity extends Activity {



    public static final String strURL = "http://jadixor.com/AndroidtoMySQL.php";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);


    }



    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_finish:

                SetServerDataTask setServerDataTask = new SetServerDataTask();
                setServerDataTask.execute();
                Intent j = new Intent(this, GetUsersActivity.class);
                startActivity(j);
                break;

        }

    }


    protected void sendServerData() {

        User user = new User();


        // Envoie de la commande http
        try {
            EditText newUserFirstName = (EditText)findViewById(R.id.newUserFirstName);
            EditText newUserLastName = (EditText)findViewById(R.id.newUserLastName);

            EditText newUserStatut = (EditText)findViewById(R.id.newUserStatut);


            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(strURL);


            ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();


            nameValuePairs.add(new BasicNameValuePair("nom", newUserLastName.getText().toString()));

            nameValuePairs.add(new BasicNameValuePair("Prenom", newUserFirstName.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("Statut", newUserStatut.getText().toString()));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }

    class SetServerDataTask extends AsyncTask<String, String, Void> {

        public Void doInBackground(String... params) {

            sendServerData();

            return null;
        }
    }
}