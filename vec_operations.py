import numpy as np

def are_coplanar(v1, v2, v3):
    matrix = np.array([v1, v2, v3])
    return np.linalg.det(matrix) == 0

def dot_product(a, b):
    return np.dot(a, b)

def scalar_projection(a, b):
    a = np.array(a)
    b = np.array(b)
    return np.dot(a, b) / np.linalg.norm(b)

def vector_projection(a, b):
    a = np.array(a)
    b = np.array(b)
    return (np.dot(a, b) / np.dot(b, b)) * b

def cross_product(a, b):
    return np.cross(a, b)

def torque(r, F):
    return np.cross(r, F)

def parallelogram_area(a, b):
    return np.linalg.norm(np.cross(a, b))

def line_parametric_equation(point, direction):
    return f"x = {point[0]} + t*{direction[0]}, y = {point[1]} + t*{direction[1]}, z = {point[2]} + t*{direction[2]}"

def line_cartesian_equation(point, direction):
    A = -direction[1]
    B = direction[0]
    C = -(A * point[0] + B * point[1])
    return f"{A}x + {B}y + {C} = 0"

def angle_between_lines(m1, m2):
    tan_theta = abs((m2 - m1) / (1 + m1 * m2))
    return np.arctan(tan_theta)

v1 = [1, 2, 3]
v2 = [4, 5, 6]
v3 = [7, 8, 9]

print("Are v1, v2, v3 coplanar?", are_coplanar(v1, v2, v3))
print("Dot product of v1 and v2:", dot_product(v1, v2))
print("Scalar projection of v1 on v2:", scalar_projection(v1, v2))
print("Vector projection of v1 on v2:", vector_projection(v1, v2))
print("Cross product of v1 and v2:", cross_product(v1, v2))
print("Torque with r=v1 and F=v2:", torque(v1, v2))
print("Area of parallelogram formed by v1 and v2:", parallelogram_area(v1, v2))
print("Parametric equation of line through point [1,2,3] with direction [4,5,6]:", line_parametric_equation([1, 2, 3], [4, 5, 6]))
print("Cartesian equation of line through point [1,2] with direction [4,5]:", line_cartesian_equation([1, 2], [4, 5]))
print("Angle between lines with slopes 2 and 3:", angle_between_lines(2, 3))
