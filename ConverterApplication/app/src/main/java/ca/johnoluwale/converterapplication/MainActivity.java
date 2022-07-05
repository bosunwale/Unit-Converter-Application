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
    Spinner spinnerConversionTo, spinnerConversionFrom;
    EditText editTextValueA, editTextValueB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextValueA = findViewById(R.id.editTextInitalValue);
        editTextValueB = findViewById(R.id.editTextFInalValue);

        spinnerConversionTo = findViewById(R.id.spinnerTo);
        spinnerConversionFrom = findViewById(R.id.spinnerFrom);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.conversion_unit, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConversionTo.setAdapter(adapter);
        spinnerConversionFrom.setAdapter(adapter);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (spinnerConversionTo.getSelectedItem().toString().equals("Kilometer") && spinnerConversionFrom.getSelectedItem().toString().equals("Meter")){
                    double value1 = Double.parseDouble(editTextValueA.getText().toString());
                    editTextValueB.setText(String.valueOf(value1 * 1000) + " m");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        editTextValueA.addTextChangedListener(textWatcher);
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