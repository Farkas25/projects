package hu.bme.aut.amorg.hf.moneyspentcounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

import hu.bme.aut.amorg.hf.moneyspentcounter.data.Item;
import hu.bme.aut.amorg.hf.moneyspentcounter.receiver.SMSReceiver;

public class CreateBoughtItemActivity extends AppCompatActivity {

    public static final String KEY_ITEM = "KEY_ITEM";
    public static final String KEY_EDIT_ITEM = "KEY_EDIT_ITEM";
    public static final String KEY_EDIT_ID = "KEY_EDIT_ID";
    public static final String KEY_SMS_ITEM = "KEY_SMS_ITEM";

    private EditText etItemName;
    private EditText etItemPrice;
    private EditText etBuyingPlace;
    private Button btnSave;
    private DatePicker dpBuyingDate;

    private Calendar calendar;

    private boolean inEditMode = false;
    private Item itemToEdtit = null;
    private int itemToEditId = 0;
    private boolean smsIntent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bought_item);

        calendar = Calendar.getInstance();

        etItemName = (EditText) findViewById(R.id.etItemName);
        etItemPrice = (EditText) findViewById(R.id.etItemPrice);
        etBuyingPlace = (EditText) findViewById(R.id.etBuyingPlace);
        dpBuyingDate = (DatePicker) findViewById(R.id.dpBuyingDate);

        if(getIntent().getExtras() != null && getIntent().getExtras().containsKey(KEY_EDIT_ITEM)){
            inEditMode = true;
            itemToEdtit = (Item) getIntent().getSerializableExtra(KEY_EDIT_ITEM);
            itemToEditId = getIntent().getIntExtra(KEY_EDIT_ID, -1);

            calendar.setTime(itemToEdtit.getBuyingDate());

            etItemName.setText(itemToEdtit.getItemName());
            etItemPrice.setText(""+itemToEdtit.getItemPrice());
            etBuyingPlace.setText(itemToEdtit.getBuyingPlace());
            dpBuyingDate.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        }

        if(getIntent().getExtras() != null && getIntent().getExtras().containsKey(SMSReceiver.KEY_SPENT_MONEY)){
            etItemPrice.setText(getIntent().getIntExtra(SMSReceiver.KEY_SPENT_MONEY, 0) + "");
            smsIntent = true;
        }

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(smsIntent){
                    addPriceFromSms();
                }
                if(inEditMode)
                    updateEditedItem();
                else
                    saveNewItem();
            }
        });
    }

    private void saveNewItem() {
        if(etItemPrice.getText().toString().equalsIgnoreCase("")) {
            etItemPrice.setText("0");
        }
        calendar.set(dpBuyingDate.getYear(), dpBuyingDate.getMonth(), dpBuyingDate.getDayOfMonth());
        Intent intentResult = new Intent();
        intentResult.putExtra(KEY_ITEM,
                (new Item(etItemName.getText().toString(), Integer.parseInt(etItemPrice.getText().toString()),
                        etBuyingPlace.getText().toString(), calendar.getTime())));
        setResult(RESULT_OK, intentResult);
        finish();
    }

    private void updateEditedItem() {
        if(etItemPrice.getText().toString().equalsIgnoreCase("")) {
            etItemPrice.setText("0");
        }
        calendar.set(dpBuyingDate.getYear(), dpBuyingDate.getMonth(), dpBuyingDate.getDayOfMonth());

        itemToEdtit.setItemName(etItemName.getText().toString());
        itemToEdtit.setItemPrice(Integer.parseInt(etItemPrice.getText().toString()));
        itemToEdtit.setBuyingPlace(etBuyingPlace.getText().toString());
        itemToEdtit.setBuyingDate(calendar.getTime());

        Intent intentResult = new Intent();
        intentResult.putExtra(KEY_ITEM, itemToEdtit);
        intentResult.putExtra(KEY_EDIT_ID, itemToEditId);
        setResult(RESULT_OK, intentResult);
        finish();
    }

    private void addPriceFromSms(){
        calendar.set(dpBuyingDate.getYear(), dpBuyingDate.getMonth(), dpBuyingDate.getDayOfMonth());
        Intent intent = new Intent();
        intent.setClass(CreateBoughtItemActivity.this, MainActivity.class);
        intent.putExtra(KEY_SMS_ITEM,
                (new Item(etItemName.getText().toString(), Integer.parseInt(etItemPrice.getText().toString()),
                        etBuyingPlace.getText().toString(), calendar.getTime())));
        startActivity(intent);
        finish();
    }
}
