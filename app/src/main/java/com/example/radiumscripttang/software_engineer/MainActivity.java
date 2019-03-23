package com.example.radiumscripttang.software_engineer;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.EditText;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.Arrays;

import parsii.eval.Parser;

public class MainActivity extends AppCompatActivity {
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
    Button btn_sin, btn_cos, btn_tan, btn_cot, btn_lg, btn_ln, btn_pow, btn_square, btn_cube, btn_pi, btn_euler, btn_sqrt;
    Button btn_arc;

    private TextView e_input, e_output;
    private StringBuilder formula = new StringBuilder();
    private StringBuilder expression = new StringBuilder();
    private int leftNumber = 0; //左括号-右括号数量
    private String result = "";
    private boolean isArc = false;
    private boolean isLastEqual; // 判断最近按下的是
    //随着用户的输入进行动态计算
    private void dynamicCalculate(){
        StringBuilder tempFormula = new StringBuilder();
        tempFormula.append(formula); // 补全括号
        for (int i = 0; i < leftNumber; i++){
            tempFormula.append(")");
        }
        try {
            BigDecimal result_num = Parser.parse(tempFormula.toString()).evaluate().setScale(6,BigDecimal.ROUND_HALF_DOWN);
            result = String.valueOf(Double.parseDouble(result_num.toString()));
            e_output.setText("=" + result);
        } catch (Exception ex){

        }
    }

