package net.tsymbaliuk.various;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Find path in 2d matrix to the node '9'.
 * Nodes '-1' are blocked
 * can go in any neighbor node from upper left corner (0; 0)
 *
 */

public class FindPathInMatrix {
  private final int height;
  private final int width;
  private final Vertex[] graph;

  static class Vertex implements Comparable<Vertex> {
    int id;
    int cost;
    ArrayList<Edge> edges;
    Vertex prev;

    Vertex(int id) {
      this.id = id;
      edges = new ArrayList<>();
      cost = Integer.MAX_VALUE;
    }

    @Override
    public int compareTo(Vertex o) {
      return Integer.compare(this.cost, o.cost);
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Vertex) {
        return obj == this || this.id == ((Vertex)obj).id;
      }
      return false;
    }

    @Override
    public int hashCode() {
      return Objects.hash(id);
    }

    int getId() {
      return id;
    }
  }

  static class Edge {
    Vertex start;
    Vertex end;

    Edge(Vertex start, Vertex end) {
      this.start = start;
      this.end = end;
    }
  }

  /**
   * Constructor.
   *
   * @param matrix 2D labyrinth, -1 for blocked path
   */
  public FindPathInMatrix(int[][] matrix) {
    height = matrix.length;
    width = matrix[0].length;
    graph = new Vertex[height * width];
    initVertexes(matrix);
    initEdges(matrix);
  }

  private boolean isOpen(int[][] matrix, int row, int column) {
    return matrix[row][column] != -1;
  }

  private boolean exists(int[][] matrix, int row, int column) {
    return isValidCell(row, column) && isOpen(matrix, row, column);
  }

  // Init vertexes
  protected void initVertexes(int[][] matrix) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (matrix[i][j] != -1) {
          Vertex v = new Vertex(m2v(i, j));
          graph[v.id] = v;
        }
      }
    }
  }

  protected void initEdges(int[][] matrix) {
    // Init advances
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (isOpen(matrix, i, j)) {
          Vertex v = graph[m2v(i, j)];
          addNeighbor(matrix, v, i, j + 1);
          addNeighbor(matrix, v, i, j - 1);
          addNeighbor(matrix, v, i + 1, j);
          addNeighbor(matrix, v, i - 1, j);
        }
      }
    }
  }

  void addNeighbor(int[][] matrix, Vertex v, int row, int column) {
    if (exists(matrix, row, column)) {
      Vertex w = graph[m2v(row, column)];
      v.edges.add(new Edge(v, w));
    }
  }

  public int m2v(int row, int column) {
    return row * width + column;
  }

  public int v2r(int v) {
    return v / width;
  }

  public int v2c(int v) {
    return v % width;
  }

  boolean isValidCell(int row, int column) {
    return row >= 0 && column >= 0
      && row < height && column < width;
  }

  /**
   * Find path from vertex {@code startId} to vertex {@code endId}.
   * @param startId Id of the start vertex
   * @param endId Id of the end vertex
   * @return path length or -1 if no path
   */
  public Vertex findPath(int startId, int endId) {
    Vertex start = graph[startId];
    Vertex end = graph[endId];
    Set<Vertex> visited = new HashSet<>();

    start.cost = 0;

    PriorityQueue<Vertex> pq = new PriorityQueue<>();
    pq.add(start);

    while (!pq.isEmpty()) {
      Vertex v = pq.poll();
      for (Edge edge : v.edges) {
        Vertex w = edge.end;
        if (!visited.contains(w) || w.cost > v.cost + 1) {
          w.prev = edge.start;
          w.cost = v.cost + 1;
          pq.add(w);
        }
        if (w == end) {
          return w;
        }
      }
      visited.add(v);
    }
    return null;
  }

  protected List<Vertex> getPathList(Vertex v) {
    LinkedList<Vertex> path = new LinkedList<>();
    path.push(v);
    while (v.prev != null) {
      v = v.prev;
      path.push(v);
    }
    return path;
  }

  public int[] getPath(Vertex v) {
    List<Vertex> path = getPathList(v);
    return path.stream().mapToInt(Vertex::getId).toArray();
  }
}

