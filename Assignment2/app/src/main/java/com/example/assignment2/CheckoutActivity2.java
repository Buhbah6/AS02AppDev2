package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CheckoutActivity2 extends AppCompatActivity {
    private RadioButton shipButton;
    private RadioButton pickButton;
    private Button completeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout2);

        shipButton = findViewById(R.id.shippingButton);
        pickButton = findViewById(R.id.pickupButton);
        completeButton = findViewById(R.id.completeButton);
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shipButton.isChecked() || pickButton.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), ConfirmationActivity.class);
                    if (shipButton.isChecked())
                        intent.putExtra("shipping", "Shipping");
                    else
                        intent.putExtra("shipping", "Pickup");
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please select a method of delivery.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), CheckoutActivity1.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}