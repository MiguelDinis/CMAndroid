package com.example.cm_hw2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cm_hw2.datamodel.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CityListAdapter extends  RecyclerView.Adapter<CityListAdapter.CityViewHolder>  {

    private HashMap<String, City> mCityList;
    private List<String> keys;
    private LayoutInflater mInflater;
    private Context context;
    public CityListAdapter(Context context, HashMap<String, City> cityList){
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.mCityList = cityList;
        keys =new ArrayList<>(mCityList.keySet());
    }

    @NonNull
    @Override
    public CityListAdapter.CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.city_item,
                parent, false);
        return new CityViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CityListAdapter.CityViewHolder holder, int position) {
        String mCurrent = keys.get(position);
        //String mCurrent = "lol";
        holder.cityItemView.setText(mCurrent);

    }

    @Override
    public int getItemCount() {
        return mCityList.size();
    }

    class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView cityItemView;
        final CityListAdapter mAdapter;

        public CityViewHolder(View itemView, CityListAdapter adapter) {
            super(itemView);
            cityItemView = itemView.findViewById(R.id.city);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
            String element = keys.get(mPosition);
            String mystring = context.getString(R.string.config);
            if(mystring.equals("small")){
                Intent intent = new Intent(context,activity_b.class);
                intent.putExtra("city",element );
                context.startActivity(intent);
            }else{
                Intent intent = new Intent(context,MainActivity.class);
                intent.putExtra("city",element );
                context.startActivity(intent);
            }


        }
    }
}