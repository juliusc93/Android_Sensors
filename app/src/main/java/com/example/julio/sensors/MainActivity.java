package com.example.julio.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;


public class MainActivity extends ActionBarActivity implements SensorEventListener {
    private TextView sensorList;
    private TextView acel;
    private TextView orient;
    private TextView magnet;
    private TextView light;
    private TextView rot;
    private TextView prox;
    private TextView gyro;
    private TextView linacc;
    private TextView grav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorList = (TextView) findViewById(R.id.sensorList);
        acel = (TextView) findViewById(R.id.accelerometer);
        orient = (TextView) findViewById(R.id.orient);
        magnet = (TextView) findViewById(R.id.magnet);
        light = (TextView) findViewById(R.id.light);
        rot = (TextView) findViewById(R.id.rotation);
        prox = (TextView) findViewById(R.id.proximity);
        gyro = (TextView) findViewById(R.id.gyroscope);
        linacc = (TextView) findViewById(R.id.lin_accel);
        grav = (TextView) findViewById(R.id.gravity);
        SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor sensor: sensors) {
            log(sensorList, sensor.getName());
        }

        sensors = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
        if (!sensors.isEmpty()) {
            Sensor orientationSensor = sensors.get(0);
            sensorManager.registerListener(this, orientationSensor, SensorManager.SENSOR_DELAY_UI);
        }
        sensors = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (!sensors.isEmpty()) {
            Sensor accelerometerSensor = sensors.get(0);
            sensorManager.registerListener(this, accelerometerSensor,SensorManager.SENSOR_DELAY_UI);
        }
        sensors = sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);
        if (!sensors.isEmpty()) {
            Sensor magneticSensor = sensors.get(0);
            sensorManager.registerListener(this, magneticSensor,SensorManager.SENSOR_DELAY_UI);
        }
        sensors = sensorManager.getSensorList(Sensor.TYPE_LIGHT);
        if (!sensors.isEmpty()) {
            Sensor light = sensors.get(0);
            sensorManager.registerListener(this, light,SensorManager.SENSOR_DELAY_UI);
        }
        sensors = sensorManager.getSensorList(Sensor.TYPE_ROTATION_VECTOR);
        if (!sensors.isEmpty()) {
            Sensor rotation = sensors.get(0);
            sensorManager.registerListener(this, rotation, SensorManager.SENSOR_DELAY_UI);
        }
        sensors = sensorManager.getSensorList(Sensor.TYPE_PROXIMITY);
        if (!sensors.isEmpty()) {
            Sensor proximity = sensors.get(0);
            sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_UI);
        }
        sensors = sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE);
        if (!sensors.isEmpty()) {
            Sensor gyroscope = sensors.get(0);
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_UI);
        }
        sensors = sensorManager.getSensorList(Sensor.TYPE_LINEAR_ACCELERATION);
        if (!sensors.isEmpty()) {
            Sensor linaccel = sensors.get(0);
            sensorManager.registerListener(this, linaccel, SensorManager.SENSOR_DELAY_UI);
        }
        sensors = sensorManager.getSensorList(Sensor.TYPE_GRAVITY);
        if (!sensors.isEmpty()) {
            Sensor gravity = sensors.get(0);
            sensorManager.registerListener(this, gravity, SensorManager.SENSOR_DELAY_UI);
        }

        
    }
    private void log(TextView text, String string) {
        text.append(string + "\n");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int precision) {}

    @Override
    public void onSensorChanged(SensorEvent event) {

        synchronized (this) {
            switch(event.sensor.getType()) {
                case Sensor.TYPE_ORIENTATION:
                    orient.setText("");
                    for (int i=0 ; i<3 ; i++) {
                        orient.append(event.sensor.getName()+" "+i+": "+event.values[i]+"\n");
                    }
                    break;

                case Sensor.TYPE_ACCELEROMETER:
                    acel.setText("");
                    for (int i=0 ; i<3 ; i++) {
                        acel.append( event.sensor.getName()+" "+i+": "+event.values[i]+"\n");
                    }
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    magnet.setText("");
                    for (int i=0 ; i<3 ; i++) {
                        magnet.append(event.sensor.getName()+" "+i+": "+event.values[i]+"\n");
                    }
                    break;

                case Sensor.TYPE_LIGHT:
                    light.setText("");
                    log(light, event.sensor.getName()+": "+event.values[0]+" lx.");
                    break;

                case Sensor.TYPE_ROTATION_VECTOR:
                    rot.setText("");
                    for (int i=0 ; i<event.values.length ; i++) {
                        rot.append(event.sensor.getName()+" "+i+": "+event.values[i]+"\n");
                    }
                    break;

                case Sensor.TYPE_PROXIMITY:
                    prox.setText("");
                    prox.append(event.sensor.getName()+": "+event.values[0]);
                    if(event.values[0] == 100) prox.append(" (far) \n");
                    else prox.append(" (near)\n");
                    break;

                case Sensor.TYPE_GYROSCOPE:
                    gyro.setText("");
                    for (int i=0 ; i<event.values.length ; i++) {
                        gyro.append(event.sensor.getName()+" "+i+": "+event.values[i]+"\n");
                    }
                    break;
                case Sensor.TYPE_LINEAR_ACCELERATION:
                    linacc.setText("");
                    for (int i=0 ; i<event.values.length ; i++) {
                        linacc.append(event.sensor.getName()+" "+i+": "+event.values[i]+"\n");
                    }
                    break;
                case Sensor.TYPE_GRAVITY:
                    grav.setText("");
                    for (int i=0 ; i<event.values.length ; i++) {
                        grav.append(event.sensor.getName()+" "+i+": "+event.values[i]+"\n");
                    }
                    break;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
