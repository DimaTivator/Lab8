package client.gui.graphics;

import commonModule.collectionClasses.HumanBeing;

import java.awt.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import static java.lang.Math.max;

public class HumanPanel extends JPanel {

    private int size;
    private Color color;
    private HumanBeing humanBeing;

    private int headDiameter;
    private int bodyLength;
    private int armLength;
    private int legLength;
    private int startX;
    private int startY;


    public HumanBeing getHumanBeing() {
        return humanBeing;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public HumanPanel(int size, HumanBeing humanBeing) {
        this.size = size;
        this.humanBeing = humanBeing;

        setPreferredSize(new Dimension(size, size*2));

        headDiameter = size / 4;
        bodyLength = size * 3 / 4;
        armLength = size / 2;
        legLength = size * 3 / 4;
        startX = size / 2;
        startY = size / 4;
    }


    private int currentY;

    private boolean isAnimationRunning = false;

    private Timer timer;


    public void startAnimation() {

        isAnimationRunning = true;

        timer = new Timer(max(1000 - (int)humanBeing.getImpactSpeed(), 50), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Изменение координаты Y человека и перерисовка панели
                setLocation(getX(), currentY);
                //repaint();
                currentY += 3;

                // Остановка анимации, если достигнута конечная координата Y
                if (currentY >= humanBeing.getCoordinates().getY()) {
                    stopAnimation();
                }
            }
        });
        timer.start();
    }


    public void stopAnimation() {
        isAnimationRunning = false;
        repaint();
        if (timer != null) {
            timer.stop();
            timer = null;
        }
    }


    private void drawParachute(Graphics g) {
        // Рисование парашюта
        int parachuteWidth = size;
        int parachuteHeight = size / 2 + 3;
        int parachuteStartX = startX - parachuteWidth / 2;
        int parachuteStartY = startY + headDiameter - parachuteHeight;


        g.setColor(Color.RED);
        g.fillArc(parachuteStartX, parachuteStartY, parachuteWidth, parachuteHeight, 0, 180);

        int stickLength = size / 2 + 8;
        int leftStickStartX = startX - parachuteWidth / 4;
        int rightStickStartX = startX + parachuteWidth / 4;
        int stickStartY = parachuteStartY + parachuteHeight / 2;

        g.drawLine(leftStickStartX, stickStartY, leftStickStartX, stickStartY + stickLength);
        g.drawLine(rightStickStartX, stickStartY, rightStickStartX, stickStartY + stickLength);
    }


    @Override
    protected void paintComponent(Graphics g) {
        //System.out.println(isAnimationRunning);

        super.paintComponent(g);

        // Рисование головы
        g.setColor(color);
        g.drawOval(startX - headDiameter/2, startY, headDiameter, headDiameter);

        // Рисование тела
        g.drawLine(startX, startY + headDiameter, startX, startY + headDiameter + bodyLength);

        // Рисование рук
        g.drawLine(startX, startY + headDiameter, startX - armLength/2, startY + headDiameter + armLength);
        g.drawLine(startX, startY + headDiameter, startX + armLength/2, startY + headDiameter + armLength);

        // Рисование ног
        g.drawLine(startX, startY + headDiameter + bodyLength, startX - legLength/2, startY + headDiameter + bodyLength + legLength);
        g.drawLine(startX, startY + headDiameter + bodyLength, startX + legLength/2, startY + headDiameter + bodyLength + legLength);

        if (isAnimationRunning) {
            drawParachute(g);
        }
    }
}

