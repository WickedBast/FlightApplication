package com.example.flightapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightapplication.Interface.ItemClickListener;
import com.example.flightapplication.Model.RouteSearch;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {

    private Context mCtx;
    private List<RouteSearch> routeList;
    private ItemClickListener clickListener;
    Button pay;
    public FlightAdapter(Context mCtx, List<RouteSearch> routeList) {
        this.mCtx = mCtx;
        this.routeList = routeList;
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerviwe_flight,parent,false);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {

        RouteSearch route = routeList.get(position);
        holder.textViewFrom.setText("From: "+route.getFrom());
        holder.textViewTo.setText("To: " + route.getTo());

    }

    @Override
    public int getItemCount( ) {
        return routeList.size();
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

  /*  public void pay(View view){
        Intent intent = new Intent(String.valueOf(Payment.class));
        intent.putExtra("price",100);
        startActivity(intent);
    }*/

    public class FlightViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewFrom,textViewTo;


        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewFrom = itemView.findViewById(R.id.text_view_from);
            textViewTo = itemView.findViewById(R.id.text_view_to);
            pay = itemView.findViewById(R.id.pay);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);

            pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mCtx,Payment.class);
                    intent.putExtra("price","100");
                    mCtx.startActivity(intent);

                }
            });

        }

        @Override
        public void onClick(View view) {
            if(clickListener !=null) clickListener.Onclick(view,getAdapterPosition());

        }


    }



}
