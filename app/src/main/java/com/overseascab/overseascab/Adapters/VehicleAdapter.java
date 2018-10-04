package com.overseascab.overseascab.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.overseascab.overseascab.Models.Vehicles;
import com.overseascab.overseascab.R;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.Holder> {

    List<Vehicles> vehicles;
    LayoutInflater inflater;

    public VehicleAdapter(Context c, List<Vehicles> vehicles) {
        inflater = LayoutInflater.from(c);
        this.vehicles = vehicles;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.vehicle, parent, false);
        Holder h = new Holder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.car_name.setText(vehicles.get(position).getCar_name());
        holder.car_distance.setText(vehicles.get(position).getDistance());
        holder.car_time.setText(vehicles.get(position).getTime());
        holder.total_fare.setText(vehicles.get(position).getT_fare());
        holder.basic_fare.setText(vehicles.get(position).getB_fare());
//        holder.car_pic.setIm(vehicles.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView car_pic;
        TextView car_name, car_distance, car_time, total_fare, basic_fare;

        public Holder(View itemView) {
            super(itemView);
            car_pic = (ImageView)itemView.findViewById(R.id.pic);
            car_name = (TextView)itemView.findViewById(R.id.car_name);
            car_distance = (TextView)itemView.findViewById(R.id.ride);
            car_time = (TextView)itemView.findViewById(R.id.time);
            total_fare = (TextView)itemView.findViewById(R.id.total_fare);
            basic_fare = (TextView)itemView.findViewById(R.id.basic_fare);
        }
    }
}
