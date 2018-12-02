// Philipp Adams cs435 7785 mp
import java.io.*;

class Exe7785
{
  public static void main(String args[])
  {
    if(args.length == 3)
    {
      String fileName = args[2];
      Graph g = createGraph(fileName);

      g.print();
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
}
