package Phase2;

import Phase1.Agglomeration;

public class TestAgglomeration {
    
    public static void main(String [] args){
    
        Agglomeration agg=new Agglomeration();
        
        InitAgglomeration.readAgglomerationFromFile(agg,"Phase2/agglomeration.txt");
    }
}