    private View.OnClickListener arcOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            isArc = !isArc;
            if (isArc){
                btn_sin.setText("sin¯¹");
                btn_cos.setText("cos¯¹");
                btn_tan.setText("tan¯¹");
                btn_cot.setText("cot¯¹");
            } else {
                btn_sin.setText("sin");
                btn_cos.setText("cos");
                btn_tan.setText("tan");
                btn_cot.setText("cot");
            }
        }
    };
    private View.OnClickListener equalOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if ((formula.length() > 1)) {
                try {
                    for (int i = 0; i < leftNumber; i++){
                        formula.append(")");
                    }
                    BigDecimal result_num = Parser.parse(formula.toString()).evaluate().setScale(6,BigDecimal.ROUND_HALF_DOWN);
                    result = String.valueOf(Double.parseDouble(result_num.toString()));
                    expression.delete(0,expression.length());
                    formula.delete(0,formula.length());
                    expression.append(result);
                    formula.append(result);
                    isLastEqual = true;
                } catch (Exception ex) {
                    result = "出错";
                    formula.delete(0,formula.length());
                    expression.delete(0,expression.length());
                }
                leftNumber = 0;
            }
            e_input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
            e_output.setTextSize(TypedValue.COMPLEX_UNIT_SP, 48);
            e_output.setText("=" + result);
        }
    };
    private View.OnClickListener numberOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isLastEqual){
                formula.delete(0,formula.length());
                expression.delete(0,expression.length());
                e_output.setText("");
            }
            e_input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 48);
            e_output.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
            switch (view.getId()) {
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
                case R.id.btn_euler:
                    formula.append("euler");
                    expression.append("e");
                    break;
                case R.id.btn_pi:
                    formula.append("pi");
                    expression.append("π");
                    break;
                case R.id.btn_point:
                    if (judge_point()) {
                        formula.append(".");
                        expression.append(".");
                    }
                    break;
                case R.id.btn_left:
                    formula.append("(");
                    expression.append("(");
                    leftNumber++;
                    break;
                case R.id.btn_right:
                    formula.append(")");
                    expression.append(")");
                    leftNumber--;
                    break;
            }
            e_input.setText(expression);
            isLastEqual = false;
            dynamicCalculate();
        }
    };
    private View.OnClickListener delOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isLastEqual){
                return;
            }
            if (formula.length() != 0) {
                if (formula.length() > 1 && formula.substring(formula.length() - 2).equals("pi")) {
                    //最后一个是pi
                    formula.delete(formula.length() - 2, formula.length());
                    expression.delete(expression.length() - 1, expression.length());
                } else if (formula.length() > 4 && (formula.substring(formula.length() - 5).equals("euler"))) {
                    //最后一个是自然对数的底
                    formula.delete(formula.length() - 5, formula.length());
                    expression.delete(expression.length() - 1, expression.length());
                } else if (formula.length() > 3 && (formula.substring(formula.length() - 4).equals("sin(") ||
                        formula.substring(formula.length() - 4).equals("cos(") || formula.substring(formula.length() - 4).equals("tan(")) ||
                        formula.substring(formula.length() - 4).equals("cot(")) {
                    //最后一位是sin( cos( tan(
                    formula.delete(formula.length() - 4, formula.length());
                    expression.delete(expression.length() - 4, expression.length());
                    leftNumber --;
                } else if (formula.length() > 2 && (formula.substring(formula.length() - 3).equals("lg(") || formula.substring(formula.length() - 3).equals("ln("))) {
                    //最后一个是求对数
                    formula.delete(formula.length() - 3, formula.length());
                    expression.delete(expression.length() - 3, expression.length());
                    leftNumber --;
                } else if (formula.length() > 4 && formula.substring(formula.length() - 5).equals("sqrt(")){
                    //最后一个是开根号
                    formula.delete(formula.length() - 5, formula.length());
                    expression.delete(expression.length() - 2, expression.length());
                    leftNumber --;
                } else if (formula.length() > 6 && (formula.substring(formula.length() - 7).equals("arcsin(") || formula.substring(formula.length() - 7).equals("arccos(") ||
                    formula.substring(formula.length() - 7).equals("arctan(") || formula.substring(formula.length() - 7).equals("arccot"))){
                    formula.delete(formula.length() - 7, formula.length());
                    expression.delete(expression.length() - 7, expression.length());
                    leftNumber --;
                }
                else if (formula.substring(formula.length()-1).equals(")")){
                    leftNumber --;
                } else {
                    formula.delete(formula.length() - 1,formula.length());
                    expression.delete(expression.length() - 1,expression.length());
                }
            }
            e_input.setText(expression);
            dynamicCalculate();
        }
    };
    private View.OnClickListener clearOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            formula.delete(0, formula.length());
            expression.delete(0,expression.length());
            e_input.setText(expression);
            e_output.setText("");
            leftNumber = 0;
            isLastEqual = false;
            e_input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 48);
            e_output.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        }
    };
    private View.OnClickListener basicOperationOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (formula.length() > 0 && (formula.substring(formula.length() - 1).equals("+") || formula.substring(formula.length() - 1).equals("-")
                    || formula.substring(formula.length() - 1).equals("*") || formula.substring(formula.length() - 1).equals("/") ||
                    formula.substring(formula.length() - 1).equals("%")) ){
                return;
            }
            switch (view.getId()){
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
                case R.id.btn_mod:
                    formula.append("%");
                    expression.append("%");
            }
            e_input.setText(expression);
            isLastEqual = false;
            e_input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 48);
            e_output.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        }
    };
    private View.OnClickListener advancedOperationOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isLastEqual){
                formula.delete(0, formula.length());
                expression.delete(0, expression.length());
                e_output.setText("");
            }
            switch (view.getId()){
                case R.id.btn_sin:
                    if (isArc){
                        formula.append("arcsin(");
                        expression.append("arccos(");
                    } else {
                        formula.append("sin(");
                        expression.append("sin(");
                    }
                    break;
                case R.id.btn_cos:
                    if (isArc){
                        formula.append("arccos(");
                        expression.append("arccos(");
                    } else {
                        formula.append("cos(");
                        expression.append("cos(");
                    }
                    break;
                case R.id.btn_tan:
                    if (isArc){
                        formula.append("arctan(");
                        expression.append("arctan(");
                    } else {
                        formula.append("tan(");
                        expression.append("tan(");
                    }
                    break;
                case R.id.btn_cot:
                    if (isArc){
                        formula.append("arccot(");
                        expression.append("arccot(");
                    } else {
                        formula.append("cot(");
                        expression.append("cot(");
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
                case R.id.btn_sqrt:
                    formula.append("sqrt(");
                    expression.append("√(");
                    break;
            }
            leftNumber ++;
            e_input.setText(expression);
            isLastEqual = false;
            e_input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 48);
            e_output.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        }
    };

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
        btn_left =  findViewById(R.id.btn_left);
        btn_right =  findViewById(R.id.btn_right);
        btn_sin = findViewById(R.id.btn_sin);
        btn_cos = findViewById(R.id.btn_cos);
        btn_tan = findViewById(R.id.btn_tan);
        btn_cot = findViewById(R.id.btn_cot);
        btn_lg = findViewById(R.id.btn_lg);
        btn_ln = findViewById(R.id.btn_ln);
        btn_pow = findViewById(R.id.btn_pow);
        btn_square = findViewById(R.id.btn_square);
        btn_cube = findViewById(R.id.btn_cube);
        btn_euler = findViewById(R.id.btn_euler);
        btn_pi = findViewById(R.id.btn_pi);
        btn_sqrt = findViewById(R.id.btn_sqrt);
        btn_arc = findViewById(R.id.btn_arc);
        e_input = (TextView) findViewById(R.id.input);
        e_output = findViewById(R.id.output);

        btn_0.setOnClickListener(numberOnClickListener);
        btn_1.setOnClickListener(numberOnClickListener);
        btn_2.setOnClickListener(numberOnClickListener);
        btn_3.setOnClickListener(numberOnClickListener);
        btn_4.setOnClickListener(numberOnClickListener);
        btn_5.setOnClickListener(numberOnClickListener);
        btn_6.setOnClickListener(numberOnClickListener);
        btn_7.setOnClickListener(numberOnClickListener);
        btn_8.setOnClickListener(numberOnClickListener);
        btn_9.setOnClickListener(numberOnClickListener);
        btn_point.setOnClickListener(numberOnClickListener);
        btn_euler.setOnClickListener(numberOnClickListener);
        btn_pi.setOnClickListener(numberOnClickListener);
        btn_left.setOnClickListener(numberOnClickListener);
        btn_right.setOnClickListener(numberOnClickListener);

        btn_equal.setOnClickListener(equalOnClickListener);
        btn_plus.setOnClickListener(basicOperationOnClickListener);
        btn_minus.setOnClickListener(basicOperationOnClickListener);
        btn_multiply.setOnClickListener(basicOperationOnClickListener);
        btn_divide.setOnClickListener(basicOperationOnClickListener);
        btn_square.setOnClickListener(basicOperationOnClickListener);
        btn_cube.setOnClickListener(basicOperationOnClickListener);
        btn_pow.setOnClickListener(basicOperationOnClickListener);
        btn_mod.setOnClickListener(basicOperationOnClickListener);

        btn_del.setOnClickListener(delOnClickListener);
        btn_clear.setOnClickListener(clearOnClickListener);

        btn_sin.setOnClickListener(advancedOperationOnClickListener);
        btn_cos.setOnClickListener(advancedOperationOnClickListener);
        btn_tan.setOnClickListener(advancedOperationOnClickListener);
        btn_cot.setOnClickListener(advancedOperationOnClickListener);
        btn_ln.setOnClickListener(advancedOperationOnClickListener);
        btn_lg.setOnClickListener(advancedOperationOnClickListener);
        btn_sqrt.setOnClickListener(advancedOperationOnClickListener);

        btn_arc.setOnClickListener(arcOnClickListener);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
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