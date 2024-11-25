import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        LinkedHashSet<String> solubles = new LinkedHashSet<String>();
        LinkedHashSet<String> insolubles = new LinkedHashSet<String>();

        //Get Element list
        String[] eList1 = new String[7];
        System.out.println("Enter the 7 elements of Table 1");
        for(int i=0; i<7; i++) {
            eList1[i]=sc.nextLine();
            solubles.add(eList1[i]);
        }
        //Get PPT
        boolean[][][] t1 = new boolean[6][6][2];
        System.out.println("Enter If Precipitate Occured(t)");
        for(int i=0; i<6; i++) for(int j=0; j<=i; j++) t1[i][j][0]=((sc.nextLine().equals("t"))? true: false);

        //Get Element List
        String[] eList2 = new String[7];
        System.out.println("Enter the 7 elements of Table 2");
        for(int i=0; i<7; i++) {
            eList2[i]=sc.nextLine();
            solubles.add(eList2[i]);
        }
        //Get PPT
        boolean[][][] t2 = new boolean[6][6][2];
        System.out.println("Enter If Precipitate Occured(t)");
        for(int i=0; i<6; i++) for(int j=0; j<=i; j++) t2[i][j][0]=((sc.nextLine().equals("t"))? true: false);

        boolean changed=true;
        while (changed) {
            changed=false;
            //Iterate through table 1
            for(int i=0; i<6; i++){
                for(int j=0; j<=i; j++){
                    //If cell has not been solved
                    if(!t1[i][j][1]){
                        String combRow=eList1[i+1];
                        String combCol=eList1[j];
                        String e1=combRow.substring(0, combRow.indexOf(' '))+combCol.substring(combCol.indexOf(' '));
                        String e2=combCol.substring(0, combCol.indexOf(' '))+combRow.substring(combRow.indexOf(' '));
                        //If element dissolved
                        if(!t1[i][j][0]){
                            changed=true;
                            t1[i][j][1]=true;
                            solubles.add(e1);
                            solubles.add(e2);
                        }
                        //If e1 is soluble
                        else if(solubles.contains(e1)){
                            changed=true;
                            t1[i][j][1]=true;
                            insolubles.add(e2);
                        }
                        //If e2 is soluble
                        else if(solubles.contains(e2)){
                            changed=true;
                            t1[i][j][1]=true;
                            insolubles.add(e1);
                        }
                        //If e1 is insoluble
                        else if(insolubles.contains(e1)){
                            changed=true;
                            t1[i][j][1]=true;
                            solubles.add(e2);
                        }
                        //If e2 is insoluble
                        else if(insolubles.contains(e2)){
                            changed=true;
                            t1[i][j][1]=true;
                            solubles.add(e1);
                        }
                    }
                    if(!t2[i][j][1]){
                        String combRow=eList2[i+1];
                        String combCol=eList2[j];
                        String e1=combRow.substring(0, combRow.indexOf(' '))+combCol.substring(combCol.indexOf(' '));
                        String e2=combCol.substring(0, combCol.indexOf(' '))+combRow.substring(combRow.indexOf(' '));
                        //If element dissolved
                        if(!t2[i][j][0]){
                            changed=true;
                            t2[i][j][1]=true;
                            solubles.add(e1);
                            solubles.add(e2);
                        }
                        //If e1 is soluble
                        else if(solubles.contains(e1)){
                            changed=true;
                            t2[i][j][1]=true;
                            insolubles.add(e2);
                        }
                        //If e2 is soluble
                        else if(solubles.contains(e2)){
                            changed=true;
                            t2[i][j][1]=true;
                            insolubles.add(e1);
                        }
                        //If e1 is insoluble
                        else if(insolubles.contains(e1)){
                            changed=true;
                            t2[i][j][1]=true;
                            solubles.add(e2);
                        }
                        //If e2 is insoluble
                        else if(insolubles.contains(e2)){
                            changed=true;
                            t2[i][j][1]=true;
                            solubles.add(e1);
                        }
                    }
                }
            }
        }

        ArrayList<String> solubleOutputs = new ArrayList<String>(solubles);
        java.util.Collections.sort(solubleOutputs, (String lineA, String lineB) -> {
            String wordA2 = lineA.split(" ")[1]+lineA.split(" ")[0];
            String wordB2 = lineB.split(" ")[1]+lineB.split(" ")[0];
            return wordA2.compareTo(wordB2);
        });
        ArrayList<String> insolubleOutputs = new ArrayList<String>(insolubles);
        java.util.Collections.sort(insolubleOutputs, (String lineA, String lineB) -> {
            String wordA2 = lineA.split(" ")[1]+lineA.split(" ")[0];
            String wordB2 = lineB.split(" ")[1]+lineB.split(" ")[0];
            return wordA2.compareTo(wordB2);
        });
        System.out.println("Solubles");
        System.out.println(solubleOutputs);

        System.out.println("Insolubles");
        System.out.println(insolubleOutputs);

        System.out.println("Unknown Pairs:");
        for(int i=0; i<6; i++){
            for(int j=0; j<=i; j++){
                if(!t1[i][j][1]){
                    String combRow=eList1[i+1];
                    String combCol=eList1[j];
                    String e1=combRow.substring(0, combRow.indexOf(' '))+" "+combCol.substring(combCol.indexOf(' ')+1);
                    String e2=combCol.substring(0, combCol.indexOf(' '))+" "+combRow.substring(combRow.indexOf(' ')+1);
                    System.out.println(e1+" "+e2);
                }
                if(!t2[i][j][1]){
                    String combRow=eList2[i+1];
                    String combCol=eList2[j];
                    String e1=combRow.substring(0, combRow.indexOf(' '))+" "+combCol.substring(combCol.indexOf(' ')+1);
                    String e2=combCol.substring(0, combCol.indexOf(' '))+" "+combRow.substring(combRow.indexOf(' ')+1);
                    System.out.println(e1+" "+e2);
                }
            }
        }

        
    }
}
