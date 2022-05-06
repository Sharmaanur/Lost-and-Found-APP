package com.suman.lostfound;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.suman.lostfound.DB.DBHandler;
import com.suman.lostfound.DB.ItemModel;

public class AdvertActivity extends AppCompatActivity {
    EditText name, phone, desc, date, location;
    Button btnsave;
    DBHandler dbHandler;
    RadioGroup postype;
    int type = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert);
        name = findViewById(R.id.ed_name);
        phone = findViewById(R.id.ed_phone);
        desc = findViewById(R.id.ed_description);
        date = findViewById(R.id.ed_date);
        location = findViewById(R.id.ed_location);
        btnsave = findViewById(R.id.btn_save);
        dbHandler = new DBHandler(this);
        postype = findViewById(R.id.radio_group);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().trim().isEmpty()){
                    ItemModel itemModel = new ItemModel();
                    if (type == 1){
                        itemModel.setName("Found "+name.getText().toString().trim());
                    }else{
                        itemModel.setName("Lost "+name.getText().toString().trim());
                    }

                    itemModel.setPhone(phone.getText().toString().trim());
                    itemModel.setDescription(desc.getText().toString().trim());
                    itemModel.setDate(date.getText().toString().trim());
                    itemModel.setLocation(location.getText().toString().trim());
                    itemModel.setType(type);
                    dbHandler.addItem(itemModel);
                    name.setText("");
                    phone.setText("");
                    desc.setText("");
                    date.setText("");
                    location.setText("");
                }
            }
        });
        postype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.type_lost:
                        type = 0;
                        break;
                    case R.id.type_found:
                        type = 1;
                        break;
                    default:
                        break;
                }
            }
        });
    }
}