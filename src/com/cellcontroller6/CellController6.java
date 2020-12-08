package com.cellcontroller6;

import com.auxiliares.Auxiliares;

import totalcross.ui.Grid;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;

public class CellController6 extends Grid.CellController {
    public CellController6() {
    }

    public int getBackColor(int row, int col) {
        int resto = row % 2;
        if (resto == 0) {
            return Auxiliares.firstStripeColorNovo;
        } else {
            return Auxiliares.secondStripeColorNovo;
        }

    }

    public int getForeColor(int row, int col) {

            return Color.BLACK;
            
    }

    public String[] getChoices(int row, int col) {
        return null;
    }

    public boolean isEnabled(int row, int col) {
        return true;
    }

    public Font getFont(int row, int col) {
        return Auxiliares.getFontGrid();
    }
}