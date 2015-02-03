package com.jades.testmysql;

/**
 * Created by erwan on 28/01/2015.
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class getUsers extends Activity {
   // TextView txt;
    ListView listView;
    ListAdapter  listAdp;
    List<User> userList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
/*
        LinearLayout rootLayout = new LinearLayout(getApplicationContext());
        txt = new TextView(getApplicationContext());
        rootLayout.addView(txt);
        setContentView(rootLayout);

        // Définir le texte et appeler la fonction de connexion.
       txt.setText("Connexion...");
        // Appeler la méthode pour récupérer les données JSON
       txt.setText(getServerData(strURL));
       */
        getServerData();
    }

    // Mettre l'adresse du script PHP
    // Attention localhost ou 127.0.0.1 ne fonctionnent pas. Mettre l'adresse IP local.
    public static final String strURL = "http://jadixor.com/mySQLtoAndroid.php";

    private void getServerData(/*String returnString*/) {
        InputStream is = null;
        String result = "";
        // Envoyer la requête au script PHP.
        // Script PHP : $sql=mysql_query("select * from tblVille where Nom_ville like '".$_REQUEST['ville']."%'");
        // $_REQUEST['ville'] sera remplacé par L dans notre exemple.
        // Ce qui veut dire que la requête enverra les villes commençant par la lettre L
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("nom","Henry"));

        // Envoie de la commande http
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(strURL);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();

        }catch(Exception e){
            Log.e("log_tag", "Error in http connection " + e.toString());
        }

        // Convertion de la requête en string
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result=sb.toString();
          Log.d(result,"ok");
        }catch(Exception e){
            Log.e("log_tag", "Error converting result " + e.toString());
        }
        // Parse les données JSON
        // Parse les données JSON
        try{
            JSONArray jArray = new JSONArray(result);

            for(int i=0;i<jArray.length();i++){
                User userCurrent = new User();
                JSONObject json_data = jArray.getJSONObject(i);
                // Affichage ID_ville et Nom_ville dans le LogCat
                /*Log.i("log_tag","id: "+json_data.getInt("id")+
                                ", nom: "+json_data.getString("nom")
                );*/
                userCurrent.setId(json_data.getInt("id"));
                userCurrent.setNom(json_data.getString("nom"));
                userCurrent.setPrenom(json_data.getString("prenom"));
                userCurrent.setStatut(json_data.getString("statut"));
                userList.add(userCurrent);
                /*returnString += "\n\t" + jArray.getJSONObject(i);*/
            }
           printUsersList(userList);
            // Résultats de la requête

        }catch(JSONException e){
            Log.e("log_tag", "Error parsing data " + e.toString());
        }
        //return returnString;
    }


    public void printUsersList(List<User> userList){

        listView = (ListView) findViewById(R.id.listView1);
// get data from the table by the ListAdapter
        listAdp = new ListAdapter(this, R.layout.itemlistrow, userList);
        listView .setAdapter(listAdp);
    }


    public void onClick(View view) {
        User user = null;
        switch (view.getId()) {
            case R.id.button_add:
               Intent i = new Intent(this, NewUser.class);
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

        printUsersList(userList);
        //listAdp.notifyDataSetChanged();
    }

}
