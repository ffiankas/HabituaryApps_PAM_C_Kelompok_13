package ffs.mobile.habituaryapps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import ffs.mobile.habituaryapps.model.YourGoals;

public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.VH> { // Ubah untuk menggunakan Generic Type
    private final Context ctx;
    private List<YourGoals> goalsList;
    public YourGoals goalsDelete;

    public GoalsAdapter(Context ctx) {
        this.ctx = ctx;
        goalsList = new ArrayList<>();
    }

    public void setGoals(List<YourGoals> goals) {
        this.goalsList = goals;
        notifyDataSetChanged();
    }

    public class VH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvGoalName;
        private final TextView tvPeriodList;
        private final TextView tvHabitTypeList;
        private final ImageView ivGoals;
        private final ImageView btDelete;
        private YourGoals goals;

        public VH(@NonNull View itemView) {
            super(itemView);
            this.tvGoalName = itemView.findViewById(R.id.tvGoalName);
            this.tvPeriodList = itemView.findViewById(R.id.tvPeriodList);
            this.tvHabitTypeList = itemView.findViewById(R.id.tvHabitTypeList);
            this.ivGoals = itemView.findViewById(R.id.ivGoals);
            this.btDelete = itemView.findViewById(R.id.ivDelete);
            this.btDelete.setOnClickListener(view -> {
                Toast.makeText(ctx, tvGoalName.getText() + " dihapus", Toast.LENGTH_SHORT).show();
                goalsList.remove(goals);
                notifyDataSetChanged();
            });
            itemView.setOnClickListener(this);
        }

        private void setGoalsDipilih(YourGoals goals) {
            this.goals = goals;
            if (this.goals != null && this.goals.goalsDipilih)
                Toast.makeText(ctx, tvGoalName.getText() + " dipilih", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onClick(View view) {
            for (YourGoals g : goalsList)
                g.goalsDipilih = false;

            if (this.goals != null) {
                this.goals.goalsDipilih = !this.goals.goalsDipilih;
                goalsDelete = this.goals.goalsDipilih ? this.goals : null;
                notifyDataSetChanged();
            }
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listGoals = LayoutInflater.from(this.ctx).inflate(R.layout.your_goals, parent, false);
        return new VH(listGoals);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        YourGoals goals = this.goalsList.get(position);
        holder.tvGoalName.setText(goals.getNama());
        holder.tvPeriodList.setText(goals.getPeriode());
        holder.tvHabitTypeList.setText(goals.getTipe());
        Picasso.get().load(goals.getImgUrl())
                .placeholder(R.drawable.baseline_close_24)
                .error(R.drawable.baseline_close_24)
                .into(holder.ivGoals);
        holder.setGoalsDipilih(goals);
    }

    @Override
    public int getItemCount() {
        return this.goalsList.size();
    }
}
