import matplotlib.pyplot as plt

from AndrewMonotoneChain import convex_hull as monotone_chain
from GrahamScan import convex_hull as graham_scan

points = [
    (4, 0), (3.8, 1.3), (3, 3), (1.3, 3.8), 
    (0, 4), (-1.3, 3.8), (-3, 3), (-3.8, 1.3), 
    (-4, 0), (-3.8, -1.3), (-3, -3), (-1.3, -3.8), 
    (0, -4), (1.3, -3.8), (3, -3), (3.8, -1.3),
    (0, 0), (1, 1), (-1, -1), (0.5, -0.5), (-1, 1), (2, 0), (0, -2)
]
hull_mc = monotone_chain(points)
print("Monotone Chain:")
print(hull_mc)
hull_gs = graham_scan(points)
print("Graham Scan:")
print(hull_gs)


print(set(hull_mc) == set(hull_gs))


#Graphs Config
px, py = zip(*points)

hull_cerrado = hull_mc+ [hull_mc[0]]
hx, hy = zip(*hull_cerrado)

plt.figure(figsize=(8, 6))
plt.scatter(px, py, color='blue', s=80, label='Puntos originales', zorder=5)

plt.plot(hx, hy, color='red', linestyle='-', linewidth=2, label='Convex Hull', zorder=4)


plt.scatter(hx[:-1], hy[:-1], color='red', s=100, zorder=6)

plt.title('Visualización de Convex Hull ', fontsize=14)
plt.xlabel('Coordenada X')
plt.ylabel('Coordenada Y')
plt.axhline(0, color='gray', linewidth=0.5, linestyle='--')
plt.axvline(0, color='gray', linewidth=0.5, linestyle='--')
plt.grid(True, linestyle=':', alpha=0.7)
plt.legend()

plt.show()

