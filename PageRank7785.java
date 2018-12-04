// Philipp Adams cs435 7785 mp
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

class PageRank7785
{
  public static void main(String args[])
  {
    if(args.length == 3)
    {
      int iterations = Integer.parseInt(args[0]);
      int initialValue = Integer.parseInt(args[1]);
      String fileName = args[2];
      Graph7785 g = createGraph(fileName);
      int vertices = g.getSize();
      double fillWith;
      double[] previousPageRank = new double[vertices];
      double[] currentPageRank = new double[vertices];

      fillWith = fillWith(initialValue, vertices);
      Arrays.fill(previousPageRank, fillWith);

      if(vertices < 11)
      {
        printRanks(previousPageRank, 0);
      }

      if(iterations > 0)
      {
        for(int j = 0; j < iterations; j++)
        {
          for(int i = 0; i < vertices; i++)
          {
            currentPageRank[i] = pageRank7785(i, g, previousPageRank);
          }
          previousPageRank = currentPageRank.clone();

          if(vertices < 11)
          {
            printRanks(currentPageRank, j+1);
          }
          else if(j == iterations - 1)
          {
            printRanksColumn(currentPageRank, j+1);
          }
        }
      }
      else
      {
        boolean deltaIsSmallEnough = false;
        int iter = 1;
        while(!deltaIsSmallEnough)
        {
          deltaIsSmallEnough = true;
          for(int i = 0; i < vertices; i++)
          {
            currentPageRank[i] = pageRank7785(i, g, previousPageRank);

            if(Math.abs(currentPageRank[i] - previousPageRank[i]) >= .0001)
            {
              deltaIsSmallEnough = false;
            }
          }
          previousPageRank = currentPageRank.clone();
          if(vertices < 11)
          {
            printRanks(currentPageRank, iter);
          }
          else if(deltaIsSmallEnough)
          {
            printRanksColumn(currentPageRank, iter);
          }
          iter++;
        }
      }
    }
    else
    {
      System.out.println("There are an incorrect number of arguments!");
    }
  }

  public static double fillWith(int initialValue, int vertices)
  {
    switch (initialValue)
    {
      case 1:
        return 1;
      case 0:
        return 0;
      case -1:
        return 1.0/vertices;
      case -2:
        return 1.0/Math.sqrt(vertices);
      default:
        return 0;
    }
  }

  public static Graph7785 createGraph(String fileName)
  {
    Graph7785 g = null;
    try(BufferedReader br = new BufferedReader(new FileReader(fileName)))
    {
      String[] lineArray;
      String currentLine;

      boolean firstLine = true;
      while((currentLine = br.readLine()) != null)
      {
        lineArray = currentLine.split(" ");

        if(firstLine)
        {
          g = new Graph7785(Integer.parseInt(lineArray[0]));
          firstLine = false;
        }
        else
        {
          g.insertEdge(Integer.parseInt(lineArray[0]), Integer.parseInt(lineArray[1]));
        }
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return g;
  }

  //Todo: create arrays of correct size for the indegree vertices on first pass. fill them on second pass.
  public static double pageRank7785(int vertex, Graph7785 g, double[] previousPageRank)
  {
    double pageRank = (1-.85)/g.getSize();

    for(int i = 0; i < g.getSize(); i++)
    {
      if(i == vertex)
      {
        continue;
      }

      LinkedList<Integer> outDegreeList = g.vertexArray[i];
      for(Integer outDegree: outDegreeList){
        if(vertex == outDegree)
        {
          pageRank += (.85*(previousPageRank[i]/g.vertexArray[i].size()));
        }
      }
    }
    return pageRank;
  }

  public static void printRanks(double[] pageRankArray, int iteration)
  {
    String format = ".000000";
    DecimalFormat df = new DecimalFormat(format);
    DecimalFormat iterf = new DecimalFormat("00");
    System.out.print("Iter: " +iterf.format(iteration)+ ": ");
    for(int i = 0; i < pageRankArray.length; i++)
    {
      System.out.print("P[" +i+ "] = " +df.format(pageRankArray[i])+ " ");
    }
    System.out.println();
  }

  public static void printRanksColumn(double[] pageRankArray, int iteration)
  {
    String format = ".000000";
    DecimalFormat df = new DecimalFormat(format);
    System.out.println("Iter: " +iteration);
    for(int i = 0; i < pageRankArray.length; i++)
    {
      System.out.println("P[" +i+ "] = " +df.format(pageRankArray[i])+ " ");
    }
  }
}
