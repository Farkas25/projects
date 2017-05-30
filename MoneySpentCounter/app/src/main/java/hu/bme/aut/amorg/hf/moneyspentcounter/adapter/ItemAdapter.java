package hu.bme.aut.amorg.hf.moneyspentcounter.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import hu.bme.aut.amorg.hf.moneyspentcounter.CreateBoughtItemActivity;
import hu.bme.aut.amorg.hf.moneyspentcounter.R;
import hu.bme.aut.amorg.hf.moneyspentcounter.data.Item;

import static hu.bme.aut.amorg.hf.moneyspentcounter.MainActivity.REQUEST_EDIT_ITEM_CODE;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemsViewHolder> {

    private List<Item> boughtItems;
    private Context context;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy. MM. dd");

    public ItemAdapter(Context context, List<Item> boughtItems) {
        this.context = context;
        this.boughtItems = boughtItems;
    }

    public void addItem(Item item) {boughtItems.add(item);}

    public void updateItem(int index, Item item) {boughtItems.set(index, item);}

    public void removeItem(int index) {boughtItems.remove(index);}

    public Item getItem(int index) {return boughtItems.get(index);}

    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemrowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ItemsViewHolder(itemrowView);

    }

    @Override
    public void onBindViewHolder(final ItemsViewHolder holder, int position) {
        final Item choosenItem = boughtItems.get(position);

        holder.tvItemName.setText(choosenItem.getItemName());
        holder.tvItemPrice.setText(choosenItem.getItemPrice() + " Ft");
        holder.tvBuyingPlace.setText(choosenItem.getBuyingPlace());
        holder.tvBuyingDate.setText(dateFormat.format(choosenItem.getBuyingDate()));

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(context, CreateBoughtItemActivity.class);
                i.putExtra(CreateBoughtItemActivity.KEY_EDIT_ITEM, choosenItem);
                i.putExtra(CreateBoughtItemActivity.KEY_EDIT_ID, holder.getAdapterPosition());
                ((Activity)context).startActivityForResult(i, REQUEST_EDIT_ITEM_CODE);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                popupMenu.inflate(R.menu.delete_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.item_delete){
                            choosenItem.delete();
                            removeItem(holder.getAdapterPosition());
                            notifyDataSetChanged();
                        }
                        return false;
                    }
                });
                popupMenu.show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return boughtItems.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvItemName;
        private final TextView tvItemPrice;
        private final TextView tvBuyingPlace;
        private final TextView tvBuyingDate;
        private final ImageView btnEdit;

        public ItemsViewHolder(View itemView) {
            super(itemView);

            itemView.setLongClickable(true);

            tvItemName = (TextView) itemView.findViewById(R.id.tvItemName);
            tvItemPrice = (TextView) itemView.findViewById(R.id.tvItemPrice);
            tvBuyingPlace = (TextView) itemView.findViewById(R.id.tvBuyingPlace);
            tvBuyingDate = (TextView) itemView.findViewById(R.id.tvBuyingDate);
            btnEdit = (ImageView) itemView.findViewById(R.id.btnEdit);
        }

    }



}
