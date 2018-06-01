package com.example.thang.contacts;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contact> arrayContact;
    private ContactAdapter adapter;
    private EditText edtname;
    private EditText edtnumber;
    private RadioButton rbtnMale;
    private RadioButton rbtnFemale;
    private Button btnadd;
    private ListView lvContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        arrayContact=new ArrayList<>();
        adapter=new ContactAdapter(this,R.layout.item_contact_lv,arrayContact);
        lvContacts.setAdapter(adapter);
        Permissions();
        lvContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showdiaglog(position);
                return false;
            }
        });

    }
    private void Permissions() {
        String[] permissions = new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS
        };
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
        }
    }
    public void Anhxa(){
        edtname = (EditText) findViewById(R.id.edit_name);
        edtnumber = (EditText) findViewById(R.id.edit_number);
        rbtnMale = (RadioButton) findViewById(R.id.rbtn_male);
        rbtnFemale = (RadioButton) findViewById(R.id.rbtn_female);
        btnadd = (Button) findViewById(R.id.btn_add);
        lvContacts= (ListView) findViewById(R.id.lv_contacts);
    }
    public void addContact(View view){
        if (view.getId()== R.id.btn_add){
            String name = edtname.getText().toString().trim();
            String number = edtnumber.getText().toString().trim();
            boolean isMale = true;
            if(rbtnMale.isChecked()){
                isMale=true;
            }
            else {
                isMale=false;
            }
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(number)) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            }
            else {
                Contact contact=new Contact(isMale,name,number);
                arrayContact.add(contact);
            }
            adapter.notifyDataSetChanged();
            }
        }
        public void showdiaglog(final int positon)
        {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_layout);
            Button btnCall = (Button) dialog.findViewById(R.id.btn_call);
            Button btnSendSMS = (Button) dialog.findViewById(R.id.btn_sendsms);
            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intentCall(positon);
                }
            });
            btnSendSMS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intentSendSMS(positon);
                }
            });
            dialog.show();
        }

    private void intentSendSMS(int positon) {
        Intent intent = new Intent();
        intent.setAction(intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:"+arrayContact.get(positon).getNumber()));
        startActivity(intent);
    }

    private void intentCall(int positon){
            Intent intent = new Intent();
            intent.setAction(intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+ arrayContact.get(positon).getNumber()));
            startActivity(intent);
        }
    }

