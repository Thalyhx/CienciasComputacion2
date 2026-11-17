# pip install matplotlib
import random
import time
import matplotlib.pyplot as plt

from AndrewMonotoneChain import convex_hull as monotone_chain
from GrahamScan import convex_hull as graham_scan

def generar_puntos(cantidad=1000000, rango=10000):
    puntos =[]
    for i in range(cantidad):
        puntos.append((random.uniform(-rango, rango), random.uniform(-rango, rango)))
    return puntos


def main():

    N = 1_000_000
    puntos = generar_puntos(cantidad=N, rango=10000)

    # Andrew's Monotone Chain
    t0 = time.perf_counter()
    hull_mc = monotone_chain(puntos)
    t1 = time.perf_counter()
    tiempo_mc = t1 - t0
    print(f"[Monotone Chain] Tiempo: {tiempo_mc:.4f} s | Vértices en envolvente: {len(hull_mc)}")

    # Graham Scan
    t2 = time.perf_counter()
    hull_gs = graham_scan(puntos)
    t3 = time.perf_counter()
    tiempo_gs = t3 - t2
    print(f"[Graham Scan]    Tiempo: {tiempo_gs:.4f} s | Vértices en envolvente: {len(hull_gs)}")



if __name__ == "__main__":
    main()