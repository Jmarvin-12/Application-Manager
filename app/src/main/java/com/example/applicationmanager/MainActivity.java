package com.example.applicationmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public AppAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public static IApps appI= new AppsVector();
    public static boolean state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= (RecyclerView) findViewById(R.id.my_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter= new AppAdapter(this, appI);

        adapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                Intent intent= new Intent(MainActivity.this, AppInfoActivity.class);
                intent.putExtra("id", pos);
                startActivityForResult(intent, 1234);
            }
        });

        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onActivityResult(int resquestCode, int resultCode, Intent data){

        if(resquestCode==1234 && resultCode==RESULT_OK){
            boolean s= data.getExtras().getBoolean("state");
            appI.status(s);
            adapter.notifyDataSetChanged();
        }else if(resquestCode==1234 && resultCode==1){
            String result=  data.getExtras().getString("result");
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        }
    }
}