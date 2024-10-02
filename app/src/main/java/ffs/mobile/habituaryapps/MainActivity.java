package ffs.mobile.habituaryapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btAddHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = this.findViewById(R.id.textView);
        TextView textView2 = this.findViewById(R.id.textView2);
        TextView textView3 = this.findViewById(R.id.textView3);
        TextView textView4 = this.findViewById(R.id.textView4);

        Intent i = new Intent(this.getIntent());
        textView.setText(i.getStringExtra("yourGoal"));
        textView2.setText(i.getStringExtra("habitName"));
        textView3.setText(i.getStringExtra("period"));
        textView4.setText(i.getStringExtra("habitType"));

        this.btAddHabit = this.findViewById(R.id.AddHabit);
        this.btAddHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddHabit.class);
                startActivity(i);
            }
        });


    }
}