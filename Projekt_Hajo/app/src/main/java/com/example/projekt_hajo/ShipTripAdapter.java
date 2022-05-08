package com.example.projekt_hajo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ShipTripAdapter extends RecyclerView.Adapter<ShipTripAdapter.ViewHolder> implements Filterable {
    private ArrayList<ShipTrip> mShipTripData = new ArrayList<>();
    private ArrayList<ShipTrip> mShipTripDataAll = new ArrayList<>();
    private Context mContext;
    private int lastPosition = -1;

    ShipTripAdapter(Context context, ArrayList<ShipTrip> itemsData){
        this.mShipTripData = itemsData;
        this.mShipTripDataAll =itemsData;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ShipTripAdapter.ViewHolder holder, int position) {
        ShipTrip currentItem = mShipTripData.get(position);

        holder.bindTo(currentItem);

        if(holder.getAdapterPosition() > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide);
            Animation animation2 = AnimationUtils.loadAnimation(mContext, R.anim.rotate);
            holder.itemView.startAnimation(animation);
            holder.itemView.startAnimation(animation2);
            lastPosition = holder.getAdapterPosition();
        }
    }

    @Override
    public int getItemCount() {
        return mShipTripData.size();
    }

    @Override
    public Filter getFilter() {
        return ShopFilter;
    }
    private Filter ShopFilter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<ShipTrip> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if(constraint == null || constraint.length() == 0) {
                results.count = mShipTripDataAll.size();
                results.values = mShipTripDataAll;
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(ShipTrip item : mShipTripDataAll) {
                    if(item.getNev().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mShipTripDataAll = (ArrayList)results.values;
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTitleText;
        private TextView mInfoText;
        private TextView mPriceText;
        private ImageView mItemImage;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleText = itemView.findViewById(R.id.itemTitle);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mItemImage = itemView.findViewById(R.id.itemImage);
            mPriceText = itemView.findViewById(R.id.price);

            itemView.findViewById(R.id.add_to_cart).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Activity","Kosarhoz adas gomb lenyomva");
                    ((ShipListActivity)mContext).updateAlertIcon();
                }
            });

        }

        public void bindTo(ShipTrip currentItem) {
            mTitleText.setText(currentItem.getNev());
            mInfoText.setText(currentItem.getInfo());
            mPriceText.setText(currentItem.getAr());

            Glide.with(mContext).load(currentItem.getImageRes()).into(mItemImage);
        }
    }
}


