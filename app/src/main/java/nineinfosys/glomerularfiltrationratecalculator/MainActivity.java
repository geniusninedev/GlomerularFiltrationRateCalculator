package nineinfosys.glomerularfiltrationratecalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DecimalFormat precision = new DecimalFormat("0.00");
    TextView creatinineTextview,ageTextview,genderTextview;
    EditText creatinineEditText,ageEditText;

    Spinner genderField;
    Spinner unitField;

    TextView glomularFiltrationValue;
    TextView QuadraticFormulaValue;
    gfr_Calci glomularFiltrationRate;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        creatinineTextview=(TextView)findViewById(R.id.textViewCreatinine);
        ageTextview=(TextView)findViewById(R.id.textViewAge);
        creatinineEditText=(EditText)findViewById(R.id.editTextCreatine);
        ageEditText=(EditText)findViewById(R.id.editTextAge);
        genderTextview=(TextView) findViewById(R.id.textViewGender);
        QuadraticFormulaValue=(TextView)findViewById(R.id.textQuadratic);

        glomularFiltrationValue=(TextView)findViewById(R.id.textResult);
        //radiusTextview=(TextView)findViewById(R.id.textViewRadius);
        Button buttonCalculte=(Button) findViewById(R.id.buttonCalculate);
        Button buttonClear=(Button) findViewById(R.id.buttonClear);
        genderField = (Spinner) findViewById(R.id.spinnerGender);
        unitField = (Spinner) findViewById(R.id.spinnerCreatinine);

        ArrayList area = new ArrayList();
        area.add("Male");
        area.add("Female");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, area);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderField.setAdapter(dataAdapter);

        ArrayList area1 = new ArrayList();
        area1.add("mg/dl");
        area1.add("mmol/L");


        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, area1);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitField.setAdapter(dataAdapter1);



        buttonCalculte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (creatinineEditText.getText().toString().trim().equals("")) {
                    creatinineEditText.setError("Enter creatinine");
                } else if (ageEditText.getText().toString().trim().equals("")) {
                    ageEditText.setError("Enter age");
                }else{
                    gfr_Calci gfr_calci=new gfr_Calci();
                   gfr_Calci resultnext=new gfr_Calci();

                    double gfr_resultQuadratic=resultnext.gfrQuadratic(Double.parseDouble(creatinineEditText.getText().toString().trim()),
                            Double.parseDouble(ageEditText.getText().toString().trim()),
                            genderField.getSelectedItem().toString().trim(),unitField.getSelectedItem().toString().trim());
                    QuadraticFormulaValue.setText("The Mayo Quadratic Formula:" + gfr_resultQuadratic+"mL/min/1.73 m2");
                   // Toast.makeText(getApplicationContext(),"The Mayo Quadratic Formula:"+ gfr_resultQuadratic,Toast.LENGTH_LONG).show();

                    double gfr_result=gfr_calci.gfr(Double.parseDouble(creatinineEditText.getText().toString().trim()),Double.parseDouble(ageEditText.getText().toString().trim()),
                            genderField.getSelectedItem().toString().trim(),unitField.getSelectedItem().toString().trim());
                    glomularFiltrationValue.setText("The CKD-EPI Formula Result:" + gfr_result+"mL/min/1.73 m2");
                }
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                creatinineEditText.getText().clear();
                ageEditText.getText().clear();
                glomularFiltrationValue.setText("");
                QuadraticFormulaValue.setText("");

            }
        });
    }
}
