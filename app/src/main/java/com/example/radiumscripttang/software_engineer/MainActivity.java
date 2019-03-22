package com.example.radiumscripttang.software_engineer;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.Arrays;

import parsii.eval.Parser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_point; //小数点
    Button btn_clear; //清除
    Button btn_del;   //删除
    Button btn_plus; //+
    Button btn_minus; //-
    Button btn_multiply; //*
    Button btn_divide; // /
    Button btn_equal; // =
    Button btn_mod;
    Button btn_left; // (
    Button btn_right; // )
    Button btn_sin, btn_cos, btn_tan, btn_lg, btn_ln, btn_pow, btn_square, btn_cube, btn_pi, btn_euler, btn_sqrt;
    private TextView e_input;
    private StringBuilder formula = new StringBuilder();
    private StringBuilder expression = new StringBuilder();

    private void initView() {
        btn_0 =  findViewById(R.id.btn_0);
        btn_1 =  findViewById(R.id.btn_1);
        btn_2 =  findViewById(R.id.btn_2);
        btn_3 =  findViewById(R.id.btn_3);
        btn_4 =  findViewById(R.id.btn_4);
        btn_5 =  findViewById(R.id.btn_5);
        btn_6 =  findViewById(R.id.btn_6);
        btn_7 =  findViewById(R.id.btn_7);
        btn_8 =  findViewById(R.id.btn_8);
        btn_9 =  findViewById(R.id.btn_9);
        btn_point =  findViewById(R.id.btn_point);
        btn_clear =  findViewById(R.id.btn_clear);
        btn_del =  findViewById(R.id.btn_del);
        btn_plus =  findViewById(R.id.btn_plus);
        btn_minus =  findViewById(R.id.btn_minus);
        btn_multiply =  findViewById(R.id.btn_mul);
        btn_divide =  findViewById(R.id.btn_divide);
        btn_equal =  findViewById(R.id.btn_equal);
        btn_mod =  findViewById(R.id.btn_mod);
        e_input = (TextView) findViewById(R.id.input);
        btn_left =  findViewById(R.id.btn_left);
        btn_right =  findViewById(R.id.btn_right);
        btn_sin = findViewById(R.id.btn_sin);
        btn_cos = findViewById(R.id.btn_cos);
        btn_tan = findViewById(R.id.btn_tan);
        btn_lg = findViewById(R.id.btn_lg);
        btn_ln = findViewById(R.id.btn_ln);
        btn_pow = findViewById(R.id.btn_pow);
        btn_square = findViewById(R.id.btn_square);
        btn_cube = findViewById(R.id.btn_cube);
        btn_euler = findViewById(R.id.btn_euler);
        btn_pi = findViewById(R.id.btn_pi);
        btn_sqrt = findViewById(R.id.btn_sqrt);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_sin.setOnClickListener(this);
        btn_cos.setOnClickListener(this);
        btn_tan.setOnClickListener(this);
        btn_ln.setOnClickListener(this);
        btn_lg.setOnClickListener(this);
        btn_euler.setOnClickListener(this);
        btn_pi.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        btn_square.setOnClickListener(this);
        btn_cube.setOnClickListener(this);
        btn_sqrt.setOnClickListener(this);
        btn_pow.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }


    public void onClick(View v) {
        int last = 0;
        if(formula.length()!=0)
        {
            last = formula.codePointAt(formula.length()-1);

        }
        switch (v.getId()) {
            case R.id.btn_0:
                formula.append("0");
                expression.append("0");
                break;
            case R.id.btn_1:
                formula.append("1");
                expression.append("1");
                break;
            case R.id.btn_2:
                formula.append("2");
                expression.append("2");
                break;
            case R.id.btn_3:
                formula.append("3");
                expression.append("3");
                break;
            case R.id.btn_4:
                formula.append("4");
                expression.append("4");
                break;
            case R.id.btn_5:
                formula.append("5");
                expression.append("5");
                break;
            case R.id.btn_6:
                formula.append("6");
                expression.append("6");
                break;
            case R.id.btn_7:
                formula.append("7");
                expression.append("7");
                break;
            case R.id.btn_8:
                formula.append("8");
                expression.append("8");
                break;
            case R.id.btn_9:
                formula.append("9");
                expression.append("9");
                break;
            case R.id.btn_plus:
                //if (last >= '0' && last <= '9' ) {
                formula.append("+");
                expression.append("+");
                // }
                break;
            case R.id.btn_minus:
                //if (last >= '0' && last <= '9') {
                formula.append("-");
                expression.append("-");
                //  }
                break;
            case R.id.btn_mul:
                // if (last >= '0' && last <= '9' ) {
                formula.append("*");
                expression.append("×");
                // }
                break;
            case R.id.btn_divide:
                // if (last >= '0' && last <= '9' ) {
                formula.append("/");
                expression.append("/");
                // }
                break;
            case R.id.btn_sin:
                formula.append("sin(");
                expression.append("sin(");
                break;
            case R.id.btn_cos:
                formula.append("cos(");
                expression.append("cos(");
                break;
            case R.id.btn_tan:
                formula.append("tan(");
                expression.append("tan(");
                break;
            case R.id.btn_point:
                if (judge_point()) {
                    formula.append(".");
                    expression.append(".");
                }
                break;
            case R.id.btn_lg:
                formula.append("lg(");
                expression.append("lg(");
                break;
            case R.id.btn_ln:
                formula.append("ln(");
                expression.append("ln(");
                break;
            case R.id.btn_euler:
                formula.append("euler");
                expression.append("e");
                break;
            case R.id.btn_pi:
                formula.append("pi");
                expression.append("π");
                break;
            case R.id.btn_pow:
                formula.append("^");
                expression.append("^");
                break;
            case R.id.btn_square:
                formula.append("^2");
                expression.append("^2");
                break;
            case R.id.btn_cube:
                formula.append("^3");
                expression.append("^3");
                break;
            case R.id.btn_sqrt:
                formula.append("sqrt(");
                expression.append("√(");
                break;
            case R.id.btn_left:
                formula.append("(");
                expression.append("(");
                break;
            case R.id.btn_right:
                formula.append(")");
                expression.append(")");
                break;
            case R.id.btn_del: //删除
                if (formula.length() != 0) {
//                    formula.delete(formula.length() - 1, formula.length());
                    if (formula.length() > 1 && formula.substring(formula.length() - 2).equals("pi")) {
                        //最后一个是pi
                        formula.delete(formula.length() - 2, formula.length());
                        expression.delete(expression.length() - 1, expression.length());
                    } else if (formula.length() > 4 && (formula.substring(formula.length() - 5).equals("euler"))) {
                        //最后一个是自然对数的底
                        formula.delete(formula.length() - 5, formula.length());
                        expression.delete(expression.length() - 1, expression.length());
                    } else if (formula.length() > 3 && (formula.substring(formula.length() - 4).equals("sin(") || formula.substring(formula.length() - 4).equals("cos(") || formula.substring(formula.length() - 4).equals("tan("))) {
                        //最后一位是sin( cos( tan(
                        formula.delete(formula.length() - 4, formula.length());
                        expression.delete(expression.length() - 4, expression.length());
                    } else if (formula.length() > 2 && (formula.substring(formula.length() - 3).equals("lg(") || formula.substring(formula.length() - 3).equals("ln("))) {
                        //最后一个是求对数
                        formula.delete(formula.length() - 3, formula.length());
                        expression.delete(expression.length() - 3, expression.length());
                    } else if (formula.length() > 4 && formula.substring(formula.length() - 5).equals("sqrt(")){
                        //最后一个是开根号
                        formula.delete(formula.length() - 5, formula.length());
                        expression.delete(expression.length() - 2, expression.length());
                    } else {
                        formula.delete(formula.length() - 1,formula.length());
                        expression.delete(expression.length() - 1,expression.length());
                    }
                }
                break;
            case R.id.btn_clear: //清空
                formula.delete(0, formula.length());
                expression.delete(0,expression.length());
                break;
            case R.id.btn_equal: // =等于
                if ((formula.length() > 1)) {
                    Parser exp ;
                    String result;
                    try {
                        String a = formula.toString();
                        BigDecimal result_num = Parser.parse(a).evaluate();
                        result = String.valueOf(Double.parseDouble(result_num.toString()));
                        expression.delete(0,expression.length());
                        expression.append(result);
                    } catch (Exception ex) {
                        result = "出错";
                        expression.delete(0,expression.length());
                        expression.append(result);
                    }
//                    expression.append()
                    formula.delete(0, formula.length());
                    if (Character.isDigit(result.charAt(0))) {
                        formula.append(result);
                    }
                }
                break;
            default:
                break;
        }
        e_input.setText(expression);
        Log.i("MAIN", "onClick: formula " + formula.length());
        Log.i("MAIN", "onClick: expression " + expression.length() );
    }
    //计算式最后一个符号不能是.
    private boolean judge_point() {
        String a = "+-*/%.";
        int[] b = new int[a.length()];
        int max;
        for (int i = 0; i < a.length(); i++) {
            b[i] = -1;
            String c = "" + a.charAt(i);
            b[i] = formula.lastIndexOf(c);
        }
        Arrays.sort(b);
        //最后一个是不是点
        if (b[a.length() - 1] == -1) {
            max = 0;
        } else {
            max = b[a.length() - 1];
        }

        if (formula.indexOf(".", max) == -1) {
            return true;
        } else {
            return false;
        }
    }
    private int judge_brackets(){
        int a=0,b=0;
        for(int i = 0 ; i < formula.length() ;i++){
            if(formula.charAt(i)=='(' ) {
                a++;
            }
            if(formula.charAt(i)==')' ) {
                b++;
            }
        }
        if(a == b)
            return 0;
        if(a > b)
            return 1;
        return 2;
    }




}