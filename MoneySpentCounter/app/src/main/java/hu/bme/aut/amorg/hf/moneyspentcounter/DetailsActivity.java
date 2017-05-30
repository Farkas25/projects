package hu.bme.aut.amorg.hf.moneyspentcounter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hu.bme.aut.amorg.hf.moneyspentcounter.data.Item;

public class DetailsActivity extends AppCompatActivity {

    private static final String PREF_NAME = "BANK_PHONE_NUMBER_PREF";
    private static final String PREF_KEY = "BANK_PHONE_NUMBER_KEY";
    private static final String PREF_KEY_2 = "CAN_SPEND_MONEY_KEY";

    private List<Item> itemsToBought;

    private int spentMoneyOnWeek;
    private int spentMoneyInMonth;

    private Calendar calendar;

    private static EditText etBankPhoneNumber;
    private TextView tvSpentMoneyOnWeek;
    private TextView tvSpentMoneyInMonth;
    private EditText etYourMoneyInMonth;
    private TextView tvYouCanSpend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        itemsToBought = Item.listAll(Item.class);
        calendar = Calendar.getInstance();
        etBankPhoneNumber = (EditText) findViewById(R.id.etBankPhoneNumber);
        etYourMoneyInMonth = (EditText) findViewById(R.id.etYourMoneyInMonth);

        SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        etBankPhoneNumber.setText(sp.getString(PREF_KEY, "36"));
        etYourMoneyInMonth.setText(sp.getString(PREF_KEY_2, ""));

        calendar.setTime(new Date(System.currentTimeMillis()));
        tvSpentMoneyOnWeek = (TextView) findViewById(R.id.tvSpentMoneyOnWeek);
        tvSpentMoneyInMonth = (TextView) findViewById(R.id.tvSpentMoneyInMonth);
        calculateSpentMoneyOnWeek();
        calculateSpentMoneyInMonth();
        tvSpentMoneyOnWeek.setText(spentMoneyOnWeek + " Ft");
        tvSpentMoneyInMonth.setText(spentMoneyInMonth + " Ft");

        tvYouCanSpend = (TextView) findViewById(R.id.tvYouCanSpend);
        if(calculateMoneyYouCanSpend() != -1)
            tvYouCanSpend.setText(calculateMoneyYouCanSpend() + " Ft");

    }

    public void calculateSpentMoneyOnWeek(){
        spentMoneyOnWeek = 0;
        calendar.setTime(new Date(System.currentTimeMillis()));
        int currentYear = calendar.get(Calendar.YEAR);
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        for (Item item: itemsToBought) {
            calendar.setTime(item.getBuyingDate());
            if(calendar.get(Calendar.YEAR) == currentYear && calendar.get(Calendar.WEEK_OF_YEAR) == currentWeek){
                spentMoneyOnWeek+=item.getItemPrice();
            }
        }
    }

    public void calculateSpentMoneyInMonth(){
        spentMoneyInMonth = 0;
        calendar.setTime(new Date(System.currentTimeMillis()));
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        for (Item item: itemsToBought) {
            calendar.setTime(item.getBuyingDate());
            if(calendar.get(Calendar.YEAR) == currentYear && calendar.get(Calendar.MONTH) == currentMonth){
                spentMoneyInMonth+=item.getItemPrice();
            }
        }
    }

    public int calculateMoneyYouCanSpend(){
        calendar.setTime(new Date(System.currentTimeMillis()));
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        if(etYourMoneyInMonth.getText().toString().equals(""))
            return -1;
        int money = Integer.parseInt(etYourMoneyInMonth.getText().toString());
        if(money > 0){
            for (Item item : itemsToBought) {
                calendar.setTime(item.getBuyingDate());
                if(item.getItemPrice() < money) {
                    if(calendar.get(Calendar.YEAR) == currentYear && calendar.get(Calendar.MONTH) == currentMonth)
                        money-=item.getItemPrice();
                }
                else{
                    money = 0;
                }
            }
        }
        return money;
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(PREF_KEY, etBankPhoneNumber.getText().toString());
        editor.putString(PREF_KEY_2, etYourMoneyInMonth.getText().toString());
        editor.commit();
    }

    public static String getBankPhoneNumber(){
        return etBankPhoneNumber.getText().toString();
    }
}
