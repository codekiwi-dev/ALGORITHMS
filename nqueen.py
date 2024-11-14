def is_safe(board, row, col, n):
    # Check if no other queen in the same column
    for i in range(row):
        if board[i][col] == 'Q':
            return False

    # Check upper left diagonal
    i, j = row, col
    while i >= 0 and j >= 0:
        if board[i][j] == 'Q':
            return False
        i -= 1
        j -= 1

    # Check upper right diagonal
    i, j = row, col
    while i >= 0 and j < n:
        if board[i][j] == 'Q':
            return False
        i -= 1
        j += 1

    return True

def solve_n_queens(board, row, n):
    if row == n:
        for r in board:
            print(" ".join(r))
        print()
        return

    for col in range(n):
        if is_safe(board, row, col, n):
            board[row][col] = 'Q'
            solve_n_queens(board, row + 1, n)
            board[row][col] = '.'  # Backtrack

def n_queens(n):
    board = [['.' for _ in range(n)] for _ in range(n)]
    solve_n_queens(board, 0, n)

# Example usage for 4 queens
n_queens(4)
