package com.bhim.waleapod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bhim.waleapod.databinding.ActivityMainBinding;
import com.bumptech.glide.util.Util;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ModelAPOD modelAPOD;
    private static final String TAG = MainActivity.class.getSimpleName();
    ActivityMainBinding binding;
    private static String stored = null;
    private SharedPreferences sharedPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initViews(view);
    }

    private void initViews(View view) {
        if (Utils.isNetworkAvailable(this))
            getAPODImage();
        else if (!getResponseString().isEmpty()) {
            Gson gson = new GsonBuilder().create();
            modelAPOD = gson.fromJson(getResponseString(), ModelAPOD.class);
            if (Utils.isDateSame(Utils.getTodaysDate(), modelAPOD.getDate()))
                setImageData(modelAPOD);
            else {
                Snackbar.make(view, getResources().getString(R.string.showing_prev_image), BaseTransientBottomBar.LENGTH_LONG).show();
                setImageData(modelAPOD);
            }
        } else {
            Snackbar.make(view, getResources().getString(R.string.pls_chk_internet_connection), BaseTransientBottomBar.LENGTH_LONG).show();
        }
    }

    private void getAPODImage() {
        String apiKey = "ODOvI9C1SfPX8AbmEISecMMKWwVZVuL0VABfUn2q";
        String url = "https://api.nasa.gov/planetary/apod?api_key=" + apiKey;
        Log.d(TAG, url);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new GsonBuilder().create();
                modelAPOD = gson.fromJson(response.toString(), ModelAPOD.class);
                saveString(response.toString());
                Log.d(TAG, response.toString());
                setImageData(modelAPOD);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d(TAG, error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void setImageData(ModelAPOD modelAPOD) {
        if (modelAPOD != null) {
            binding.tvTitle.setText(modelAPOD.getTitle() != null ? modelAPOD.getTitle() : "");
            binding.tvDescription.setText(modelAPOD.getExplanation() != null ? modelAPOD.getExplanation() : "");
            Utils.setImage(this, modelAPOD.getUrl(), binding.ivAPOD);
        }
    }

    private SharedPreferences getSharedPrefInstance() {
        if (sharedPreferences == null)
            return getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        else return sharedPreferences;
    }

    private void saveString(String value) {
        SharedPreferences sharedPreferences = getSharedPrefInstance();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Key1", value);
        editor.apply();
    }

    private String getResponseString() {
        //SharedPreferences sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return getSharedPrefInstance().getString("Key1", "");
    }
}