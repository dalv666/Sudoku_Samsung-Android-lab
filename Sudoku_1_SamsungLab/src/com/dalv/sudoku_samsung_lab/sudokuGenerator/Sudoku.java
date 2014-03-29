package com.dalv.sudoku_samsung_lab.sudokuGenerator;

import java.util.Random;


public class Sudoku {
	private Random random = new Random();
	private int mGridTable[][];
	private int n = 3;

	public int[][] getGridTable() {
		return mGridTable;
	}

	public void makeGrid() {
		this.mGridTable = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				mGridTable[i][j] = (i * n + i / n + j) % (n * n) + 1;
			}
		}
	}

	public void show() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(mGridTable[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void transposing() {
		for (int i = 0; i < n * n; i++) {
			for (int j = i; j < n * n; j++) {
				int temp = mGridTable[i][j];
				mGridTable[i][j] = mGridTable[j][i];
				mGridTable[j][i] = temp;
			}
		}
	}

	public void swapRowsSmall() {
		int area = random.nextInt(n);
		int line1 = random.nextInt(n);
		// получение случайного района и случайной строки
		int numOfLine1 = area * n + line1;
		// номер 1 строки для обмена
		int line2 = random.nextInt(n);
		while (line1 == line2) {
			line2 = random.nextInt(n);
		}
		int numOfLine2 = area * n + line2;
		// номер 2 строки для обмена

		changeLines(numOfLine1, numOfLine2);
	}

	public void changeLines(int from, int to) {
		for (int i = 0; i < 9; i++) {
			int temp = mGridTable[from][i];
			mGridTable[from][i] = mGridTable[to][i];
			mGridTable[to][i] = temp;
		}

	}

	public void swapColumsSmall() {
		// Для обмена столбцов можно поменять строки у транспонированной таблицы
		transposing();
		swapRowsSmall();
		transposing();
	}

	public void swapRowsArea() {
		int area1 = random.nextInt(n);
		// получение случайного района
		int area2 = random.nextInt(n);
		while (area1 == area2) {
			area2 = random.nextInt(n);
		}

		for (int i = 0; i < n; i++) {
			int numOfLine1 = area1 * n + i;
			int numOfLine2 = area2 * n + i;
			changeLines(numOfLine1, numOfLine2);
		}

	}

	public void swapColumsArea() {
		transposing();
		swapRowsArea();
		transposing();
	}

	public void sudokuGenerator() {
		int i = random.nextInt(10);
		int j = random.nextInt(10);
		int k = random.nextInt(10);
		int n = random.nextInt(2);
		for (i = i; i > 0; i--) {
			swapColumsArea();
			for (n = random.nextInt(2); n > 0; n--) {
				transposing();
			}
			for (j = j; j > 0; j--) {
				swapRowsArea();
				for (n = random.nextInt(2); n > 0; n--) {
					transposing();
				}
				for (k = k; k > 0; k--) {
					swapColumsSmall();
					for (n = random.nextInt(2); n > 0; n--) {
						transposing();
					}
					for (n = random.nextInt(2); n > 0; n--) {
						swapRowsSmall();
						for (n = random.nextInt(2); n > 0; n--) {
							transposing();
						}
					}
				}
			}
		}
	}

	public int[][] getGridSudokuByHard(int diffucult) {
		makeGrid();
		sudokuGenerator();
		int beginDiffucult = 0;
		int checkedPosition[][] = new int[9][9];
		while (beginDiffucult != diffucult) {
			int xRandomPosition = random.nextInt(9);
			int yRandomPosition = random.nextInt(9);
			if (checkedPosition[xRandomPosition][yRandomPosition] == 0) {
				// не проверяли
				int temp = mGridTable[xRandomPosition][yRandomPosition];
				mGridTable[xRandomPosition][yRandomPosition] = 0;
				beginDiffucult++;
				checkedPosition[xRandomPosition][yRandomPosition] = temp;
			}
		}
		return this.mGridTable;
	}
}
