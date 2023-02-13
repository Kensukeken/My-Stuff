import matplotlib.pyplot as plt
import numpy as np

# Define the number of points to be generated for the explosion
num_points = 1000

# Generate random data for the explosion
data = np.random.rand(num_points, 2) * 100

# Calculate the magnitude of each point
magnitude = np.sqrt(np.power(data[:, 0], 2) + np.power(data[:, 1], 2))

# Normalize the magnitude to a value between 0 and 1
magnitude = (magnitude - magnitude.min()) / (magnitude.max() - magnitude.min())

# Define a color map based on the magnitude
cmap = plt.get_cmap('plasma')
colors = cmap(magnitude)

# Plot the data as a scatter plot
plt.scatter(data[:, 0], data[:, 1], c=colors)

# Add title and labels to the plot
plt.title("Imaginary Supernova Explosion")
plt.xlabel("X Coordinate")
plt.ylabel("Y Coordinate")

# Add a color bar to represent the magnitude
cbar = plt.colorbar()
cbar.set_label("Intensity")

# Show the plot
plt.show()
