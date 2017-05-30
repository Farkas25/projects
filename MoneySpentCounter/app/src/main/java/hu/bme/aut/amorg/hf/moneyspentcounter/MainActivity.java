package hu.bme.aut.amorg.hf.moneyspentcounter;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import hu.bme.aut.amorg.hf.moneyspentcounter.adapter.ItemAdapter;
import hu.bme.aut.amorg.hf.moneyspentcounter.data.Item;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_NEW_ITEM_CODE = 100;
    public static final int REQUEST_EDIT_ITEM_CODE = 101;

    private ItemAdapter itemAdapter;
    private FloatingActionButton btnAddNewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<Item> itemsToBought = Item.listAll(Item.class);

        itemAdapter = new ItemAdapter(this, itemsToBought);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.item_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(itemAdapter);

        btnAddNewItem = (FloatingActionButton) findViewById(R.id.btnAddNewItem);
        btnAddNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(MainActivity.this, CreateBoughtItemActivity.class);
                startActivityForResult(i, REQUEST_NEW_ITEM_CODE);
            }
        });

        if(getIntent().getExtras() != null && getIntent().getExtras().containsKey(CreateBoughtItemActivity.KEY_SMS_ITEM)){
            Item item = (Item) getIntent().getSerializableExtra(CreateBoughtItemActivity.KEY_SMS_ITEM);
            item.save();
            itemAdapter.addItem(item);
            itemAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(resultCode){
            case RESULT_OK:
                if(requestCode == REQUEST_NEW_ITEM_CODE) {
                    Item item = (Item) data.getSerializableExtra(CreateBoughtItemActivity.KEY_ITEM);
                    item.save();
                    itemAdapter.addItem(item);
                    itemAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Add new item", Toast.LENGTH_LONG).show();
                    break;
                }

                else if(requestCode == REQUEST_EDIT_ITEM_CODE) {
                    int itemIndex = data.getIntExtra(CreateBoughtItemActivity.KEY_EDIT_ID, -1);
                    if(itemIndex != -1) {
                        Item item = (Item) data.getSerializableExtra(CreateBoughtItemActivity.KEY_ITEM);
                        item.setId(itemAdapter.getItem(itemIndex).getId());
                        item.save();

                        itemAdapter.updateItem(itemIndex, item);
                        itemAdapter.notifyDataSetChanged();
                    }

                }

            case RESULT_CANCELED:
                Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_details){
            Intent i = new Intent();
            i.setClass(MainActivity.this, DetailsActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
