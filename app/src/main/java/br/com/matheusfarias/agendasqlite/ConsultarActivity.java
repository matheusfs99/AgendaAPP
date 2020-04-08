package br.com.matheusfarias.agendasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ConsultarActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        DBController crud = new DBController(getBaseContext());
        final Cursor cursor = crud.buscarTodos();

        String[] nomeCampos = new String[]{"_id", "nome", "fone"};

        int[] idViews = new int[]{R.id.idContato, R.id.nomeContato, R.id.foneContato};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.contatos_layout, cursor, nomeCampos, idViews);

        lista = findViewById(R.id.ListView);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
                Intent intent = new Intent(ConsultarActivity.this, AlterarActivity.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });

        Button sair = findViewById(R.id.btnSair);
        Button cadastrar = findViewById(R.id.btnCadContato);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarActivity.this, InserirActivity.class);
                startActivity(intent);
            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
