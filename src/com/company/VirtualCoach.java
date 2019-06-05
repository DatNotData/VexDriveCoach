package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;


public class VirtualCoach {
    private PointsManager _pointsManager;

    public VirtualCoach(PointsManager pointsManager){
        _pointsManager = pointsManager;
    }

    public int[] GetScoreDeltaWhenAddCubeToTower(){
        int[] scoreDeltas = {0,0,0};
        int[][] cubesInGoalZones = _pointsManager.GetCubesInGoalZones();
        int[] cubesInTowers = _pointsManager.GetCubesInTowers();
        if(IntStream.of(cubesInTowers).sum() < 7) {
            for (int color = 0; color < 3; color++) {
                if(cubesInGoalZones[0][color] + cubesInGoalZones[1][color] + cubesInTowers[color] < 22) {
                    scoreDeltas[color] = cubesInGoalZones[0][color] - cubesInGoalZones[1][color];
                }
            }
        }
        return scoreDeltas;
    }

    public int[] GetScoreDeltaWhenRemoveCubeToTower(){
        int[] scoreDeltas = {0,0,0};
        int[][] cubesInGoalZones = _pointsManager.GetCubesInGoalZones();
        for(int color = 0; color < 3; color++) {
            if(_pointsManager.GetCubesInTowers()[color] > 0) {
                scoreDeltas[color] = cubesInGoalZones[1][color] - cubesInGoalZones[0][color];
            }
        }
        return scoreDeltas;
    }

    public int[] GetScoreDeltaWhenAddCubeToGoalZones(){
        int[] cubesInTowers = _pointsManager.GetCubesInTowers();
        int[][] cubesInGoalZones = _pointsManager.GetCubesInGoalZones();
        int[] scoreDeltas = {0,0,0};
        for(int color = 0; color < 3; color++) {
            if((cubesInGoalZones[0][color] + cubesInGoalZones[1][color] + cubesInTowers[color]) < 22)
            scoreDeltas[color] = cubesInTowers[color] + 1;
        }
        return scoreDeltas;
    }

    public int[][] GetEstimatedCubeInGoalZones(PointsManager _pointsManager){
        return _pointsManager.GetCubesInGoalZones();
    }

    public int[][] GetBestOption(PointsManager _pointsManager){
        Map<Integer, Integer> scoreDeltasUnsorted = new HashMap<>();
        Map<Integer, Integer> scoreDeltasSorted = new HashMap<>();

        int[] scoreDeltaAddCubeToTower = this.GetScoreDeltaWhenAddCubeToTower();
        int[] scoreDeltaRemoveCubeToTower = this.GetScoreDeltaWhenRemoveCubeToTower();
        int[] scoreDeltaAddCubeToGoalZones = this.GetScoreDeltaWhenAddCubeToGoalZones();

        for(int i=0; i<3; i++)
            scoreDeltasUnsorted.put(i, scoreDeltaAddCubeToTower[i]);
        for(int i=0; i<3; i++)
            scoreDeltasUnsorted.put(i+3, scoreDeltaRemoveCubeToTower[i]);
        for(int i=0; i<3; i++)
            scoreDeltasUnsorted.put(i+6, scoreDeltaAddCubeToGoalZones[i]);


        int[][] bestOptions = {{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0}};
        for(int i = 0; i<9; i++) {
            int max = 0;
            for (int j = 0; j < 9; j++) // find valid "first" key
                if(scoreDeltasUnsorted.containsKey(j))
                    max = j;

            for (int j = 0; j < 9; j++) {
                if(scoreDeltasUnsorted.containsKey(j)){
                    max = (scoreDeltasUnsorted.get(j) >= scoreDeltasUnsorted.get(max)) ? j : max;
                }
            }
            bestOptions[i][0] = max;
            bestOptions[i][1] = scoreDeltasUnsorted.get(max);
            scoreDeltasSorted.put(max, scoreDeltasUnsorted.get(max));
            scoreDeltasUnsorted.remove(max);
        }
        return bestOptions;
    }
}
