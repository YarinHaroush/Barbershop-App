package com.example.exercise_1_yr;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class PurchaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        final EditText editText =findViewById(R.id.edit);
        Button buttonAdd = findViewById(R.id.add);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numOfButtonStr=editText.getText().toString();
                if(numOfButtonStr.matches("[0-9]"));
                {
                    final LinearLayout btnProductLayout=findViewById(R.id.product);
                    btnProductLayout.removeAllViews();
                    try {
                        int numOfProduct = Integer.parseInt(numOfButtonStr);
                        Drawable img = getResources().getDrawable(R.drawable.shape_selector);
                        for (int i = 1; i < numOfProduct+1; i++) {
                            Button product = new Button(PurchaseActivity.this);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);

                            product.setLayoutParams(layoutParams);
                            product.setText(getText(R.string.product_btn).toString() + " " + i);
                            product.setBackground(img);

                            product.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String addToCart = getText(R.string.add_cart).toString();
                                    Toast.makeText(PurchaseActivity.this, addToCart, Toast.LENGTH_SHORT).show();
                                }
                            });
                            closeKeyword();
                            btnProductLayout.addView(product);
                        }
                    }
                    catch (NumberFormatException e){
                        Toast.makeText(PurchaseActivity.this, getText(R.string.toast_num), Toast.LENGTH_SHORT).show();
                        editText.setText("");
                    }

                }
                editText.setText("");
            }

        });



    }
    private void closeKeyword(){
        View view =this.getCurrentFocus();
        if(view!=null)
        {
            InputMethodManager inputMethodManager=  (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}
