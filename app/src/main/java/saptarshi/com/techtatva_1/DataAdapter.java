package saptarshi.com.techtatva_1;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{

       private List<Details> details;
    private final Activity activity;
    int i;


    public DataAdapter(List<Details> details, Activity activity){
        this.details=details;
        this.activity=activity;
     }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row_1,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i){
        viewHolder.name.setText(details.get(i).getName());
        viewHolder.user_name.setText(details.get(i).getUsername());
    }

    @Override
    public int getItemCount(){ return details.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name,user_name;
         private final Context context;
        public ViewHolder(View view) {
            super(view);
            context=view.getContext();
            name = (TextView) view.findViewById(R.id.name);
            user_name = (TextView) view.findViewById(R.id.user_name);
            view.setClickable(true);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View v){
             Intent intent = new Intent(context, SecondActivity.class);
            i=getLayoutPosition();
            Details detail= details.get(i);
            intent.putExtra("id", detail.getId());
            context.startActivity(intent);}


    }
}
