package utd.theia.calibrator;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import utd.theia.database.MapContract;
import utd.theia.database.MapDB;
import utd.theia.enums.Direction;

public class CalibratorActivity extends AppCompatActivity {
    private EditText fromEdit;
    private EditText toEdit;
    private EditText distanceEdit;
    private Spinner directionSpinner;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibrator);

        fromEdit = (EditText) findViewById(R.id.fromEdit);
        toEdit = (EditText) findViewById(R.id.toEdit);
        distanceEdit = (EditText) findViewById(R.id.distanceEdit);
        directionSpinner = (Spinner) findViewById(R.id.directionSpinner);
        directionSpinner.setAdapter(new ArrayAdapter<Direction>(this, android.R.layout.simple_list_item_1, Direction.values()));
        addButton = (Button) findViewById(R.id.addButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calibrator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void add(View view) {
        try {
            MapDB db = new MapDB(getApplicationContext());

            if (!fromEdit.getText().toString().isEmpty() &&
                    !toEdit.getText().toString().isEmpty() &&
                    !distanceEdit.getText().toString().isEmpty()) {
                ContentValues values = new ContentValues();
                values.put(MapContract.RoomIndicatorEntry.COLUMN_NAME_FROM, fromEdit.getText().toString());
                values.put(MapContract.RoomIndicatorEntry.COLUMN_NAME_TO, toEdit.getText().toString());
                values.put(MapContract.RoomIndicatorEntry.COLUMN_NAME_DISTANCE, distanceEdit.getText().toString());
                values.put(MapContract.RoomIndicatorEntry.COLUMN_NAME_DIRECTION, ((Direction) directionSpinner.getSelectedItem()).toString());
                db.upsert(MapContract.RoomIndicatorEntry.TABLE_NAME, null, MapContract.RoomIndicatorEntry.PRIMARY_KEY, values);

                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Check the input", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
