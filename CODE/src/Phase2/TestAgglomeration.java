package Phase2;

import Phase1.Agglomeration;
public class TestAgglomeration {
    
    public static void main(String [] args){
    
        Agglomeration agg=new Agglomeration();
        
        InitAgglomeration.readAgglomerationFromFile(agg,"Phase2/agglomeration.txt");
        
        int k=agg.getNombreVilles();

        int scoreAlgoNaif=AlgoApproximationNaif.algoNaif(k, agg);
        System.out.println("avec le premier algo : "+scoreAlgoNaif);

        int scoreAlgoMoinNaif=AlgoApproximationNaif.algoMoinNaif(k,agg);
        System.out.println("avec le deuxieme algo : "+scoreAlgoMoinNaif);

    }
}
