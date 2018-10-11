package com.example.sergio.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mWebsiteEdiText;
    private EditText mLocationEditText;//m es para los globales de toda la actividad o clase
    //non public non static field names start with m , por convencion ver reglas de estilo!!
    private EditText mShareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEdiText= findViewById(R.id.website_edittext);
        mLocationEditText=findViewById(R.id.openlocation_edittext);
        mShareTextEditText=findViewById(R.id.sharetext_edittext);
    }
    public void openWebsite (View view){
        //guardar una url
        String url=mWebsiteEdiText.getText().toString();//lo de dentor de edittext estipo text , hay que convertirlo a string
        //hacer un parse
        Uri webpage=Uri.parse(url);// tipo uri.. para convertir de string a uri elmetodo parse
        //action view es la acc para ver esos datos en una pagina web
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);//
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }  else {
            Log.d("ImplicitIntents", "No hay aplicacion para abrirlo");
        }

    }

    public void openLocation(View view) {
    //string que recoja el texto
        String loc=mLocationEditText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc); //q porque empieza una query
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }  else {
            Log.d("ImplicitIntents", "No hay aplicacion para abrirlo");
        }
    }

    public void shareText(View view) {
        String txt = mShareTextEditText.getText().toString();
        String mimeType = "text/plain";//lo que vamos a pasar
        ShareCompat.IntentBuilder// van todos detras pero se pone asi xq no se ve
                .from(this)
                .setType(mimeType)// podria ser ubicacion geo
                .setChooserTitle("Compartir este texto con: ")
                .setText(txt)
                .startChooser();
    }



}
