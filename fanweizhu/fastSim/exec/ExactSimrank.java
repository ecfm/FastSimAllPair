package fanweizhu.fastSim.exec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import fanweizhu.fastSim.core.Simrank;
import fanweizhu.fastSim.data.*;
import fanweizhu.fastSim.util.Config;
import fanweizhu.fastSim.util.MapUtil;
import fanweizhu.fastSim.util.io.TextReader;
import fanweizhu.fastSim.util.io.TextWriter;

public class ExactSimrank {
	 public static void main(String[] args) throws Exception {
	    	/*String nodeFile = args[0];
	    	String edgeFile = args[1];
	    	String queryFile = args[2];
	    	String resultFile = args[3];
	    	int topk = Integer.valueOf(args[4]);*/
	    	
	    	
	    	
	   // 	Config.maxNode  = Integer.valueOf(args[0]);
	  //  	int topk = Integer.valueOf(args[1]);
	    	
	    
	        Graph graph = new Graph();	
	        graph.loadFromFile(Config.nodeFile, Config.edgeFile, false);
	        
	        long start = System.currentTimeMillis();
	    		
	    		Simrank sr = new Simrank(graph);
	    		
	    		
		
			
			long elapsed =  System.currentTimeMillis() - start;

			System.out.println("\nLoading queries...");
	        List<Integer> qids = new ArrayList<Integer>();
	        TextReader in = new TextReader(Config.queryFile);
	        String line;
	        while ( (line = in.readln()) != null) {
	        	int qid = Integer.parseInt(line);
	        	qids.add(qid);
	        }
	        in.close();
	 //       System.out.println("Size of queries: " + qids.size());
	       
	        System.out.println("Starting query processing...");
	        
	        TextWriter out = new TextWriter(Config.outputDir + "/exact");       
	        for (int qid : qids) {
	        	Map<Integer, Double> result = new HashMap<Integer, Double>();
	        	List<Entry<Integer, Double>> rankedResult = null;
	            result = sr.computeResult(qid);
	           
	            
	            rankedResult = MapUtil.sortMap(result,Config.resultTop);
	        
	    
				out.write(elapsed + "ms ");
				for (Map.Entry<Integer, Double> e : rankedResult)
	                out.write(e.getKey() + "_" + e.getValue() + " ");
	          
	            out.writeln();
	        }
	        
	        out.close();
	        
	        System.out.println();
	    }
	 
	
}
