package gatomanjuarez.manejomemoria;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void generarArchivo(View v){
        try{
            //Declaracion de edittext.
            EditText editnombre = (EditText) findViewById(R.id.edtNombre);
            String nombre = editnombre.getText().toString();

            FileOutputStream outputStream = null;
            outputStream = openFileOutput("MiArchivo.txt", Context.MODE_APPEND);
            outputStream.write(nombre.getBytes());
            outputStream.close();

            Toast.makeText(MainActivity.this, "El archivo se ha creado.", Toast.LENGTH_SHORT).show();
            //Poner en blanco.
            editnombre.setText("");
        }
        catch (Exception e){
            //Identificar el error para nosotros.
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Error en la escritura en el archivo.", Toast.LENGTH_SHORT).show();
        }
    }

    //Preferencia
    public void guardarPreferencia(View v){
        SharedPreferences miPreferencia = getSharedPreferences("MisDatosPersonales",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = miPreferencia.edit();
        EditText edtNombre = (EditText) findViewById(R.id.edtNombre);
        EditText edtCorreo = (EditText) findViewById(R.id.edtCorreo);

        String nombre = edtNombre.getText().toString();
        String correo = edtCorreo.getText().toString();

        //               Clave   , Valor
        editor.putString("Nombre", nombre);
        editor.putString("Correo", correo);
        editor.commit();

        Toast.makeText(MainActivity.this, "Se ha creado la preferencia compartida.", Toast.LENGTH_SHORT).show();
        edtCorreo.setText("");
        edtNombre.setText("");
    }

    public void mostrarPreferencia(){
        SharedPreferences miPreferencia = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);

        //                                       Clave a buscar, retorna en caso de no tenerlo.
        String nombre = miPreferencia.getString("Nombre", "No existe ese dato.");
        String correo = miPreferencia.getString("Correo", "No existe ese dato.");

        TextView preferenciaCompartida = (TextView) findViewById(R.id.tvPreferenciaCompartida);
        String preferencia = "\nNombre: "+ nombre + "\nCorreo: "+ correo;
        preferenciaCompartida.setText(preferencia);
    }
}
