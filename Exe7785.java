// Philipp Adams cs435 7785 mp
import java.io.*;
import java.util.Arrays;

class Exe7785
{
  public static void main(String args[])
  {
    if(args.length == 3)
    {
      int iterations = Integer.parseInt(args[0]);
      int initialValue = Integer.parseInt(args[1]);
      String fileName = args[2];
      Graph g = createGraph(fileName);
      int vertices = g.getSize();
      double fillWith;
      double[] previousPageRank = new double[vertices];
      double[] currentPageRank = new double[vertices];

      switch (initialValue)
      {
        case 1:
          fillWith = 1;
          break;
        case 0:
          fillWith = 0;
          break;
        case -1:
          fillWith = 1.0/vertices;
          break;
        case -2:
          fillWith = 1.0/Math.sqrt(vertices);
          break;
        default:
          fillWith = 0;
          System.out.println("Please input valid initial value");
      }

      System.out.println(fillWith);
      Arrays.fill(previousPageRank, fillWith);

      System.out.println(pageRank7785());
    }
    else
    {
      System.out.println("There are an incorrect number of arguments!");
    }
  }

  public static Graph createGraph(String fileName)
  {
    Graph g = null;
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
          g = new Graph(Integer.parseInt(lineArray[0]));
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

  public static double pageRank7785()
  {
    return (1 - .85)/4;
  }
}
