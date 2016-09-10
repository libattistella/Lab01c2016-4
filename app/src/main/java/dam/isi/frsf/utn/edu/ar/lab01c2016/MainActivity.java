package dam.isi.frsf.utn.edu.ar.lab01c2016;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    int dias;
    SeekBar barra;
    Button boton;
    EditText importe,email,cuit;
    TextView aceptado, campoDias, campoResultado;
    float dinero;
    float resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barra=(SeekBar) findViewById(R.id.Barra);
        barra.setOnSeekBarChangeListener(this);
        boton= (Button) findViewById(R.id.button);
        boton.setOnClickListener(this);
        importe = (EditText) findViewById(R.id.Importe);
        aceptado = (TextView) findViewById(R.id.Aceptado);
        email= (EditText) findViewById(R.id.Email);
        cuit=  (EditText) findViewById(R.id.Cuit);
        campoDias=(TextView) findViewById(R.id.Dias);
        campoResultado= (TextView) findViewById(R.id.Resultado);

    }







    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        campoDias.setText("" + progress);


    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        if(importe.getText().toString().isEmpty()) {
            aceptado.setTextColor(getResources().getColor(R.color.error));
            aceptado.setText("Importe no especificado");
        }
        else {
            dias = seekBar.getProgress();

            dinero = Float.parseFloat(importe.getText().toString());
            resultado = monto(dinero);


            campoResultado.setText(String.valueOf(resultado));
        }
    }



    float monto(float dinero){


        Float a = Float.parseFloat(getResources().getString(R.string.tasa1));
        Float b = Float.parseFloat(getResources().getString(R.string.tasa2));
        Float c = Float.parseFloat(getResources().getString(R.string.tasa3));
        Float d = Float.parseFloat(getResources().getString(R.string.tasa4));
        Float e = Float.parseFloat(getResources().getString(R.string.tasa5));
        Float f = Float.parseFloat(getResources().getString(R.string.tasa6));


        if ((dinero>0)&(dinero<5000)&(dias<30))
        return interes(dinero, a);
        else if ((dinero>0)&(dinero<5000)&(dias>=30))
        return interes(dinero,b);
        else if ((dinero>=5000)&(dinero<99999)&(dias<30))
        return interes(dinero, c);
        else if ((dinero>=5000)&(dinero<99999)&(dias>=30))
        return    interes(dinero, d);
        else if ((dinero>=99999)& (dias<30))
        return    interes(dinero, e);
        else if ((dinero>=99999)& (dias>=30))
        return    interes(dinero, f);
        else return 0;

     }


    float interes (float dinero, float tasa){

        return (dinero*((float)((Math.pow(((double)(1+(tasa/100.0))),(double)(dias/360.0)))-1)));

    }


    @Override
    public void onClick(View view) {

        if(email.getText().toString().isEmpty()) {
            aceptado.setTextColor(getResources().getColor(R.color.error));
            aceptado.setText("Email no especificado");
        }
        else if(cuit.getText().toString().isEmpty()){
            aceptado.setTextColor(getResources().getColor(R.color.error));
            aceptado.setText("Cuit no especificado");
        }
    else if(importe.getText().toString().isEmpty()) {
        aceptado.setTextColor(getResources().getColor(R.color.error));
        aceptado.setText("Importe no especificado");
    }
        else if((campoDias.getText().toString().isEmpty()) || (campoDias.getText().toString().equals("0")) ){
            aceptado.setTextColor(getResources().getColor(R.color.error));
            aceptado.setText("Cantidad de días no especificada");
        }

        else {aceptado.setTextColor(getResources().getColor(R.color.correcto));
            aceptado.setText("Plazo fijo Realiazdo. Recibirá " + String.valueOf(resultado) + " al vencimiento");}








    }
}
