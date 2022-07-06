package ca.johnoluwale.converterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinnerConversionFrom, spinnerConversionTo;
    EditText editTextValueTo, editTextValueFrom;
    double value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextValueTo = findViewById(R.id.editTextFinalValue);
        editTextValueFrom = findViewById(R.id.editTextInitialValue);

        /*
        The code here handles the display of the items in the
        conversion.xml file
         */
        spinnerConversionFrom = findViewById(R.id.spinnerFrom);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.conversion_unit, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConversionFrom.setAdapter(adapter);

        /*
        Implemented the textwatcher class to display the result
        of the conversion automatically. The implementation was
        done in the onTextChanged method. In the method,
        if and else-if statement was used to validate the item
        selected by the user to display the appropriate result
        based on the unit conversion selected by the user.
         */
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (spinnerConversionFrom.getSelectedItemPosition() == 0) {
                        value = Double.parseDouble(editTextValueFrom.getText().toString());
                        editTextValueTo.setText(String.format("%s m", String.valueOf(value * 1000)));
                    }
                    else if (spinnerConversionFrom.getSelectedItemPosition() == 1) {
                        value = Double.parseDouble(editTextValueFrom.getText().toString());
                        editTextValueTo.setText(String.format("%s ft", String.valueOf(value / 12)));
                    }
                    else if (spinnerConversionFrom.getSelectedItemPosition() == 2) {
                        value = Double.parseDouble(editTextValueFrom.getText().toString());
                        editTextValueTo.setText(String.format("%s F", String.valueOf((value * 9 / 5) + 32)));
                    }
                    else if (spinnerConversionFrom.getSelectedItemPosition() == 3) {
                        value = Double.parseDouble(editTextValueFrom.getText().toString());
                        editTextValueTo.setText(String.format("%s mm", String.valueOf(value * 10)));
                    }
                    else if (spinnerConversionFrom.getSelectedItemPosition() == 4) {
                        value = Double.parseDouble(editTextValueFrom.getText().toString());
                        editTextValueTo.setText(String.format("%s kg", String.valueOf(value * 0.45)));
                    }
                    else if (spinnerConversionFrom.getSelectedItemPosition() == 5) {
                        value = Double.parseDouble(editTextValueFrom.getText().toString());
                        editTextValueTo.setText(String.format("%s in", String.valueOf(value * 0.39)));
                    }
                }catch (NumberFormatException nfe){
                    value = 0;
                    editTextValueTo.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        /*
        This allows the result to display automatically depending
        on what conversion unit selected by the user
         */
        editTextValueFrom.addTextChangedListener(textWatcher);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerConversionTo.setOnItemSelectedListener(this);
        spinnerConversionFrom.setOnItemSelectedListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}