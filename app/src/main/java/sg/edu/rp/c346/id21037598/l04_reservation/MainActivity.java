package sg.edu.rp.c346.id21037598.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    // Create variables
    EditText nameInput;
    EditText numberInput;
    EditText paxInput;

    RadioGroup tableRadioGroup;

    DatePicker datePicker;
    TimePicker timePicker;

    Button submitButton;
    Button resetButton;

    TextView nameDisplay;
    TextView numberDisplay;
    TextView paxDisplay;
    TextView tableDisplay;
    TextView dateDisplay;
    TextView timeDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all variables
        nameInput = findViewById(R.id.nameInput);
        numberInput = findViewById(R.id.numberInput);
        paxInput = findViewById(R.id.paxInput);

        tableRadioGroup = findViewById(R.id.tableRadioGroup);

        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timeInput);

        submitButton = findViewById(R.id.submitButton);
        resetButton = findViewById(R.id.resetButton);

        nameDisplay = findViewById(R.id.nameDisplay);
        numberDisplay = findViewById(R.id.numberDisplay);
        paxDisplay = findViewById(R.id.paxDisplay);
        tableDisplay = findViewById(R.id.tableDisplay);
        dateDisplay = findViewById(R.id.dateDisplay);
        timeDisplay = findViewById(R.id.timeDisplay);

        // Set default values
        datePicker.updateDate(2023, 5, 1);
        datePicker.setMinDate(System.currentTimeMillis() - 1000);
        timePicker.setCurrentHour(19);
        timePicker.setCurrentMinute(30);
        tableRadioGroup.check(R.id.nonSmokingRadio);

        // Displays inputs and sends toast message
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Capture inputs into variables
                String nameResponse = nameInput.getText().toString();
                String numberResponse = numberInput.getText().toString();
                String paxResponse = paxInput.getText().toString();
                String tableResponse, timeResponse;
                String dateResponse = String.format("Date: %d/%d/%d", datePicker.getDayOfMonth(), datePicker.getMonth() + 1, datePicker.getYear());

                // Adds a 0 in the beginning of the minutes if the minute input is less than 10
                if (timePicker.getCurrentMinute() < 10)
                    timeResponse = String.format("Time : %d:0%d", timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                else
                    timeResponse = String.format("Time : %d:%d", timePicker.getCurrentHour(), timePicker.getCurrentMinute());

                // Checking which radio button user has pressed
                if (tableRadioGroup.getCheckedRadioButtonId() == R.id.nonSmokingRadio)
                    tableResponse = "Non-smoking area";
                else
                    tableResponse = "Smoking area";

                // Checking if every input is not empty
                if (!nameInput.getText().toString().equals("") &&
                !numberInput.getText().toString().equals("") &&
                !paxInput.getText().toString().equals("")) {
                    // If not empty, return inputs
                    nameDisplay.setText("Name: " + nameResponse);
                    numberDisplay.setText("Phone Number: " + numberResponse);
                    paxDisplay.setText("Number of People: " + paxResponse);
                    tableDisplay.setText("Table preference: " + tableResponse);
                    dateDisplay.setText(dateResponse);
                    timeDisplay.setText(timeResponse);

                    Toast.makeText(submitButton.getContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(submitButton.getContext(), "Error. Please fill in all fields.", Toast.LENGTH_SHORT).show();
            }
        });

        // Resets all values
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.updateDate(2023, 5, 1);
                timePicker.setCurrentHour(19);
                timePicker.setCurrentMinute(30);
                tableRadioGroup.check(R.id.nonSmokingRadio);

                nameInput.setText("");
                numberInput.setText("");
                paxInput.setText("");

                nameDisplay.setText("");
                numberDisplay.setText("");
                paxDisplay.setText("");
                tableDisplay.setText("");
                dateDisplay.setText("");
                timeDisplay.setText("");
            }
        });

        // Enhancements

        // Restricts time to be between 8am - 8:59pm
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if (timePicker.getCurrentHour() >= 21) {
                    timePicker.setCurrentHour(20);
                    timePicker.setCurrentMinute(59);
                    Toast.makeText(timePicker.getContext(), "Please input a time before 9PM.", Toast.LENGTH_SHORT).show();

                } else if (timePicker.getCurrentHour() < 8) {
                    timePicker.setCurrentHour(8);
                    timePicker.setCurrentMinute(0);
                    Toast.makeText(timePicker.getContext(), "Please input a time after 8AM.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}