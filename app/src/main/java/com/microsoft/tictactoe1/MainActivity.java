package com.microsoft.tictactoe1;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.microsoft.tictactoe1.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    final List<int[]> combinationList = new ArrayList<>();
    int[] boxPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0}; // 9 zeros
    int playerTurn = 1;
    int totalSelectedBoxes = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Retrieve player names from the Intent
        String playerOneName = getIntent().getStringExtra("playerOne");
        String playerTwoName = getIntent().getStringExtra("playerTwo");

        // Set player names to the TextViews
        binding.playerOneName.setText(playerOneName);
        binding.playerTwoName.setText(playerTwoName);

        combinationList.add(new int[]{0, 1, 2});
        combinationList.add(new int[]{3, 4, 5});
        combinationList.add(new int[]{6, 7, 8});
        combinationList.add(new int[]{0, 3, 6});
        combinationList.add(new int[]{1, 4, 7});
        combinationList.add(new int[]{2, 5, 8});
        combinationList.add(new int[]{2, 4, 6});
        combinationList.add(new int[]{0, 4, 8});

        binding.image1.setOnClickListener(view -> {
            if (isBoxSelectable(0)) {
                performAction((ImageView) view, 0);
            }
        });

        binding.image2.setOnClickListener(view -> {
            if (isBoxSelectable(1)) {
                performAction((ImageView) view, 1);
            }
        });
        binding.image3.setOnClickListener(view -> {
            if (isBoxSelectable(2)) {
                performAction((ImageView) view, 2);
            }
        });
        binding.image4.setOnClickListener(view -> {
            if (isBoxSelectable(3)) {
                performAction((ImageView) view, 3);
            }
        });
        binding.image5.setOnClickListener(view -> {
            if (isBoxSelectable(4)) {
                performAction((ImageView) view, 4);
            }
        });
        binding.image6.setOnClickListener(view -> {
            if (isBoxSelectable(5)) {
                performAction((ImageView) view, 5);
            }
        });
        binding.image7.setOnClickListener(view -> {
            if (isBoxSelectable(6)) {
                performAction((ImageView) view, 6);
            }
        });
        binding.image8.setOnClickListener(view -> {
            if (isBoxSelectable(7)) {
                performAction((ImageView) view, 7);
            }
        });
        binding.image9.setOnClickListener(view -> {
            if (isBoxSelectable(8)) {
                performAction((ImageView) view, 8);
            }
        });
    }

    private void performAction(ImageView imageView, int selectedBoxPosition) {
        boxPositions[selectedBoxPosition] = playerTurn;
        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.ximage);
            if (checkResults()) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, binding.playerOneName.getText().toString()
                        + " is the Winner!", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else if (totalSelectedBoxes == 9) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, "Match Draw", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
        } else {
            imageView.setImageResource(R.drawable.oimage);
            if (checkResults()) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, binding.playerTwoName.getText().toString()
                        + " is the Winner!", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else if (totalSelectedBoxes == 9) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, "Match Draw", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                changePlayerTurn(1);
                totalSelectedBoxes++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn = currentPlayerTurn;
        if (playerTurn == 1) {
            binding.playerOneLayout.setBackgroundResource(R.drawable.black_border);
            binding.playerTwoLayout.setBackgroundResource(R.drawable.white_box);
        } else {
            binding.playerTwoLayout.setBackgroundResource(R.drawable.black_border);
            binding.playerOneLayout.setBackgroundResource(R.drawable.white_box);
        }
    }

    private boolean isBoxSelectable(int boxPosition) {
        return boxPositions[boxPosition] == 0;
    }

    private boolean checkResults() {
        boolean response = false;
        for (int[] combination : combinationList) {
            if (boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn &&
                    boxPositions[combination[2]] == playerTurn) {
                response = true;
            }
        }
        return response;
    }

    public void restartMatch() {
        boxPositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}; // 9 zeros
        playerTurn = 1;
        totalSelectedBoxes = 1;
        binding.image1.setImageResource(0);
        binding.image2.setImageResource(0);
        binding.image3.setImageResource(0);
        binding.image4.setImageResource(0);
        binding.image5.setImageResource(0);
        binding.image6.setImageResource(0);
        binding.image7.setImageResource(0);
        binding.image8.setImageResource(0);
        binding.image9.setImageResource(0);
    }
}
