package com.company;

import java.util.stream.IntStream;

public class PointsManager {
    private int _cubesInGoalZones[][] = {{0,0,0},{0,0,0}}; // us:opponent; orange:purple:green
    private int _cubesInTowers[] = {0,0,0}; // orange:purple:green
    private int _points[] = {0,0}; // us:opponent
    private GUI _gui = null;

    public void CalculatePoints(){
        int[] points = {0,0};
        for(int alliance=0; alliance<2; alliance++){
            for(int color = 0; color<3; color++){
                points[alliance] += _cubesInGoalZones[alliance][color] * (_cubesInTowers[color] + 1);
            }
        }
        _points = points;
    }

    public int[][] GetCubesInGoalZones(){
        return _cubesInGoalZones;
    }
    public int[] GetCubesInTowers(){
        return _cubesInTowers;
    }
    public int[] GetPoints(){
        return _points;
    }

    public void SetGui(GUI gui){
        _gui = gui;
    }

    public void UpdateGui(){
        if(_gui != null) // if a gui was set
            _gui.Update(); // update it
    }

    public void AddCubeToGoalZone(int alliance, int color){
        if(_cubesInGoalZones[0][color] + _cubesInGoalZones[1][color] + _cubesInTowers[color] < 22){
            _cubesInGoalZones[alliance][color]++;
            this.CalculatePoints();
            this.UpdateGui();
        }
    }

    public void RemoveCubeFromGoalZone(int alliance, int color){
        if(_cubesInGoalZones[alliance][color] > 0) {
            _cubesInGoalZones[alliance][color]--;
            this.CalculatePoints();
            this.UpdateGui();
        }
    }

    public void AddCubeToTower(int color){
        if(IntStream.of(_cubesInTowers).sum() < 7 && _cubesInGoalZones[0][color] + _cubesInGoalZones[1][color] + _cubesInTowers[color] < 22) {
            _cubesInTowers[color]++;
            this.CalculatePoints();
            this.UpdateGui();
        }
    }

    public void RemoveCubeFromTower(int color){
        if(_cubesInTowers[color] > 0) {
            _cubesInTowers[color]--;
            this.CalculatePoints();
            this.UpdateGui();
        }
    }

    public void PrintAllInfo(){
        for(int alliance = 0; alliance <2; alliance++) {
            String alliances[] = {"Our alliance", "Opponent"};
            System.out.println(alliances[alliance]);
            System.out.print("Points : ");
            System.out.println(_points[alliance]);
            System.out.println("Cubes in goal zones (orange:purple:green) :");
            for(int cubes : _cubesInGoalZones[alliance])
                System.out.println(cubes);
            System.out.println("----------");
        }
        System.out.println("Cubes in towers (orange:purple:green) :");
        for(int cubes : _cubesInTowers)
            System.out.println(cubes);
        System.out.println("----------");
    }
}
