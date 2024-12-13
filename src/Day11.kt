fun main() {
    val dp=HashMap<Pair<Long,Int>,Long>()
    fun solve(n: Long, k: Int): Long {
        if (dp.containsKey(Pair(n,k)))return dp[Pair(n,k)]!!
        if (k==0) {
            dp[Pair(n, k)] = 1
            return dp[Pair(n, k)]!!
        }
        if (n==0L){
            dp[Pair(n, k)] = solve(1,k-1)
            return dp[Pair(n, k)]!!
        }
        val str=n.toString()
        if (str.length%2==0) {
            dp[Pair(n, k)] = solve(str.slice(0..<str.length/2).toLong(),k-1)+solve(str.slice((str.length/2)..<str.length).toLong(),k-1)
            return dp[Pair(n, k)]!!
        }
        dp[Pair(n,k)]=solve(n*2024,k-1)
        return dp[Pair(n, k)]!!
    }
    fun part1(input: List<String>): Long {
        val arr=input[0].split(" ").map { it.toLong() }
        return arr.map { solve(it,25) }.sumOf { it }

    }

    fun part2(input: List<String>): Long {
        val arr=input[0].split(" ").map { it.toLong() }
        return arr.map { solve(it,75) }.sumOf { it }
    }

    // Read the input from the `src/input.txt` file.
    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}