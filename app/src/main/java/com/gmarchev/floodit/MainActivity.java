package com.gmarchev.floodit;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.gmarchev.floodit.core.board.RandomColorBoardCreator;
import com.gmarchev.floodit.core.engine.GameEngine;
import com.gmarchev.floodit.core.engine.GameEngineImpl;
import com.gmarchev.floodit.core.engine.GameObserver;
import com.gmarchev.floodit.core.engine.GameState;
import com.gmarchev.floodit.core.strategy.FloodStrategyFactory;

public class MainActivity extends AppCompatActivity implements GameObserver {

    private final int rows = 10;
    private final int cols = 10;
    private View[][] board;

    private GameEngine gameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    @Override
    public void onGameStateUpdate(GameState gameState) {

        int[][] board = gameState.board();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.board[i][j].setBackgroundColor(board[i][j]);
            }
        }
    }
}
