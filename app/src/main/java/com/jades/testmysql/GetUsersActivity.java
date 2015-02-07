package com.jades.testmysql;

/**
 * Created by erwan on 28/01/2015.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import android.app.Activity;
import android.os.AsyncTask;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class GetUsersActivity extends Activity {
    TextView txt;
    ListView listView;
    ListAdapter listAdp;
    List<User> userList = new ArrayList<>();
    public static final String strURL = "http://www.jadixor.com/mySQLtoAndroid.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GetServerDataTask getServerDataTask = new GetServerDataTask();
        getServerDataTask.execute("nom", "Henry");

    }


    // Mettre l'adresse du script PHP
    // Attention localhost ou 127.0.0.1 ne fonctionnent pas. Mettre l'adresse IP local.


    protected void getServerData(String categorie, String value) throws IOException, JSONException {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        Log.d("ok", "ok");
        nameValuePairs.add(new BasicNameValuePair(categorie, value));
        Log.d("ko", "ko");
        // Envoie de la commande http
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(strURL);

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            Log.d("koi", "koi");
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
                Log.d("ok", json_data.toString());
            }
            printUsersList(userList);

        } catch (IOException ioe) {
            throw ioe;
        } catch(Exception e) {
            throw new IOException("Service problem",e);
        }
}


    public String printUsersList(List<User> userList) {

        listView = (ListView) findViewById(R.id.listView1);
// get data from the table by the ListAdapter
        listAdp = new ListAdapter(this, R.layout.itemlistrow, userList);
        listView.setAdapter(listAdp);
        return null;
    }

    /*
        public void onClick(View view) {
            User user = null;
            switch (view.getId()) {
                case R.id.button_add:
                   Intent i = new Intent(this, NewUserActivity.class);
                    startActivity(i);
                    break;
                case R.id.button_remove:
                    if (listAdp.getCount() > 0) {
                        user = (User) listAdp.getItem(0);
                        //usersBdd.removeUserWithID(user.getId());
                        //listAdp.remove(comment);
                    }
                    break;
            }
            List<User> userList = new ArrayList<>();
            printUsersList(userList);
        }


    */
    class GetServerDataTask extends AsyncTask<String, String, Void> {

        public Void doInBackground(String... params) {
            try {
                getServerData(params[0], params[1]);
            } catch (IOException ex) {
                Log.e("GetServerDataTask", "Failed !", ex);
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
            return null;
        }

        /*
        @Override
        public void onPostExecute(List<User> retUsers) {



        }*/

    }
}
