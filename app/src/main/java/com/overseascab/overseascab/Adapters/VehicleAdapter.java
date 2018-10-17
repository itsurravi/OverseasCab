package com.overseascab.overseascab.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.overseascab.overseascab.Models.Vehicles;
import com.overseascab.overseascab.R;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.Holder> {

    List<Vehicles> vehicles;
    LayoutInflater inflater;

    private OnItemClickListener onClick;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    public void setOnClick(OnItemClickListener onClick)
    {
        this.onClick=onClick;
    }

    Context c;
    public VehicleAdapter(Context c, List<Vehicles> vehicles) {
        inflater = LayoutInflater.from(c);
        this.vehicles = vehicles;
        this.c=c;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.vehicle, parent, false);
        Holder h = new Holder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        holder.car_name.setText(vehicles.get(position).getCarname());
        holder.basedistance.setText(vehicles.get(position).getBasedistance());
        holder.seatingcap.setText(vehicles.get(position).getSeatingcap());
        holder.baseprice.setText(vehicles.get(position).getBaseprice());
        holder.unitdistance.setText(vehicles.get(position).getUnitdistance());
        String book = vehicles.get(position).getBooked();
        Integer in = Integer.parseInt(book);
        if(in==1)
        {
            holder.booked.setVisibility(View.VISIBLE);
        }

        Glide.with(c).load(vehicles.get(position).getCarimage()).into(holder.car_pic);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{

        RelativeLayout layout;
        ImageView car_pic, booked;
        TextView car_name, basedistance, seatingcap, baseprice, unitdistance;

        public Holder(View itemView) {
            super(itemView);
            car_pic = (ImageView)itemView.findViewById(R.id.pic);
            booked = (ImageView)itemView.findViewById(R.id.booked);
            car_name = (TextView)itemView.findViewById(R.id.car_name);
            basedistance = (TextView)itemView.findViewById(R.id.basedistance);
            seatingcap = (TextView)itemView.findViewById(R.id.seatingcap);
            baseprice = (TextView)itemView.findViewById(R.id.baseprice);
            unitdistance = (TextView)itemView.findViewById(R.id.unitdistance);
            layout = (RelativeLayout)itemView.findViewById(R.id.r_layout);
        }
    }
}
