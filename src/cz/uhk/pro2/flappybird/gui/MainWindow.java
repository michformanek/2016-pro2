package cz.uhk.pro2.flappybird.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import cz.uhk.pro2.flappybird.game.GameBoard;
import cz.uhk.pro2.flappybird.game.service.BoardLoader;
import cz.uhk.pro2.flappybird.game.service.CsvBoardLoader;

public class MainWindow extends JFrame {
	BoardPanel pnl = new BoardPanel();
	GameBoard gameBoard;
	long x = 0;

	// vnitrni trida
	class BoardPanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			gameBoard.drawAndDetectCollisions(g);

		}
	}

	public MainWindow() {
		try (InputStream inputStream = new FileInputStream("level.csv")) {
			BoardLoader boardLoader = new CsvBoardLoader(inputStream);
			gameBoard = boardLoader.getGameboard();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			gameBoard = new GameBoard();
		} catch (IOException e1) {
			e1.printStackTrace();
			gameBoard = new GameBoard();

		}
		add(pnl, BorderLayout.CENTER);

		pnl.setPreferredSize(new Dimension(200, 200));// TODO
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		gameBoard.setWidthPix(pnl.getWidth());

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					gameBoard.reset();
					x = 0;
				} else {
					gameBoard.kickTheBird();
				}
			}

		});

		Timer t = new Timer(20, e -> {// jak èasto se timer spouští v ms
			gameBoard.tick(x++);// promìná, která udržuje poèet tickù od zaèátku
			pnl.repaint();// refresh obrazovky
		});
		t.start();
	}

	public static void main(String[] args) {
		// díky swingUtilities jede gui ve vlastním vláknu
		SwingUtilities.invokeLater(() -> {
			MainWindow w = new MainWindow();
			w.setVisible(true);
		});

	}

}
