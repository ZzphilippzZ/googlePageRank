// Philipp Adams cs435 7785 mp
import java.util.*;

class Graph7785
{
  int vertices;
  LinkedList[] vertexArray;

  public Graph7785(int vertices)
  {
    this.vertices = vertices;
    vertexArray = new LinkedList[vertices];

    for(int i = 0; i < vertexArray.length; i++)
    {
      vertexArray[i] = new LinkedList<Integer>();
    }
  }

  public void insertEdge(int fromVertex, int toVertex)
  {
    vertexArray[fromVertex].add(toVertex);
  }

  public int getSize()
  {
    return vertices;
  }

  public void print()
  {
    for(int i = 0; i < vertexArray.length; i++)
    {
      System.out.print(i+ ": " +vertexArray[i]+ "\n");
    }
  }
}
