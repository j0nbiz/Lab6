package jonbiz.lab6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class lab6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab6);
    }

    public void onCalculateClick(View view){
        // Setting intial values
        double loanamt = 0;
        double loanterm = 0;
        double loanint = 0;

        double paymonth = 0;
        double paytotal = 0;
        double payint = 0;

        // Get input fields
        EditText in_loanamt = (EditText)findViewById(R.id.in_loanamt);
        EditText in_loanterm = (EditText)findViewById(R.id.in_loanterm);
        EditText in_loanint = (EditText)findViewById(R.id.in_loanint);

        // Get output fields
        TextView out_paymonth = (TextView)findViewById(R.id.out_paymonth);
        TextView out_paytotal = (TextView)findViewById(R.id.out_paytotal);
        TextView out_payint = (TextView)findViewById(R.id.out_payint);

        // Checking for filled fields
        if(in_loanamt.getText().length() == 0 ||
                in_loanterm.getText().length() == 0 ||
                in_loanint.getText().length() == 0){
            Toast.makeText(getApplicationContext(), "Please fill in the fields!", Toast.LENGTH_SHORT).show();
        }
        else{
            // Setting fields
            loanamt = Double.parseDouble(in_loanamt.getText().toString());
            loanterm = Double.parseDouble(in_loanterm.getText().toString());
            loanint = Double.parseDouble(in_loanint.getText().toString());

            paymonth = loanamt * (loanint / (12 * 100) / (1 - Math.pow(1 + loanint / (12 * 100), -(loanterm * 12))));
            paytotal = paymonth * 12 * loanterm;
            payint = paytotal - loanamt;

            if(Double.isNaN(paymonth) ||
                    Double.isNaN(paytotal) ||
                    Double.isNaN(payint) ||
                    loanamt < 0.0 ||
                    loanterm < 1.0 || loanterm > 25.0 ||
                    loanint < 0.0 || loanint > 100.0){
                Toast.makeText(getApplicationContext(), "Enter valid numbers!", Toast.LENGTH_SHORT).show();
            }
            else{
                out_paymonth.setText("$" + String.valueOf(Math.round(paymonth*100.0)/100.0));
                out_paytotal.setText("$" + String.valueOf(Math.round(paytotal*100.0)/100.0));
                out_payint.setText("$" + String.valueOf(Math.round(payint*100.0)/100.0));

                Toast.makeText(getApplicationContext(), "Calculated results!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onClearClick(View view){
        // Get input fields
        EditText in_loanamt = (EditText)findViewById(R.id.in_loanamt);
        EditText in_loanterm = (EditText)findViewById(R.id.in_loanterm);
        EditText in_loanint = (EditText)findViewById(R.id.in_loanint);

        // Get output fields
        TextView out_paymonth = (TextView)findViewById(R.id.out_paymonth);
        TextView out_paytotal = (TextView)findViewById(R.id.out_paytotal);
        TextView out_payint = (TextView)findViewById(R.id.out_payint);

        in_loanamt.setText("");
        in_loanterm.setText("");
        in_loanint.setText("");

        out_paymonth.setText("");
        out_paytotal.setText("");
        out_payint.setText("");

        Toast.makeText(getApplicationContext(), "Cleared results!", Toast.LENGTH_SHORT).show();
    }
}
