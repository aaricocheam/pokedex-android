package app.pokedex;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import app.pokedex.network.PokeCallBack;
import app.pokedex.network.models.Pokemon;
import app.pokedex.network.models.PokemonListResponse;
import app.pokedex.pokemon.PokemonAdapter;
import app.pokedex.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokedexActivity extends BaseActivity {
    List<Pokemon> pokemonList;
    PokemonAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);

        EditText etPokeAdd = findViewById(R.id.etPokeAdd);
        Button btPokemonAdd = findViewById(R.id.btPokemonAdd);

        btPokemonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String poke = etPokeAdd.getText().toString();
                if (poke.isEmpty()) {
                    return;
                }
                Pokemon pokemon = new Pokemon(poke, "");
                pokemonList.add(0, pokemon);
                adapter.notifyDataSetChanged();
            }
        });

        final RecyclerView rvPokemonList = findViewById(R.id.rvPokemonList);

        Call<PokemonListResponse> call = loader.getPokemonList();

        call.enqueue(new PokeCallBack<PokemonListResponse>(PokedexActivity.this, true) {
            @Override
            public void onResponse(Call<PokemonListResponse> call, Response<PokemonListResponse> response) {
                super.onResponse(call, response);

                if (response.isSuccessful()) {
                    pokemonList = response.body().getPokemonList();
                    adapter = new PokemonAdapter(pokemonList, PokedexActivity.this);
                    rvPokemonList.setAdapter(adapter);
                    rvPokemonList.setHasFixedSize(true);
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(PokedexActivity.this);
                    rvPokemonList.setLayoutManager(manager);
                } else {
                    //Toast.makeText(PokedexActivity.this, "Fallo en el servidor", Toast.LENGTH_SHORT).show();
                    showDialogError();
                }
            }

            @Override
            public void onFailure(Call<PokemonListResponse> call, Throwable t) {
                super.onFailure(call, t);
                Log.e(Constant.DEBUG_POKEMON, t.getMessage());
                showDialogError();
            }
        });

        /*call.enqueue(new Callback<PokemonListResponse>() {
            @Override
            public void onResponse(Call<PokemonListResponse> call, Response<PokemonListResponse> response) {
                pokemonList = response.body().getPokemonList();
                adapter = new PokemonAdapter(pokemonList, PokedexActivity.this);
                rvPokemonList.setAdapter(adapter);
                rvPokemonList.setHasFixedSize(true);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(PokedexActivity.this);
                rvPokemonList.setLayoutManager(manager);
            }
            @Override
            public void onFailure(Call<PokemonListResponse> call, Throwable t) {
                Log.e(Constant.DEBUG_POKEMON, t.getMessage());
            }
        });*/
    }
}