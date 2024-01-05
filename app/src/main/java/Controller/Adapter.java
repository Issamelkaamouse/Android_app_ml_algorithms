package Controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapp.EditData;
import com.example.androidapp.R;
import com.example.androidapp.Recycler;

import java.util.List;

import Model.MachineLearning;
import Utils.Utils;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private Context context;
    private List<MachineLearning> machineLearningList;
    private DataBaseHandler dataBaseHandler;
    private int selectedPosition = RecyclerView.NO_POSITION;



    // Constructor for the Adapter
    public Adapter(Context context, List<MachineLearning> machineLearningList, DataBaseHandler dataBaseHandler) {
        this.context = context;
        this.machineLearningList = machineLearningList;
        this.dataBaseHandler = dataBaseHandler;
    }

    // Getter for the selected position
    public int getSelectedPosition() {
        return selectedPosition;
    }




    // ViewHolder class representing the UI elements in each row of the RecyclerView
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mpg, displacement, horsePower, weight, acceleration, origin;
        public ImageView delete, edit;

        // Constructor for the ViewHolder
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize UI elements
            mpg = itemView.findViewById(R.id.mpg);
            displacement = itemView.findViewById(R.id.displacement);
            horsePower = itemView.findViewById(R.id.horsePower);
            weight = itemView.findViewById(R.id.weight);
            acceleration = itemView.findViewById(R.id.acceleration);
            origin = itemView.findViewById(R.id.origine);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    // Method to create a new ViewHolder instance
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each row of the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_content, parent, false);
        return new MyViewHolder(view);
    }

    // Method to bind data to each ViewHolder
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Get the MachineLearning object for the current position
        MachineLearning machineLearning = machineLearningList.get(position);

        // Set text for each UI element in the ViewHolder
        holder.mpg.setText("MPG: " + String.valueOf(machineLearning.getMpg()));
        holder.displacement.setText("Displacement: " + String.valueOf(machineLearning.getDisplacement()));
        holder.horsePower.setText("Horsepower: " + String.valueOf(machineLearning.getHorsePower()));
        holder.weight.setText("Weight: " + String.valueOf(machineLearning.getWeight()));
        holder.acceleration.setText("Acceleration: " + String.valueOf(machineLearning.getAcceleration()));
        holder.origin.setText("Origin: " + machineLearning.getOrigin());

        // Add click listeners for the edit and delete operations
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Get the selected MachineLearning object
                MachineLearning selectedMachineLearning = machineLearningList.get(position);

                // Create a custom dialog
                Dialog myPopup = new Dialog(context);
                myPopup.setContentView(R.layout.popup_edit_data);
                myPopup.getWindow().setBackgroundDrawableResource(R.drawable.border_radius);

                // Find the EditText views in the layout
                EditText editTextMpgE = myPopup.findViewById(R.id.editTextMpgE);
                EditText editTextDispE = myPopup.findViewById(R.id.editTextDispE);
                EditText editTextHorsPE = myPopup.findViewById(R.id.editTextHorsPE);
                EditText editTextWeiE = myPopup.findViewById(R.id.editTextWeiE);
                EditText editTextAccE = myPopup.findViewById(R.id.editTextAccE);
                EditText editTextOrE = myPopup.findViewById(R.id.editTextOrE);
                Button savedBtn = myPopup.findViewById(R.id.button2E);

                // Set the text values based on the selectedMachineLearning object
                editTextMpgE.setText(String.valueOf(selectedMachineLearning.getMpg()));
                editTextDispE.setText(String.valueOf(selectedMachineLearning.getDisplacement()));
                editTextHorsPE.setText(String.valueOf(selectedMachineLearning.getHorsePower()));
                editTextWeiE.setText(String.valueOf(selectedMachineLearning.getWeight()));
                editTextAccE.setText(String.valueOf(selectedMachineLearning.getAcceleration()));
                editTextOrE.setText(selectedMachineLearning.getOrigin());

                // Show the dialog
                myPopup.show();

                savedBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Update the selectedMachineLearning object with the new values
                        selectedMachineLearning.setMpg(Integer.parseInt(editTextMpgE.getText().toString()));
                        selectedMachineLearning.setDisplacement(Integer.parseInt(editTextDispE.getText().toString()));
                        selectedMachineLearning.setHorsePower(Integer.parseInt(editTextHorsPE.getText().toString()));
                        selectedMachineLearning.setWeight(Integer.parseInt(editTextWeiE.getText().toString()));
                        selectedMachineLearning.setAcceleration(Integer.parseInt(editTextAccE.getText().toString()));
                        selectedMachineLearning.setOrigin(editTextOrE.getText().toString());

                        // Update the data in the database
                        DataBaseHandler dataBaseHandler = new DataBaseHandler(context, Utils.DATABASE_NAME, null, Utils.DATABASE_VERSION);
                        dataBaseHandler.updateData(selectedMachineLearning.getId(), selectedMachineLearning);

                        // Close the dialog
                        myPopup.dismiss();
                        Intent intent = new Intent(context, Recycler.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        context.startActivity(intent);
                    }
                });



                // Create an intent to start the EditData activity
//                Intent intent = new Intent(context, EditData.class);
//                intent.putExtra("machineLearning",selectedMachineLearning);
//                context.startActivity(intent);
            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show a confirmation dialog before deleting
                showDeleteConfirmationDialog(position);
            }
        });
    }

    // Method to display a delete confirmation dialog
    private void showDeleteConfirmationDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Voulez-vous supprimer cet élément ?");
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Call the method to delete the item from the database
                long itemId = machineLearningList.get(position).getId();
                dataBaseHandler.deleteData(itemId);

                // Update the list in the activity
                machineLearningList = dataBaseHandler.getAllData();

                // Inform the adapter of the changes
                setData(machineLearningList);
                notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Non", null);
        builder.show();
    }

    // Method to get the item count in the RecyclerView
    @Override
    public int getItemCount() {
        return machineLearningList.size();
    }


    // Method to update the data set in the adapter
    public void setData(List<MachineLearning> newData) {
        this.machineLearningList = newData;
    }
}
