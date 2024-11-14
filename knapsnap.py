def fractional_knapsack(weights, values, W):
    n = len(values)
    items = [(values[i] / weights[i], weights[i], values[i]) for i in range(n)]
    items.sort(reverse=True, key=lambda x: x[0])

    total_value = 0
    for ratio, weight, value in items:
        if W >= weight:
            W -= weight
            total_value += value
        else:
            total_value += W * ratio
            break

    return total_value

# Driver code for the Fractional Knapsack
weights = [10, 20, 30]
values = [60, 100, 120]
W = 50  # Knapsack capacity

max_value = fractional_knapsack(weights, values, W)
print(f"Maximum value in Fractional Knapsack: {max_value}")
