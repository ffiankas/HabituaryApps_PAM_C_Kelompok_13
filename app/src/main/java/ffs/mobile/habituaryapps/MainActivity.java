package ffs.mobile.habituaryapps;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import ffs.mobile.habituaryapps.api.ApiService;
import ffs.mobile.habituaryapps.api.RetrofitClient;
import ffs.mobile.habituaryapps.model.ApiResponse;
import ffs.mobile.habituaryapps.model.YourGoals;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private GoalsAdapter goalsAdapter;
    private RecyclerView rvGoals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.rvGoals = findViewById(R.id.rvGoals);

        goalsAdapter = new GoalsAdapter(this);
        rvGoals.setLayoutManager(new LinearLayoutManager(this));
        rvGoals.setAdapter(goalsAdapter);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        apiService.getGoals().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<YourGoals> goalsList = response.body().getGoals();
                    if (goalsList != null) { // Null check untuk mencegah NullPointerException
                        goalsAdapter.setGoals(goalsList);
                    } else {
                        Toast.makeText(MainActivity.this, "Data goals kosong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Gagal memuat data goals", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//penambahan fragment_trasanction (rachel)
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_progress_bar, new ProgressBarFragment());
        transaction.replace(R.id.fragment_container_today_habit, new TodayHabitFragment());
        transaction.replace(R.id.fragment_container_your_goals, new YourGoalsFragment());
        transaction.commit();
    }
}
