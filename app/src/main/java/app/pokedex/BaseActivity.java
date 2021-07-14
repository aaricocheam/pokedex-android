package app.pokedex;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import app.pokedex.network.PokemonLoader;
import app.pokedex.utils.DialogManager;

public class BaseActivity extends AppCompatActivity {
    public PokemonLoader loader;
    private ProgressDialog progress;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loader = new PokemonLoader();
        progress = new ProgressDialog(this);
    }

    public void showProgress() {
        progress.setCancelable(false);
        progress.setMessage("Cargando...");
        progress.show();
    }

    public void hideProgress() {
        if (progress.isShowing()) {
            progress.dismiss();
        }
    }

    public void showDialogError(){
        DialogManager manager = new DialogManager(BaseActivity.this,"ERROR", "Error del Servidor");
        Dialog dialog = manager.builDialog();
        dialog.show();
        manager.getBtDialog().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
