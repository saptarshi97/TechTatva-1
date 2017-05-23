package saptarshi.com.techtatva_1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private List<Details> data;
    private final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initViews();

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
               boolean loaded = sharedPref.getBoolean("Loaded", false);

                        if (loaded) {
                        copyData();
                        displayData();
                    } else {
                        loadJSON();

                            }
            }
    private void copyData() {
                Realm database = Realm.getDefaultInstance();
                RealmResults<Details> results = database.where(Details.class).findAll();
                data = database.copyFromRealm(results);
                database.close();
            }

                private void displayData() {
                    recyclerView = (RecyclerView) findViewById(R.id.users_recycler_view);
                    recyclerView.setAdapter(new DataAdapter(data,activity));
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);

                    }




    private void loadJSON() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface service = retrofit.create(RequestInterface.class);
        Call<List<Details>> call = service.getDetails();
        call.enqueue(new Callback<List<Details>>() {
            @Override
            public void onResponse(Call<List<Details>> call, Response<List<Details>> response) {
                int statusCode = response.code();
                 data = response.body();
                displayData();

                final Realm database = Realm.getDefaultInstance();
                 database.executeTransactionAsync(new Realm.Transaction() {
                     @Override
                     public void execute(Realm realm) {
                         realm.delete(Details.class);
                         realm.copyToRealm(data);

                     }
                 }, new Realm.Transaction.OnSuccess() {
                     @Override
                    public void onSuccess() {
                         Realm database = Realm.getDefaultInstance();
                 RealmResults<Details> results = database.where(Details.class).findAll();
                  data = database.copyFromRealm(results);
                         SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                         SharedPreferences.Editor editor = sharedPref.edit();
                         editor.putBoolean("Loaded", true);
                         editor.apply();
                         database.close();

                     }
                 }, new Realm.Transaction.OnError() {
                     @Override
                     public void onError(Throwable error) {

                     }
                 });

             }

            @Override
            public void onFailure(Call<List<Details>> call, Throwable t) {
                Log.d("Error", t.getMessage());
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, "Not Connected !", duration);
                toast.show();

            }

        });
    }
}
