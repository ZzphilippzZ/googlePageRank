import java.util.*;

class Graph
{
  int vertices;
  private LinkedList[] vertexArray;

  public Graph(int vertices)
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
