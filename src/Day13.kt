fun main() {
    val regex = """-?\d+""".toRegex()
    val shift=10000000000000L
    var ans2=0L
    fun calc(Ax:Long, Ay:Long ,Bx:Long, By:Long, Tx:Long, Ty:Long):Long{
        val det: Long = Ax * By - Ay * Bx
        val det1: Long = By * Tx - Bx * Ty
        val det2: Long = Ax * Ty - Ay * Tx
        if(det1%det==0L && det2%det==0L)
            return (3*(det1/det))+(det2/det)
        else
            return 0L
    }
    fun part1(input: List<String>): Long {
        var ans=0L
        input.chunked(4).forEach {
            val numbers = regex.findAll(it.joinToString(" ")).map { match -> match.value.toLong() }.toList()
            if (numbers.size == 6) {
                val Ax = numbers[0]
                val Ay = numbers[1]
                val Bx = numbers[2]
                val By = numbers[3]
                val Tx = numbers[4]
                val Ty = numbers[5]
                ans+=calc(Ax, Ay, Bx, By, Tx, Ty)
                ans2+=calc(Ax,Ay,Bx,By,Tx+shift,Ty+shift)
            }
        }
        return ans
    }

    fun part2(input: List<String>): Long {
        return ans2
    }

    // Read the input from the `src/input.txt` file.
    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}