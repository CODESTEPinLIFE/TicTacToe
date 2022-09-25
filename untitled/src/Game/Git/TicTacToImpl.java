package Game.Git;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TicTacToImpl extends JComponent {
    public static final int FIELD_EMPTY = 0;
    public static final int FIELD_X = 10;
    public static final int FIELD_0 = 200;
    boolean GameLogic;
    int Field[][];

    void DrawGrid(Graphics graphics) {
        int w = getWidth();
        int h = getHeight();
        int dw = w / 3;
        int dh = h / 3;
        graphics.setColor(Color.blue);
        for (int i = 1; i < 3; i++) {
            graphics.drawLine(0, dh * i, w, dh * i);
            graphics.drawLine(dw * i, 0, dw * i, h);
        }

    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        DrawGrid(graphics);
        DrowXO(graphics);
    }

    public TicTacToImpl() {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        Field = new int[3][3];

    }

    void InitGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Field[i][j] = FIELD_EMPTY;
            }
        }
        GameLogic = true;
    }

    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            int x_bottom_mouse = mouseEvent.getX();
            int y_bottom_mouse = mouseEvent.getY();
            int I = (int) ((float) y_bottom_mouse / getHeight() * 3);
            int J = (int) ((float) x_bottom_mouse / getWidth() * 3);
            if (Field[I][J] == FIELD_EMPTY) {
                Field[I][J] = GameLogic ? FIELD_X : FIELD_0;
                GameLogic = !GameLogic;
                repaint();
                int rec = checkstate();
                if(rec!=0){
                    if(rec ==FIELD_X*3){
                        JOptionPane.showMessageDialog(this,"Крестики победили","Победа!",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if(rec ==FIELD_0*3){
                        JOptionPane.showMessageDialog(this,"Нолики победили","Победа!",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(this,"Ничья!","Ничья!",JOptionPane.INFORMATION_MESSAGE);
                    }
                    InitGame();
                    repaint();
                }
            }
        }
    }

    void DrowX(int j, int i, Graphics graghics) {
        graghics.setColor(Color.BLACK);
        int dx = getWidth() / 3;
        int dy = getHeight() / 3;
        int x = dx * i;
        int y = dy * j;
        graghics.drawLine(x, y, x + dx, y + dy);
        graghics.drawLine(x, y + dy, x + dx, y);
    }

    void DrowY(int j, int i, Graphics graghics) {
        graghics.setColor(Color.BLACK);
        int dx = getWidth() / 3;
        int dy = getHeight() / 3;
        int x = dx * i;
        int y = dy * j;
        graghics.drawOval(x, y, dx, dy);
    }

    void DrowXO(Graphics graghics) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (Field[i][j] == FIELD_X)
                    DrowX(i, j, graghics);
                else if (Field[i][j] == FIELD_0) {
                    DrowY(i, j, graghics);
                }
            }
        }
    }

    int checkstate() {
        int diag = 0;
        int diag2 = 0;
        for (int i = 0; i < 3; i++) {
            diag += Field[i][i];
            diag2 += Field[i][2 - i];
        }
        if (diag == FIELD_0 * 3 || diag == FIELD_X * 3) {
            return diag;
        }
        if (diag2 == FIELD_0 * 3 || diag2 == FIELD_X * 3) {
            return diag2;
        }
        int check_i,check_j;
        boolean hasEmpty = false;
        for (int i = 0; i < 3; i++){
            check_i = 0;
            check_j = 0;
            for (int j = 0; j < 3; j++){
                if(Field[i][j]==0){
                    hasEmpty = true;
                }
                check_i+=Field[i][j];
                check_j+=Field[j][i];
            }
            if(check_i==FIELD_0*3||check_i==FIELD_0*3){
                return check_i;
            }
            if(check_j==FIELD_0*3||check_j==FIELD_0*3){
                return check_j;
            }
        }
        if(hasEmpty) return 0;else return -1;
    }

}
