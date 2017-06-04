package com.example.methawee.myapplication.data.cart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.methawee.myapplication.R;

import static com.example.methawee.myapplication.main.book.BookActivity.cartArrayList;
import static com.example.methawee.myapplication.main.book.BookActivity.user;

public class CartActivity extends AppCompatActivity {

    public static TextView txtBalance;
    public static Button addBalance_button;
    TextView totalPrice;
    Button seeCart_button;
    Button purchase_button;
    CartPresenter presenter ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        presenter = new CartPresenter() ;
        txtBalance = (TextView) findViewById(R.id.showBalance);
        addBalance_button = (Button) findViewById(R.id.btnAddMoney);

        txtBalance.setText(""+ user.getBalance());
        seeCart_button = (Button) findViewById(R.id.cartBtn);

        addBalance_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.increaseBalance();
            }
        });

        seeCart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ShowListOrderActivity.class);
                startActivity(i);
            }
        });

        totalPrice = (TextView) findViewById(R.id.totalprice);
        totalPrice.setText("" + presenter.totalPrice());
        purchase_button = (Button) findViewById(R.id.purchase);
        purchase_button.setOnClickListener(new AdapterView.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CartActivity.this);
                alertDialog.setTitle("Confirmation");
                alertDialog.setMessage("Are you sure to buy these books?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if ( presenter.totalPrice() > user.getBalance() ){
                            Toast.makeText(getApplicationContext(),"Sorry, your money is not enough", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Thank you", Toast.LENGTH_SHORT).show();
                            user.setBalance(user.getBalance()- presenter.totalPrice());
                            totalPrice.setText("0.0");
                            txtBalance.setText(user.getBalance() + "");

                            for ( int i = 0 ; i < cartArrayList.size() ; i++) {
                                user.getListbook().add(cartArrayList.get(i));
                            }

                            cartArrayList.clear();
                        }
                    }
                });

                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Cancel", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.show();
            }

        });
    }
}