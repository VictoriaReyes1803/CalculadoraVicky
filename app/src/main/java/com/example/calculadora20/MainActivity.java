package com.example.calculadora20;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private String input = "";
    private double num1, num2;
    private char operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Configurar clics de botón para los dígitos
        Button[] digitButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            String buttonID = "btn" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            digitButtons[i] = findViewById(resID);
            final int finalI = i;
            digitButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    input += finalI;
                    display.setText(input);
                }
            });
        }

        // Configurar clics de botón para los operadores
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSubtract = findViewById(R.id.btnSubtract);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                operator = '+';
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                operator = '-';
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                operator = '*';
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                operator = '/';
            }
        });

        // Configurar clic para el botón "="
        Button btnEquals = findViewById(R.id.btnEquals);
        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                operator = ' ';
            }
        });

        // Configurar clic para el botón "C" (limpiar)
        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }

    private void compute() {
        if (!input.isEmpty()) {
            if (num1 == 0) {
                num1 = Double.parseDouble(input);
            } else {
                num2 = Double.parseDouble(input);

                switch (operator) {
                    case '+':
                        num1 += num2;
                        break;
                    case '-':
                        num1 -= num2;
                        break;
                    case '*':
                        num1 *= num2;
                        break;
                    case '/':
                        if (num2 != 0) {
                            num1 /= num2;
                        } else {
                            clear();
                            display.setText("Error");
                            return;
                        }
                        break;
                }
            }

            input = "";
            display.setText(String.valueOf(num1));
        }
    }

    private void clear() {
        input = "";
        num1 = 0;
        num2 = 0;
        operator = ' ';
        display.setText("");
    }
}
