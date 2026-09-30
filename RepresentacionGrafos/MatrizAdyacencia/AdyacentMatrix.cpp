#include <iostream>
#include <vector>
#include <string>
#include <sstream>
using namespace std;

vector <char> nodes;
vector<vector<string>> matrix;

// Confirm the existence of node: return -1 if the node doesn't exist or index if the node exist.
int searchIndex(char n) {
    for (int i = 0; i < nodes.size(); i++)
        if (nodes[i] == n) return i;
    return -1;
}

// Return the index of a node: If the node don't exist create them and add this to the matrix.
int getIndex(char n) {
    int index = searchIndex(n);
    if (index != -1) return index;
    nodes.push_back(n);
    for (auto& fila : matrix) fila.push_back("0");
    matrix.push_back(vector<string>(nodes.size(), "0"));
    return nodes.size() - 1;
}

// Create relation method
void createRelation(char a, char b, const string& w){
    int i = searchIndex(a), j = searchIndex(b);
    if (i != -1 && j != -1 && matrix[i][j] != "0") {
        cout << "-> The relation already exist. Use: update NodeNodeWeight\n";
        return;
    }
    i = getIndex(a);
    j = getIndex(b);
    matrix[i][j] = w;
    matrix[j][i] = w;
}

// Update relation method
void updateRelation(char a, char b, const string& w) {
    int i = searchIndex(a), j = searchIndex(b);
    if (i == -1 || j == -1 || matrix[i][j] == "0") {
        cout << "-> The relation doesn't exist. Use: create NodeNodeWeight\n";
        return;
    }
    matrix[i][j] = w;
    matrix[j][i] = w;
}

// Delete relation method
void deleteRelation(char a, char b) {
    int i = searchIndex(a), j = searchIndex(b);
    if (i == -1 || j == -1 || matrix[i][j] == "0") {
        cout << "-> The relation doesn't exist.\n";
        return;
    }
    matrix[i][j] = "0";
    matrix[j][i] = "0";
}

// Print in console the matrix
void printMatrix() {
    cout << "   ";
    for (char n : nodes) cout << n << " ";
    cout << "\n";
    for (int i = 0; i < nodes.size(); i++) {
        cout << nodes[i] << "  ";
        for (int j = 0; j < nodes.size(); j++)
            cout << matrix[i][j] << " ";
        cout << "\n";
    }
}


int main() {
    string line;
    cout << "*** Adyacent Matrix ***\n";
    cout << "Enter a comant whit the relation of the form: comand NodeNode Weight.\nDisponible comands: create, delete, update and exit (for break the program).\nExample: ABr (Node1: A, Node2: B, Weight: r).";
    while (true) {
        cout << "\n> ";
        getline(cin, line);
        if (line == "exit") break;

        istringstream ss(line);
        string comand, relation, w;
        ss >> comand >> relation >> w;

        if (relation.size() != 2) {
            cout << "Remember: The correct format is: comand NodeNode Weight.";
            continue;
        }
        if (w.empty()) w = "1";

        if (comand == "create") createRelation(relation[0], relation[1], w);
        else if (comand == "update") updateRelation(relation[0], relation[1], w);
        else if (comand == "delete") deleteRelation(relation[0], relation[1]);
        else {
            cout << "Unkow comand...\n";
            continue;
        }

        printMatrix();
    }
    return 0;
}