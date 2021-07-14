package app.pokedex.game;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.pokedex.R;

public class GameViewHolder extends RecyclerView.ViewHolder {
    TextView tvGame;

    public GameViewHolder(@NonNull View v) {
        super(v);

        tvGame = v.findViewById(R.id.tvGame);
    }
}
