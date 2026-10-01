nodes = set()
edges = set()
weighted_edges = set()

def add_node(node):
    nodes.add(node)
    
def add_edge(origin, destination):
    if origin in nodes and destination in nodes:
        edges.add((origin, destination))
    else:
        print("Error: One or both nodes does not exists")

def add_weighted_edge(origin, destination, peso):
    if origin in nodes and destination in nodes:
        if peso > 0:
            weighted_edges.add((origin, destination, peso))
        else:
            print("Error: the weight must be greater than 0")
    else:
        print("Error: One or both nodes does not exists")

def find_neighbors(node):
    if node in nodes:
        neighbors = []
        for edge in edges:
            if edge[0] == node:
                neighbors.append(edge[1])
            elif edge[1] == node:
                neighbors.append(edge[0])
        return neighbors
    else:
        print("Error: the node does not exists")
        return []
    
    
def find_neighbors_with_weight(node):
    if node in nodes:
        neighbors = []
        for edge in weighted_edges:
            if edge[0] == node:
                neighbors.append((edge[1], edge[2]))
            elif edge[1] == node:
                neighbors.append((edge[0], edge[2]))
        return neighbors
    else:
        print("Error: the node does not exists")
        return []

# Test cases
if __name__ == "__main__":
    for n in ["A", "B", "C", "D", "E", "F", "G"]:
        add_node(n)
        
    add_edge("A", "B")
    add_edge("A", "C")
    add_edge("B", "D")
    add_edge("C", "E")
    add_edge("D", "E") 
    
    add_weighted_edge("A", "D", 10)
    add_weighted_edge("A", "F", 15)
    add_weighted_edge("E", "F", 8)
    
    print("--- failures ---")
    add_edge("A", "H")               
    add_weighted_edge("B", "C", -5)  
    add_weighted_edge("Z", "X", 10)  
    print("-" * 30, "\n")

    print(f"All nodes ({len(nodes)}):", nodes)
    print(f"All edges ({len(edges)}):", edges)
    print(f"All weighted edges ({len(weighted_edges)}):", weighted_edges)
    
    print("\n--- neighbors (simple) ---")
    print("Vecinos de A (Hub central):", find_neighbors("A")) 
    print("Vecinos de E:", find_neighbors("E"))              
    print("Vecinos de F:", find_neighbors("F"))               
    print("Vecinos de G (Aislado):", find_neighbors("G"))    
    
    print("\n--- neighbors (with weight) ---")
    print("Rutas pesadas desde A:", find_neighbors_with_weight("A")) 
    print("Rutas pesadas desde F:", find_neighbors_with_weight("F")) 
    print("Rutas pesadas desde G:", find_neighbors_with_weight("G"))