def heart():
    print("\n".join(["".join(["❤️" if ((x**2 + y**2 - 1)**3 - x**2 * y**3 <= 0) else " " for x in range(-30, 30)]) for y in range(-30, 30)]))

heart()
