package com.company;

public class Main {
    public static PointsManager pointsManager = new PointsManager();
    public static VirtualCoach virtualCoach = new VirtualCoach(pointsManager);
    public static GUI gui = new GUI(pointsManager, virtualCoach);

    public static void main(String[] args) {
        pointsManager.SetGui(gui);
    }
}
