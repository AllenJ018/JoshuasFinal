package edu.mssu.cis385.afinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ItemHolder> {
    private ArrayList<AnItem> itemList;
    private Context mContext;

    public RecAdapter (ArrayList<AnItem> itemList, Context mContext) {
        this.itemList = itemList;
        this.mContext = mContext;
    }

    public void setItemList(ArrayList<AnItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recylerview, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public int getItemCount() {
        return itemList ==null? 0:itemList.size();
    }
    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int position){
        final AnItem anItem = itemList.get(position);

        holder.setItemName(anItem.getName());
        holder.setItemType(anItem.getType());
        holder.setItemIcon(anItem.getIcon());
        holder.setItemRarity(anItem.getRarity());
        holder.setItemVendor(anItem.getVendor());
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtType;
        public TextView txtIcon;
        public TextView txtRarity;
        public TextView txtVendor;

        public ItemHolder(View itemView){
            super(itemView);

            txtName = itemView.findViewById(R.id.nameView);
            txtType = itemView.findViewById(R.id.typeView);
            txtIcon = itemView.findViewById(R.id.iconView);
            txtRarity = itemView.findViewById(R.id.rarityView);
            txtVendor = itemView.findViewById(R.id.vendorView);
        }

        public void setItemName(String name)
        {
            String text = "Item Name: " + name;
            txtName.setText(text);
        }

        public void setItemType(String type)
        {
            String text = "Item type: " + type;
            txtType.setText(text);
        }

        public void setItemIcon(String icon)
        {

            txtIcon.setText(icon);
        }

        public void setItemRarity(String rarity)
        {
            String text = "Item Rarity: " + rarity;
            txtRarity.setText(text);
        }

        public void setItemVendor(int vendor)
        {
            String text = "Vendor Value: " + String.valueOf(vendor);
            txtVendor.setText(text);
        }
    }
}
