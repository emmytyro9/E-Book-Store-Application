package com.example.methawee.myapplication.data.cart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.methawee.myapplication.R;
import com.example.methawee.myapplication.data.Book;
import com.example.methawee.myapplication.main.book.BookActivity;

public class ShowListOrderActivity extends AppCompatActivity {

    ListView list;
    ArrayAdapter<Book> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_order);
        list = (ListView) findViewById(R.id.list_order_books);
        listAdapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, BookActivity.cartArrayList);
        list.setAdapter(listAdapter);
    }
}
