package com.example.methawee.myapplication.data.cart;

import android.view.View;

import com.example.methawee.myapplication.main.book.BookActivity;

import static com.example.methawee.myapplication.data.cart.CartActivity.addBalance_button;
import static com.example.methawee.myapplication.data.cart.CartActivity.txtBalance;
import static com.example.methawee.myapplication.main.book.BookActivity.user;

public class CartPresenter implements CartView {

    @Override
    public double totalPrice() {
        double sum = 0;
        for (int i = 0; i < BookActivity.cartArrayList.size(); i++ ){
            sum += BookActivity.cartArrayList.get(i).getPrice();
        }
        return sum;
    }

    @Override
    public void increaseBalance( ) {
        addBalance_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setBalance(user.getBalance() + 100);
                txtBalance.setText("" + user.getBalance());

            }
        });
    }
}
