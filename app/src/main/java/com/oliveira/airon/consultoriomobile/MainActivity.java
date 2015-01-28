package com.oliveira.airon.consultoriomobile;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        Log.d("insert: ", "inserting..");
        db.addCliente(new Cliente("Airon Cavalcanti de Oliveira","342.024.488-63", Sexo.masculino, "17/06/1986", "+55 11 96323-1051", "airon_oliveira@hotmail.com", "churrosOnline123!@#"));
        db.addCliente(new Cliente("Rosilene Silva Paiva","217.879.318-54", Sexo.feminino, "01/07/1983", "+55 11 99594-1289", "rosi.ap83@gmail.com", "bob123!@#"));

        Log.d("Reading: ", "Reading all contacts..");
        List<Cliente> clientes = db.getAllClientes();

        for(Cliente c : clientes){
            String log = "Nome Completo:" + c.getNomeCompleto() +
                    "CPF:" + c.getCpf() +
                    "Sexo:" + c.getSexo() +
                    "Data Nascimento:" + c.getDataNascimento() +
                    "Celular:" + c.getCelular() +
                    "E-mail:" + c.getEmail() +
                    "Senha:" + c.getSenha();
            Log.d ("nome: ", log);
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
