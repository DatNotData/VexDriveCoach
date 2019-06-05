package com.company;

import javax.swing.*;

public class GUI {
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JSpinner spinner3;
    private JSpinner spinner4;
    private JSpinner spinner5;
    private JSpinner spinner6;
    private JSpinner spinner7;
    private JSpinner spinner8;
    private JSpinner spinner9;
    private JPanel panel;
    private JTextField textFieldPointsA;
    private JTextField textFieldPointsB;
    private JTextPane coachTextField;

    private JButton button1; //
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7; //
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13; //
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button17;
    private JButton button18;

    private JTextField textFieldA;
    private JTextField textFieldB;
    private JTextField textFieldC;
    private JTextField textFieldD;
    private JTextField textFieldE;
    private JTextField textFieldF;
    private JTextField textFieldG;
    private JTextField textFieldH;
    private JTextField textFieldI;

    private JTextField textFieldScoreDelta1;
    private JTextField textFieldScoreDelta2;
    private JTextField textFieldScoreDelta3;
    private JTextField textFieldScoreDelta4;
    private JTextField textFieldScoreDelta5;
    private JTextField textFieldScoreDelta6;
    private JTextField textFieldScoreDelta7;
    private JTextField textFieldScoreDelta8;
    private JTextField textFieldScoreDelta9;

    private PointsManager _pointsManager;
    private VirtualCoach _virtualCoach;

    private JFrame frame = new JFrame("VexDriveCoach");

    public GUI(PointsManager pointsManager, VirtualCoach virtualCoach){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e){}
        SwingUtilities.updateComponentTreeUI(frame);
        frame.setVisible(true);

        _pointsManager = pointsManager;
        _virtualCoach = virtualCoach;

        // our alliance's buttons
        button1.addActionListener(e -> _pointsManager.AddCubeToGoalZone(0,0));
        button2.addActionListener(e -> _pointsManager.RemoveCubeFromGoalZone(0,0));
        button3.addActionListener(e -> _pointsManager.AddCubeToGoalZone(0,1));
        button4.addActionListener(e -> _pointsManager.RemoveCubeFromGoalZone(0,1));
        button5.addActionListener(e -> _pointsManager.AddCubeToGoalZone(0,2));
        button6.addActionListener(e -> _pointsManager.RemoveCubeFromGoalZone(0,2));

        // opponent's alliances buttons
        button7.addActionListener(e -> _pointsManager.AddCubeToGoalZone(1,0));
        button8.addActionListener(e -> _pointsManager.RemoveCubeFromGoalZone(1,0));
        button9.addActionListener(e -> _pointsManager.AddCubeToGoalZone(1,1));
        button10.addActionListener(e -> _pointsManager.RemoveCubeFromGoalZone(1,1));
        button11.addActionListener(e -> _pointsManager.AddCubeToGoalZone(1,2));
        button12.addActionListener(e -> _pointsManager.RemoveCubeFromGoalZone(1,2));

        // tower's buttons
        button13.addActionListener(e -> _pointsManager.AddCubeToTower(0));
        button14.addActionListener(e -> _pointsManager.RemoveCubeFromTower(0));
        button15.addActionListener(e -> _pointsManager.AddCubeToTower(1));
        button16.addActionListener(e -> _pointsManager.RemoveCubeFromTower(1));
        button17.addActionListener(e -> _pointsManager.AddCubeToTower(2));
        button18.addActionListener(e -> _pointsManager.RemoveCubeFromTower(2));

        this.Update();
    }


    public void SetPoints(){
        int[] points = _pointsManager.GetPoints();
        textFieldPointsA.setText(Integer.toString(points[0]));
        textFieldPointsB.setText(Integer.toString(points[1]));
    }

    public void SetCubesQuantity(){
        int[][] cubesInGoalZones = _pointsManager.GetCubesInGoalZones();
        int[] cubesInTowers = _pointsManager.GetCubesInTowers();

        textFieldA.setText(Integer.toString(cubesInGoalZones[0][0]));
        textFieldB.setText(Integer.toString(cubesInGoalZones[0][1]));
        textFieldC.setText(Integer.toString(cubesInGoalZones[0][2]));

        textFieldD.setText(Integer.toString(cubesInGoalZones[1][0]));
        textFieldE.setText(Integer.toString(cubesInGoalZones[1][1]));
        textFieldF.setText(Integer.toString(cubesInGoalZones[1][2]));

        textFieldG.setText(Integer.toString(cubesInTowers[0]));
        textFieldH.setText(Integer.toString(cubesInTowers[1]));
        textFieldI.setText(Integer.toString(cubesInTowers[2]));
    }

    public void SetScoreDeltas(){
        int[] deltaCubesAddTower = _virtualCoach.GetScoreDeltaWhenAddCubeToTower();
        int[] deltaCubesRemoveTower = _virtualCoach.GetScoreDeltaWhenRemoveCubeToTower();
        int[] deltaCubesAddGoalZones = _virtualCoach.GetScoreDeltaWhenAddCubeToGoalZones();

        textFieldScoreDelta1.setText(Integer.toString(deltaCubesAddTower[0]));
        textFieldScoreDelta2.setText(Integer.toString(deltaCubesAddTower[1]));
        textFieldScoreDelta3.setText(Integer.toString(deltaCubesAddTower[2]));

        textFieldScoreDelta4.setText(Integer.toString(deltaCubesRemoveTower[0]));
        textFieldScoreDelta5.setText(Integer.toString(deltaCubesRemoveTower[1]));
        textFieldScoreDelta6.setText(Integer.toString(deltaCubesRemoveTower[2]));

        textFieldScoreDelta7.setText(Integer.toString(deltaCubesAddGoalZones[0]));
        textFieldScoreDelta8.setText(Integer.toString(deltaCubesAddGoalZones[1]));
        textFieldScoreDelta9.setText(Integer.toString(deltaCubesAddGoalZones[2]));
    }

    public void SetCoachMessage(){
        int[][] bestOption = _virtualCoach.GetBestOption(_pointsManager);
        String[] messages = {
                "Add Orange Tower",
                "Add Purple Tower",
                "Add Green  Tower",

                "Rem Orange Tower",
                "Rem Purple Tower",
                "Rem Green  Tower",

                "Add Orange Zones",
                "Add Purple Zones",
                "Add Green  Zones"
        };
        String coachRecommendation = "";
        for(int i=0; i<9; i++){
            coachRecommendation += Integer.toString(i+1) + ") " + messages[bestOption[i][0]] + "  :  " + bestOption[i][1] + " points\n";
        }
        coachTextField.setText(coachRecommendation);
    }

    public void Update(){
        this.SetPoints();
        this.SetCubesQuantity();
        this.SetCoachMessage();
        //this.SetScoreDeltas();
    }
}
