package edu.mssu.cis385.afinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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
        public ImageView txtIcon;
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
            String text = "\n" +"----------------------------------------------------------" + "\n\n\n" +
                    "Item Name: " + name;
            txtName.setText(text);
        }

        public void setItemType(String type)
        {
            String text = "Item type: " + type;
            txtType.setText(text);
        }


        public void setItemRarity(String rarity)
        {
            String text = "Item Rarity: " + rarity;
            txtRarity.setText(text);
        }

        public void setItemVendor(String vendor)
        {
           // String vendorValue = toStringVendor(vendor);
            //String text = "Vendor Value: " + String.valueOf(vendor) + "\n";
            String text = "Vendor Value: " + vendor + "\n";
            txtVendor.setText(text);
        }

        public void setItemIcon(String icon)
        {
            Glide.with(mContext).load(icon).into(txtIcon);

        }

        public String toStringVendor(int vendor){

            if(vendor<100)
                return (String.valueOf(vendor)+ " Copper" );
            else if(vendor<1000){
                int vendorHolder = vendor;
                while (vendorHolder > 9) {
                    vendorHolder /= 10;
                }
                int silver = vendorHolder;
                return (String.valueOf(silver) + " Silver " + " and " + String.valueOf(vendor - (silver * 100)) + " Copper");
            }
            else
            {
                int vendorHolder = vendor;
                while (vendorHolder > 9) {
                    vendorHolder /= 10;
                }
                int gold = vendorHolder;
                vendorHolder = vendor - gold;
                while (vendorHolder > 9) {
                    vendorHolder /= 10;
                }
                int silver = vendorHolder;

                return ("Gold " + String.valueOf(gold) + " " + String.valueOf(silver) + "silver" + " and " + String.valueOf(vendor - ((silver * 100) + (gold * 100))) + " copper");
            }
        }
    }
}
