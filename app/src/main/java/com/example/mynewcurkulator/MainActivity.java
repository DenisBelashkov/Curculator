package com.example.mynewcurkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewAnimator;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Integer> numberIds = Arrays.asList(
            R.id.dot,
            R.id.nine,
            R.id.eight,
            R.id.seven,
            R.id.six,
            R.id.five,
            R.id.four,
            R.id.three,
            R.id.two,
            R.id.one,
            R.id.zero
    );
    private List<Integer> operationsIds = Arrays.asList(
            R.id.minus,
            R.id.plus,
            R.id.sign,
            R.id.div,
            R.id.equals,
            R.id.multiply,
            R.id.clear,
            R.id.del,
            R.id.beer,
            R.id.twoBeer,
            R.id.chicken,
            R.id.worm,
            R.id.procent
    );

    private List<String> operations = Arrays.asList(
            "+", "-", "*", "/", "%", "C", "=", "⌫", "±", "🐔", "🍺", "🐛", "🍻"


    );

    private List<Integer> specialIds = Arrays.asList(
            R.id.beer,
            R.id.twoBeer,
            R.id.chicken,
            R.id.worm
    );

    Character operation = null;
    Double operand1;
    Double operand2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.output);
        textView.setText("0");


        View.OnClickListener onNumberClick = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (textView.length() == 1 && textView.getText().charAt(0) == '0' && button.getText() != ".")
                    textView.setText(button.getText());
                else {
                    textView.append(button.getText());
                    if (operations.contains("" + textView.getText().charAt(textView.length() - 2)))
                        operation = textView.getText().charAt(textView.length() - 2);


                }
            }

        };
        View.OnClickListener onOperationClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (!operations.contains(textView.getText().charAt(textView.length() - 1))) {
                    String op = textView.getText().toString();
                    if (operation == null) {
                        operand1 = Double.parseDouble(op);
                        switch (button.getText().toString()) {

                            case "±":
                                operand1 /=-1;
                                textView.setText(operand1.toString());
                                break;
                            case "=":
                                break;
                            case "🐔":
                                textView.setText("🐔");
                                break;
                            case "🍺":
                                textView.append("🍺");
                                break;
                            case "🐛":
                                textView.setText("I'm not а bag! I'm a worm!");
                                break;
                            case "🍻":
                                textView.setText(operand1 * 2 + "🍻");
                                break;
                            case "%":
                                operand1*=0.01;
                                textView.setText(operand1+"");
                                break;
                            case "⌫":
                                operand1 = Double.parseDouble(operand1.toString().substring(0,operand1.toString().length()-1));
                                textView.setText(operand1+"");
                                break;
                            case "C":
                                operand1=0.0;
                                textView.setText(operand1+"");
                                break;
                            default:
                                textView.append(button.getText());


                        }


                    } else {


                        String[] rest2 = op.split("[+-/*]");

                        operand2 = Double.parseDouble(rest2[rest2.length - 1]);


                        switch (operation) {
                            case '/':
                                if (operand2 == 0) {
                                    operand1 = 0.0;
                                } else {
                                    operand1 /= operand2;
                                }
                                break;
                            case '*':
                                operand1 *= operand2;
                                break;
                            case '+':
                                operand1 += operand2;
                                break;
                            case '-':
                                operand1 -= operand2;
                                break;


                        }
                        operation = null;
                        textView.setText(operand1.toString());

                    }


                }
            }


        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            numberIds.forEach(numberId -> {
                findViewById(numberId).setOnClickListener(onNumberClick);
            });
            operationsIds.forEach(operationId -> {
                findViewById(operationId).setOnClickListener(onOperationClick);
            });
        }

    }


    /*
    записать выражение в строку
    использовать рекурсивный спуск
    погуглить?
     */
}