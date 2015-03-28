package fanweizhu.fastSim.exec;



import fanweizhu.fastSim.core.*;
import fanweizhu.fastSim.core.QueryProcessor2.nodePairVal;
import fanweizhu.fastSim.data.*;
import fanweizhu.fastSim.util.*;
import fanweizhu.fastSim.util.io.TextWriter;


public class Online {
	public static void main(String args[]) throws Exception {
		//init parameters
		Config.hubType = args[0];
		Config.numHubs = Integer.parseInt(args[1]);
		Config.eta = Integer.parseInt(args[2]);

		Graph graph = new Graph();
		if (Config.numHubs > 0)
    		graph.loadFromFile(Config.nodeFile, Config.edgeFile, true);
    	else
    		graph.loadFromFile(Config.nodeFile, Config.edgeFile, false);
		QueryProcessor2 qp = new QueryProcessor2(graph);
		TextWriter out = new TextWriter(Config.outputDir + "/" + 
				"fastsim-AP-" +  "_H" + Config.numHubs + "_D" +Config.depth +"_C"+String.format("%1.0e",Config.clip)+"_E"+Config.eta);
		int count = 0;
		long start = System.currentTimeMillis();
		nodePairVal[] result = qp.query();
		long elapsed = (System.currentTimeMillis() - start);
		out.writeln(elapsed + "ms ");
		for (nodePairVal npv: result ){
			if (npv != null)
				out.writeln(npv.n1+"\t"+npv.n2+"\t"+npv.val);
		}
		out.close();
	}
}
