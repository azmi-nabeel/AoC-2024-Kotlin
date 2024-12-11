fun main() {

    val mulRegex="""mul\((\d{1,3}),(\d{1,3})\)"""
    val doRegex="""do\(\)"""
    val dontRegex="""don't\(\)"""

    fun part1helper(line: String): Long{
        return mulRegex.toRegex().findAll(line).sumOf {
            val (a,b) = it.destructured
            a.toLong()*b.toLong()
        }
    }
    var enable=true
    fun part2helper(line: String): Long{
        var ans=0L
         """$mulRegex|$doRegex|$dontRegex""".toRegex().findAll(line).forEach { match ->
             when(match.value){
                 "don't()" -> enable=false
                 "do()" -> enable=true
                 else ->if (enable) ans+=(match.destructured.toList()[0].toLong())*(match.destructured.toList()[1].toLong())
             }
         }
        return ans
    }

    fun part1(input: List<String>): Long {
        return input.sumOf { part1helper(it) }
    }

    fun part2(input: List<String>): Long {
        return input.sumOf { part2helper(it) }
    }

    // Read the input from the `src/input.txt` file.
    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}