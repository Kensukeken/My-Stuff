using Pkg
Pkg.add("Plots")

using Plots

# Define vectors
vector1 = [3, 2]
vector2 = [1, 4]

# Define starting points for the vectors
origin = [0, 0]

# Create a plot
plot(xlim=(-1, 5), ylim=(-1, 5), legend=false, aspect_ratio=:equal)

# Plot the vectors
quiver!([origin[1]], [origin[2]], quiver=(vector1[1], vector1[2]), color=:blue)
quiver!([origin[1]], [origin[2]], quiver=(vector2[1], vector2[2]), color=:red)

# Add labels
annotate!(origin[1] + vector1[1]/2, origin[2] + vector1[2]/2, text("Vector 1", :blue, :center))
annotate!(origin[1] + vector2[1]/2, origin[2] + vector2[2]/2, text("Vector 2", :red, :center))

# Display the plot
display(plot)
