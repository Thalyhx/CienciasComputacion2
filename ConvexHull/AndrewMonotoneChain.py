
def cross(a,b,c):
    #vectors 
    ab=(b[0]-a[0],b[1]-a[1])
    bc=(c[0]-b[0],c[1]-b[1])

    #cross product (determinant)
    return ab[0]*bc[1]-ab[1]*bc[0]


#monotone chain algoritm
def convex_hull(points):

    sorted_points = sorted(set(points))
    lower_hull = []
    upper_hull = []

    # if only have 1 point
    if len(sorted_points) <= 1:
        return sorted_points

    #lower hull
    for point in sorted_points:
        
        while  len(lower_hull)>=2 and cross(lower_hull[-2], lower_hull[-1], point) <=0:
            lower_hull.pop()

        lower_hull.append(point)
                
    #upper hull
    for point in reversed(sorted_points):

        while len(upper_hull) >=2 and cross(upper_hull[-2], upper_hull[-1], point) <=0:
            upper_hull.pop()

        upper_hull.append(point)

    return lower_hull[:-1] + upper_hull[:-1]

#puntos = [(4, 0), (3.8, 1.3), (3, 3), (1.3, 3.8)]
#hull =convex_hull(puntos)
#print(hull)