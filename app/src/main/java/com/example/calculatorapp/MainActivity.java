package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView calculatingView; // TextView to display the current calculation
    TextView resultsView; // TextView to display the result

    String calculating = ""; // Stores the current calculation expression
    String formula = ""; // Stores the formula for calculation
    String tempFormula = ""; // Temporary formula for manipulation

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

        // Update the calculation expression when a button is pressed
        calculating = calculating + givenValues;

        // Display the calculation expression on the TextView
        calculatingView.setText(calculating);
    }

    public void equalsOnClick(View view) {
        Object result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf(); // Check and process power operator (^)

        try {
            result = engine.eval(formula); // Calculate the formula

            // Checks whether the result of the calculation is infinity or not a number
            if (Double.isInfinite((double) result)) {
                Toast.makeText(this, "Can't divide by zero.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (Double.isNaN((double) result)) {
                Toast.makeText(this, "Invalid format used.", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (ScriptException e) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        if (result != null) {
            if (result instanceof Double) {
                double doubleResult = (double) result;
                if (doubleResult == (int) doubleResult) {
                    // Check if the result is a whole number (integer)
                    int intResult = (int) doubleResult;
                    resultsView.setText(String.valueOf(intResult));
                } else {
                    resultsView.setText(String.valueOf(doubleResult));
                }
            } else if (result instanceof Integer) {
                int intResult = (int) result;
                resultsView.setText(String.valueOf(intResult));
            }
        }
    }



    private void checkForPowerOf()
    {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i = 0; i < calculating.length(); i++)
        {
            if (calculating.charAt(i) == '^')
                // Find the positions of the power operator (^) in the expression
                indexOfPowers.add(i);
        }

        // Copy the expression to the formula
        formula = calculating;

        // Copy the expression to the temporary formula
        tempFormula = calculating;

        for(Integer index: indexOfPowers)
        {
            changeFormula(index); // Modify the formula for power operators
        }

        // Update the calculation formula
        formula = tempFormula;
    }


    private void changeFormula(Integer index) {
        String numberLeft = "";
        String numberRight = "";

        for (int i = index + 1; i < calculating.length(); i++) {
            char currentChar = calculating.charAt(i);
            if (isNumeric(currentChar) || (currentChar == '.' && !numberRight.contains("."))) {
                numberRight += currentChar; // Build the right-hand side of the power operator
            } else {
                break;
            }
        }

        for (int i = index - 1; i >= 0; i--) {
            char currentChar = calculating.charAt(i);
            if (isNumeric(currentChar) || (currentChar == '.' && !numberLeft.contains("."))) {
                numberLeft = currentChar + numberLeft; // Build the left-hand side of the power operator
            } else {
                break;
            }
        }

        // Build the original expression
        String original = numberLeft + "^" + numberRight;

        // Build the modified expression
        String changed = "Math.pow(" + numberLeft + "," + numberRight + ")";

        // Replace the original expression with the modified one
        tempFormula = tempFormula.replace(original, changed);
    }


    private boolean isNumeric(char c)
    {
        return (c <= '9' && c >= '0') || c == '.';
    }


    public void clearOnClick(View view) {

        // Clear the calculation expression on the TextView
        calculatingView.setText("");

        // Clear the calculation expression
        calculating = "";

        // Clear the result on the TextView
        resultsView.setText("");

        leftBracket = true;

    }

    boolean leftBracket = true;
    public void bracketsOnClick(View view) {
        if (leftBracket){
            // Add an opening bracket to the calculation expression
            setCalculating("(");
            leftBracket = false;
        }
        else {
            // Add a closing bracket to the calculation expression
            setCalculating(")");
            leftBracket = true;
        }
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