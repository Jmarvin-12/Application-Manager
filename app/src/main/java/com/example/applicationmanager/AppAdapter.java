package com.example.applicationmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {

    protected IApps apps;
    protected LayoutInflater inflater;
    protected Context context;

    protected View.OnClickListener onClickListener;

    public AppAdapter(Context context, IApps apps){
        this.apps= apps;
        this.context= context;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView icon;
        private TextView appName, packageName;
        private Button uninstall, check;

        public ViewHolder(View v){
            super(v);

            icon= (ImageView) v.findViewById(R.id.app_icon);
            appName = (TextView) v.findViewById(R.id.app_name);
            packageName= (TextView) v.findViewById(R.id.app_package_name);
            uninstall= (Button) v.findViewById(R.id.uninstall_icon);
            check= (Button) v.findViewById(R.id.check_inactive);


        }

    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    @Override
    public AppAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int item){
        View v= inflater.inflate(R.layout.recycler_view_elements, parent, false);
        v.setOnClickListener(onClickListener);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Apps app= apps.getAppById(position);
        int id= holder.getAdapterPosition();

        if (app.isSistem()){
            holder.uninstall.setBackgroundResource(R.drawable.close2);
        }else {
            holder.uninstall.setBackgroundResource(R.drawable.twotone_close_24);
        }
        holder.uninstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (app.isSistem()){
                    holder.uninstall.setActivated(false);
                }else {
                    new AlertDialog.Builder(context)
                            .setTitle("Confirmar operación")
                            .setMessage("La aplicación se desinstalará. \n ¿Está seguro que desea continuar?")
                            .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    apps.deleteAppById(id);
                                    Toast.makeText(v.getContext(), "La aplicación se ha desinstalado con éxito.", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }).setNegativeButton("Cancelar", null).show();
                }
                }
        });

        customeView(holder, app);
    }

    public void customeView(ViewHolder holder, Apps apps){

        holder.packageName.setText(apps.getPackageName());

        int id= R.drawable.twotone_extension_24;
        switch (apps.getAppName()){
            case YOUTUBE:id= R.drawable.icons8_youtube;
            break;
            case GOOGLEPLAY:id= R.drawable.icons8_google_play;
            break;
            case CHROME:id= R.drawable.icons8_chrome;
            break;
            case FACEBOOK:id= R.drawable.baseline_facebook_24;
            break;
            case TELEFONO:id= R.drawable.twotone_extension_24;
            break;
        }
        holder.icon.setImageResource(id);
        holder.icon.setScaleType(ImageView.ScaleType.FIT_END);
        holder.check.setOnClickListener((v -> {
        if (apps.isUnable()) {
            if (apps.isSistem()){
                new AlertDialog.Builder(context)
                        .setTitle("Confirmar operación")
                        .setMessage("Esta es una aplicación del sistema. \n ¿Está seguro que la desea desactivar?")
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                apps.setUnable(false);
                                Toast.makeText(v.getContext(), apps.getAppName().getAppName() + " ha sido desactivado.", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                            }
                        }).setNegativeButton("Cancelar", null).show();
            }else {
                apps.setUnable(false);
                Toast.makeText(v.getContext(), apps.getAppName().getAppName() + " ha sido desactivado.", Toast.LENGTH_SHORT).show();
            }
        }else {
            apps.setUnable(true);
            Toast.makeText(v.getContext(), apps.getAppName().getAppName()+" ha sido activado.", Toast.LENGTH_SHORT).show();
        }
        notifyDataSetChanged();
    }));


        if (apps.isUnable()){
            if (apps.isSistem()){
                holder.appName.setText(apps.getAppName().getAppName()+ " (Sistema)");
                holder.appName.setTypeface(null, Typeface.BOLD);
                holder.appName.setTextColor(Color.RED);
            }else {
                holder.appName.setText(apps.getAppName().getAppName());
                holder.appName.setTypeface(null, Typeface.BOLD);
                holder.appName.setTextColor(Color.BLACK);
            }
            holder.check.setBackgroundResource(R.drawable.twotone_unpublished_24);
        }else {
            holder.appName.setText(apps.getAppName().getAppName() + " (Desactivado)");
            holder.appName.setTypeface(null, Typeface.BOLD);
            holder.appName.setTextColor(Color.parseColor("#00aae4"));
            holder.check.setBackgroundResource(R.drawable.twotone_check_circle_24);
        }
    }

    @Override
    public int getItemCount() {
        return apps.appSize();
    }
}
