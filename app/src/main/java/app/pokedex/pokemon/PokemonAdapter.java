package app.pokedex.pokemon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.pokedex.PokemonDetailActivity;
import app.pokedex.R;
import app.pokedex.network.models.Pokemon;
import app.pokedex.utils.Constant;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {
    private List<Pokemon> pokemonList;
    Context ctx;

    public PokemonAdapter(List<Pokemon> pokemonList, Context ctx) {
        this.pokemonList = pokemonList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon, parent, false);
        return new PokemonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        holder.tvPokemonName.setText(pokemonList.get(holder.getAdapterPosition()).getName());

        holder.tvPokemonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pokemonId = pokemonList.get(position).getName();

                Intent intent = new Intent(ctx, PokemonDetailActivity.class);
                intent.putExtra(Constant.EXTRA_POKEMON_ID, pokemonId);
                ctx.startActivity(intent);
            }
        });

        holder.btPokemonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pokemonList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }
}
