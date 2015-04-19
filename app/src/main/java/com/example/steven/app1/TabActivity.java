package com.example.steven.app1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class TabActivity extends ActionBarActivity {

    private Button click;
    private EditText name;
    private EditText phone;
    private EditText address;
    private EditText email;
    private TabHost t1;
    private List<Contact> contacts=new ArrayList<>();
    private ListView list;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        this.click=(Button)findViewById(R.id.clickme);
        this.name=(EditText)findViewById(R.id.name);
        this.phone=(EditText)findViewById(R.id.phone);
        this.address=(EditText)findViewById(R.id.address);
        this.email=(EditText)findViewById(R.id.email);
        this.t1=(TabHost)findViewById(R.id.tabHost);
        click.setEnabled(false);

        this.list=(ListView)findViewById(R.id.listView);


        t1.setup();

        TabHost.TabSpec tab1=t1.newTabSpec("tab1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("Creator");
        t1.addTab(tab1);

        TabHost.TabSpec tab2=t1.newTabSpec("tab2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("Contact List");
        t1.addTab(tab2);
       // final ArrayAdapter<Contact> temp=new ArrayAdapter<Contact>(this,R.layout.contactview,contacts);
        final contactListAdaptor temp=new contactListAdaptor();
        list.setAdapter(temp);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!name.getText().toString().trim().equals(""))
                click.setEnabled(true);
                else
                click.setEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast a=new Toast(getApplicationContext());
//                a.setText("hello");
//                a.setDuration(Toast.LENGTH_LONG);
////                a.show();
                Toast a=Toast.makeText(TabActivity.this,"nope",Toast.LENGTH_SHORT);
                a.show();

               // addContacts(name.getText().toString(),email.getText().toString(),address.getText().toString(),phone.getText().toString());
                temp.add(new Contact(name.getText().toString(),email.getText().toString(),address.getText().toString()+"",phone.getText().toString()));
                Log.v("do not worry",contacts.size()+" "+contacts.get(contacts.size()-1).toString());
//               i++;
//                if(i==10)
//                    contacts.clear();


            }
        });

//       ArrayAdapter a=new ArrayAdapter(
//           getApplicationContext(),
//           android.R.layout.simple_list_item_1,
//           this.contacts
//       );

    }

    private void addContacts(String name, String email, String address, String phone){
        contacts.add(new Contact(name,email,address,phone));

    }

    private class contactListAdaptor extends ArrayAdapter<Contact>{

        public contactListAdaptor() {
            super(TabActivity.this, R.layout.contactview,contacts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
               convertView=getLayoutInflater().inflate(R.layout.contactview,parent,false);
            Contact current=contacts.get(position);

            TextView name=(TextView)convertView.findViewById(R.id.textView2);
            name.setText(current.get_name());

            TextView phone=(TextView)convertView.findViewById(R.id.textView3);
            phone.setText(current.get_phone());

            TextView email=(TextView)convertView.findViewById(R.id.textView4);
            email.setText(current.get_email());

            TextView address=(TextView)convertView.findViewById(R.id.textView5);
            address.setText(current.get_address());
           // return super.getView(position, convertView, parent);
            return convertView;
        }
    }




}
