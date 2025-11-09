package com.gmarchev.floodit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.gmarchev.floodit.core.board.RandomColorBoardCreator;
import com.gmarchev.floodit.core.engine.GameEngine;
import com.gmarchev.floodit.core.engine.GameEngineImpl;
import com.gmarchev.floodit.core.engine.GameObserver;
import com.gmarchev.floodit.core.engine.GameState;
import com.gmarchev.floodit.core.engine.InvalidInputException;
import com.gmarchev.floodit.core.strategy.FloodStrategyFactory;

public class MainActivity extends AppCompatActivity implements GameObserver {

    private final int rows = 10;

    private final int cols = 10;

    private TextView moveCounter;

    private View[][] board;

    private GameEngine gameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moveCounter = findViewById(R.id.move_counter);

        int[] colors = new int[] {
                ContextCompat.getColor(this, R.color.purple),
                ContextCompat.getColor(this, R.color.teal),
                ContextCompat.getColor(this, R.color.green),
                ContextCompat.getColor(this, R.color.salmon),
                ContextCompat.getColor(this, R.color.pink),
                ContextCompat.getColor(this, R.color.blue)
        };

        createBoard();

        startGameEngine(colors);

        createColorButtons(colors);
    }

    private void createBoard() {

        GridLayout grid = findViewById(R.id.board);
        grid.setRowCount(rows);
        grid.setColumnCount(cols);
        View[][] board = new View[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                View cell = new View(this);

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 0;
                params.height = 0;
                params.rowSpec = GridLayout.spec(i, 1f);
                params.columnSpec = GridLayout.spec(j, 1f);

                cell.setLayoutParams(params);

                grid.addView(cell);
                board[i][j] = cell;
            }
        }

        this.board = board;
    }

    private void startGameEngine(int[] colors) {

        this.gameEngine = new GameEngineImpl(
                RandomColorBoardCreator.create(rows, cols, colors),
                FloodStrategyFactory.create(rows, cols));
        this.gameEngine.addObserver(this);
        this.gameEngine.start();
    }

    private void createColorButtons(int[] colors) {
        LinearLayout buttonsLayout = findViewById(R.id.buttons_layout);

        for (int color : colors) {
            Button button = new Button(this);
            button.setBackgroundColor(color);
            button.setOnClickListener(v -> {
                try {
                    gameEngine.flood(color);
                } catch (InvalidInputException e) {
                    // A more user-friendly error handling can be added here
                    e.printStackTrace();
                }
            });

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
            params.setMargins(16, 16, 16, 16);
            button.setLayoutParams(params);

            buttonsLayout.addView(button);
        }
    }

    @Override
    public void onGameStateUpdate(GameState gameState) {

        moveCounter.setText(getString(R.string.moves_format, gameState.moveCount()));

        int[][] board = gameState.board();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.board[i][j].setBackgroundColor(board[i][j]);
            }
        }
    }
}
