package com.example.steven.app1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
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
    private ImageView photo;
    int index;
    private Uri imageUri=Uri.parse("android.resource://com.example.steven.app1/drawable/nouser");
    private Dbhandler dbhandler;
    contactListAdaptor temp;

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
            {
                break;

            }
            case 1:
            {

                dbhandler.delete(contacts.get(index));
                contacts.remove(index);
                break;
            }


        }

        temp.notifyDataSetChanged();
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderIcon(R.drawable.pencil_icon);
        menu.setHeaderTitle("Contact Edition Options");
        menu.add(Menu.NONE,0,Menu.NONE,"Edit Contact");
        menu.add(Menu.NONE,1,Menu.NONE,"Delete Contact");

    }

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
        this.photo=(ImageView)findViewById(R.id.contactimg);

        this.dbhandler=new Dbhandler(getApplicationContext());

        this.list=(ListView)findViewById(R.id.listView);
        this.temp=new contactListAdaptor();

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
        List<Contact> addable=dbhandler.getAll();
        for (int i=0;i<addable.size();i++)
        {
            contacts.add(addable.get(i));
        }



        list.setAdapter(temp);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!name.getText().toString().trim().equals(""))
                    click.setEnabled(true);
                else
                    click.setEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        this.registerForContextMenu(list);


        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                index=position;
                return false;
            }
        });

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast a=new Toast(getApplicationContext());
//                a.setText("hello");
//                a.setDuration(Toast.LENGTH_LONG);
////                a.show();
                Toast a=Toast.makeText(getApplicationContext(),"Add Contacts Successfully",Toast.LENGTH_SHORT);
                a.show();

               // addContacts(name.getText().toString(),email.getText().toString(),address.getText().toString(),phone.getText().toString());
               // temp.add(new Contact(name.getText().toString(),email.getText().toString(),address.getText().toString(),phone.getText().toString(),imageUri,0));
                Log.v(this.getClass().getSimpleName(),dbhandler.getClass().getSimpleName());
                Contact go=new Contact(name.getText().toString(),email.getText().toString(),address.getText().toString(),phone.getText().toString(),imageUri,dbhandler.getcount());

                dbhandler.insert(go);
                contacts.add(go);
                temp.notifyDataSetChanged();
//                list.setAdapter(new contactListAdaptor());

                name.setText("");
                email.setText("");
                address.setText("");
                phone.setText("");
                photo.setImageResource(R.drawable.nouser);
                imageUri=Uri.parse("android.resource://com.example.steven.app1/drawable/nouser");
                Log.v("do not worry", contacts.size() + " " + contacts.get(contacts.size() - 1).toString());
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

        this.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent();
                a.setType("image/*");
                a.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(a,"Select Contact Photo"),1);

            }
        });


    }


    @Override
        public void onActivityResult(int reqCode, int resCode, Intent data){
        if(resCode==RESULT_OK)
            if(reqCode==1)
            {

                this.imageUri=data.getData();
                this.photo.setImageURI(data.getData());
            }

    }

//    private void addContacts(String name, String email, String address, String phone){
////        contacts.add(new Contact(name,email,address,phone));
//
//    }

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

            ImageView imgphoto=(ImageView)convertView.findViewById(R.id.avator);
            Log.e("Image uRI ???:\n",getApplicationContext().getPackageName());// current.getIamgeuri().toString());
            imgphoto.setImageURI(current.getIamgeuri());
           // return super.getView(position, convertView, parent);
            return convertView;
        }
    }




}
