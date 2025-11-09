package com.gmarchev.floodit;

import android.app.AlertDialog;
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

    private final int ROWS = 10;

    private final int COLS = ROWS;

    private int[] colors;

    private TextView moveCounter;

    private View[][] board;

    private GameEngine gameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.colors = new int[] {
                ContextCompat.getColor(this, R.color.purple),
                ContextCompat.getColor(this, R.color.teal),
                ContextCompat.getColor(this, R.color.green),
                ContextCompat.getColor(this, R.color.salmon),
                ContextCompat.getColor(this, R.color.pink),
                ContextCompat.getColor(this, R.color.blue)
        };

        this.moveCounter = findViewById(R.id.move_counter);

        createBoard();

        startGameEngine(this.colors);

        createColorButtons(this.colors);

        Button newGameButton = findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(v -> startGameEngine(this.colors));

        Button undoMoveButton = findViewById(R.id.undo_move_button);
        undoMoveButton.setOnClickListener(v ->gameEngine.undo());
    }

    private void createBoard() {

        GridLayout grid = findViewById(R.id.board);
        grid.setRowCount(ROWS);
        grid.setColumnCount(COLS);
        View[][] board = new View[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {

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
                RandomColorBoardCreator.create(ROWS, COLS, colors),
                FloodStrategyFactory.create(ROWS, COLS));
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

                    showNewGameDialog(R.string.error_title, R.string.error_message);
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

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {

                this.board[i][j].setBackgroundColor(board[i][j]);
            }
        }

        if (gameState.isComplete()) {

            showNewGameDialog(R.string.won_game_title, R.string.won_game_message);
        }
    }

    private void showNewGameDialog(int titleId, int messageId) {
        new AlertDialog.Builder(this)
                .setTitle(titleId)
                .setMessage(messageId)
                .setPositiveButton(
                        R.string.new_game,
                        (dialog, which) -> startGameEngine(this.colors))
                .setCancelable(false)
                .show();
    }
}
