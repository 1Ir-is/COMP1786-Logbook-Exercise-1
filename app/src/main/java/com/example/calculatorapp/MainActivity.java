package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView calculatingView;
    TextView resultsView;

    String calculating = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextView();
    }

    private void initTextView()
    {
        calculatingView = (TextView)findViewById(R.id.calculatingTextView);
        resultsView = (TextView)findViewById(R.id.resultTextView);
    }

    private void setCalculating(String givenValues){
        calculating = calculating + givenValues;
        calculatingView.setText(calculating);
    }

    public void equalsOnClick(View view) {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

        try {
            result = (double)engine.eval(calculating);
        } catch (ScriptException e) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        if (result != null){
            resultsView.setText(String.valueOf(result.doubleValue()));
        }
    }

    public void clearOnClick(View view) {
        calculatingView.setText("");
        calculating = "";
        resultsView.setText("");
    }

    public void bracketsOnClick(View view) {
    }

    public void powerOfOnClick(View view) {
        setCalculating("^");
    }

    public void divisionOnClick(View view) {
        setCalculating("/");
    }

    public void sevenOnClick(View view) {
        setCalculating("7");
    }

    public void eightOnClick(View view) {
        setCalculating("8");
    }

    public void nineOnClick(View view) {
        setCalculating("9");
    }

    public void timesOnClick(View view) {
        setCalculating("*");
    }

    public void fourOnClick(View view) {
        setCalculating("4");
    }

    public void fiveOnClick(View view) {
        setCalculating("5");
    }

    public void sixOnClick(View view) {
        setCalculating("6");
    }

    public void minusOnClick(View view) {
        setCalculating("-");
    }

    public void oneOnClick(View view) {
        setCalculating("1");
    }

    public void twoOnClick(View view) {
        setCalculating("2");
    }

    public void threeOnClick(View view) {
        setCalculating("3");
    }

    public void plusOnClick(View view) {
        setCalculating("+");
    }

    public void zeroOnClick(View view) {
        setCalculating("0");
    }

    public void decimalOnClick(View view) {
        setCalculating(".");
    }

}