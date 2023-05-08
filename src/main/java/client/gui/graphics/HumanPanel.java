package client.gui.graphics;

import commonModule.collectionClasses.HumanBeing;

import java.awt.*;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class HumanPanel extends JPanel {

    private int size;
    private Color color;
    private HumanBeing humanBeing;

    public HumanBeing getHumanBeing() {
        return humanBeing;
    }

    public void setHumanBeing(HumanBeing humanBeing) {
        this.humanBeing = humanBeing;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public HumanPanel(int size) {
        this.size = size;
        setPreferredSize(new Dimension(size, size*2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int headDiameter = size / 4;
        int bodyLength = size * 3 / 4;
        int armLength = size / 2;
        int legLength = size * 3 / 4;
        int startX = size / 2;
        int startY = size / 4;

        // Рисование головы
        g.setColor(color);
        g.drawOval(startX - headDiameter/2, startY, headDiameter, headDiameter);

        // Рисование тела
        g.drawLine(startX, startY + headDiameter, startX, startY + headDiameter + bodyLength);

        // Рисование рук
        g.drawLine(startX, startY + headDiameter + armLength/2, startX - armLength/2, startY + headDiameter + armLength);
        g.drawLine(startX, startY + headDiameter + armLength/2, startX + armLength/2, startY + headDiameter + armLength);

        // Рисование ног
        g.drawLine(startX, startY + headDiameter + bodyLength, startX - legLength/2, startY + headDiameter + bodyLength + legLength);
        g.drawLine(startX, startY + headDiameter + bodyLength, startX + legLength/2, startY + headDiameter + bodyLength + legLength);
    }
}

