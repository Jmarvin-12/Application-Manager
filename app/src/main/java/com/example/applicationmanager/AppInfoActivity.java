package com.example.applicationmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AppInfoActivity extends AppCompatActivity {


    private TextView textViewAppName;
    private TextView textViewApkSize;
    private TextView textViewApkData;
    private TextView textViewTotal;

    protected Double apkSize;
    protected Double apkData;
    protected Double total;

    private Apps apps;
    private int id;
    AppAdapter adapter;
    private boolean state;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_info_details);

        Bundle extras= getIntent().getExtras();
        id= extras.getInt("id", -1);
        apps= MainActivity.appI.getAppById((int) id);
        state= apps.isUnable();
        textViewAppName= (TextView) findViewById(R.id.textView_appName);
        if (apps.isUnable()) {
            if(apps.isSistem()){
                textViewAppName.setText(apps.getAppName().getAppName()+ " (Sistema)");
                textViewAppName.setTypeface(null, Typeface.BOLD);
                textViewAppName.setTextColor(Color.RED);
            }else {
                textViewAppName.setText(apps.getAppName().getAppName());
                textViewAppName.setTypeface(null, Typeface.BOLD);
            }
        }else {
            textViewAppName.setText(apps.getAppName().getAppName() + " (Desactivado)");
            textViewAppName.setTypeface(null, Typeface.BOLD);
            textViewAppName.setTextColor(Color.parseColor("#00aae4"));
        }
        textViewApkSize= (TextView) findViewById(R.id.textView_apkSize);
        textViewApkSize.setText("APK: " +Double.toString(apps.getApkSize()));
        apkSize = apps.getApkSize();
        textViewApkData= (TextView) findViewById(R.id.textView_apkData);
        apkData = apps.getDataSize();
        textViewTotal= (TextView) findViewById(R.id.textView_total);
        if (apps.isUnable()){
            textViewApkData.setText("Datos: "+Double.toString(apps.getDataSize()));
            total= apkSize + apkData;
        }else {
            apps.setDataSize(0.0);
            apkData= apps.getDataSize();
            total= apkSize+apkData;
            textViewApkData.setText("Datos: "+ Double.toString(apkData));
            textViewTotal.setText("Total: "+Double.toString(total));
        }
        textViewTotal.setText("Total: "+Double.toString(total));
        Button desactivar = (Button) findViewById(R.id.button1);
        Button desinstalar = (Button) findViewById(R.id.button2);

        if (apps.isUnable()){
            desactivar.setText("Desactivar");
        }else {
            desactivar.setText("Activar");
        }

        if(apps.isSistem()){
            desinstalar.setTextColor(Color.GRAY);
        }

        desactivar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (apps.isUnable()) {
                    if (apps.isSistem()){
                        new AlertDialog.Builder(v.getContext())
                                .setTitle("Confirmar operación")
                                .setMessage("Esta es una aplicación del sistema. \n ¿Está seguro que la desea desactivar?")
                                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        apps.setUnable(false);
                                        Toast.makeText(v.getContext(), apps.getAppName().getAppName() + " ha sido desactivado.", Toast.LENGTH_SHORT).show();
                                        apps.setDataSize(0.0);
                                        apkData = apps.getDataSize();
                                        total = apkSize + apkData;
                                        textViewApkData.setText("Datos: " + Double.toString(apkData));
                                        textViewTotal.setText("Total: " + Double.toString(total));
                                        desactivar.setText("Activar");
                                        textViewAppName.setText(apps.getAppName().getAppName() + " (Desactivado)");
                                        textViewAppName.setTypeface(null, Typeface.BOLD);
                                        textViewAppName.setTextColor(Color.parseColor("#00aae4"));

                                        state= apps.isUnable();
                                        Intent i= new Intent();
                                        i.putExtra("state", state);
                                        setResult(RESULT_OK, i);
                                    }
                                }).setNegativeButton("Cancelar", null).show();
                    }else {
                        apps.setUnable(false);
                        Toast.makeText(v.getContext(), apps.getAppName().getAppName() + " ha sido desactivado.", Toast.LENGTH_SHORT).show();
                        apps.setDataSize(0.0);
                        apkData = apps.getDataSize();
                        total = apkSize + apkData;
                        textViewApkData.setText("Datos: " + Double.toString(apkData));
                        textViewTotal.setText("Total: " + Double.toString(total));
                        desactivar.setText("Activar");
                        textViewAppName.setText(apps.getAppName().getAppName() + " (Desactivado)");
                        textViewAppName.setTypeface(null, Typeface.BOLD);
                        textViewAppName.setTextColor(Color.parseColor("#00aae4"));

                        state= apps.isUnable();
                        Intent i= new Intent();
                        i.putExtra("state", state);
                        setResult(RESULT_OK, i);
                    }
                }else {
                    if(apps.isSistem()){
                        textViewAppName.setText(apps.getAppName().getAppName()+ " (Sistema)");
                        textViewAppName.setTypeface(null, Typeface.BOLD);
                        textViewAppName.setTextColor(Color.RED);

                    }else {
                        textViewAppName.setText(apps.getAppName().getAppName());
                        textViewAppName.setTypeface(null, Typeface.BOLD);
                        textViewAppName.setTextColor(Color.BLACK);
                    }
                    apps.setUnable(true);
                    Toast.makeText(v.getContext(), apps.getAppName().getAppName()+" ha sido activado.", Toast.LENGTH_SHORT).show();
                    desactivar.setText("desactivar");

                    state= apps.isUnable();
                    Intent i= new Intent();
                    i.putExtra("state", state);
                    setResult(RESULT_OK, i);
                }
                }

            void updateData() {
                adapter.notifyDataSetChanged();
            }
        });

        desinstalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (apps.isSistem()) {
                        desinstalar.setActivated(false);
                } else {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Confirmar operación")
                            .setMessage("La aplicación se desinstalará. \n ¿Está seguro que desea continuar?")
                            .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.appI.deleteAppById(id);
                                    Intent i = new Intent();
                                    i.putExtra("result", "La aplicación se ha desinstalado con éxito.");
                                    setResult(1,i);
                                    finish();
                                }
                            }).setNegativeButton("Cancelar", null).show();
                }
            }
        });

    }

}
