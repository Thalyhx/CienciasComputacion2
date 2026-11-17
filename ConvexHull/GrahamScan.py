
import math

def cross(a,b,c):
    #vectors 
    ab=(b[0]-a[0],b[1]-a[1])
    bc=(c[0]-b[0],c[1]-b[1])

    #cross product (determinant)
    return ab[0]*bc[1]-ab[1]*bc[0]

def convex_hull(points):

    points_sorted = sorted(set(points), key =lambda p: (p[1],p[0]))

    # if only have 1 point
    if len(points_sorted) <= 1:
        return points_sorted
    
    a = points_sorted[0]
    points_sorted_angle = [a] + sorted(points_sorted[1:], key = lambda p: math.atan2(p[1]-a[1],p[0]-a[0]))

    hull_stack =[]

    for point in points_sorted_angle:
        while len(hull_stack)>=2 and cross(hull_stack[-2], hull_stack[-1],point)<=0:
            hull_stack.pop()
        hull_stack.append(point)

    return hull_stack

#puntos = [(4, 0), (3.8, 1.3), (3, 3), (1.3, 3.8)]
#hull =convex_hull(puntos)
#print(hull)

