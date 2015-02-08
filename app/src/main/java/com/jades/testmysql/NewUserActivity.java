package com.jades.testmysql;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
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
               // sendServerData();
                Intent j = new Intent(this, GetUsersActivity.class);
                startActivity(j);
                break;

        }

    }
/*

    private void sendServerData(String StrURL,String valuePhp, String nom) {
        private List<User> getServerData(String StrURL,String valuePhp, String nom) throws
        IOException {

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair(valuePhp, nom));

            // Envoie de la commande http
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(strURL);
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();

                String entityStr = EntityUtils.toString(entity, "UTF-8");


                JSONArray jArray = new JSONArray(entityStr);

                for (int i = 0; i < jArray.length(); i++) {
                    User userCurrent = new User();
                    JSONObject json_data = jArray.getJSONObject(i);


                    userCurrent.setId(json_data.getInt("id"));
                    userCurrent.setNom(json_data.getString("nom"));
                    userCurrent.setPrenom(json_data.getString("prenom"));
                    userCurrent.setStatut(json_data.getString("statut"));
                    userList.add(userCurrent);

                }
                return userList;
            } catch (IOException ioe) {
                throw ioe;
            } catch (Exception e) {
                throw new IOException("Service problem", e);
            }
*/
    }
