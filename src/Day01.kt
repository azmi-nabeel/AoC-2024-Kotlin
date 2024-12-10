import java.util.ArrayList
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val a= ArrayList<Int>()
        val b= ArrayList<Int>()
        var lineNo=0
        while (lineNo<input.size) {
            val line= input[lineNo].split("   ").map { it.toInt() }
            a.add(line[0])
            b.add(line[1])
            lineNo++
        }
        a.sort()
        b.sort()
        val sz=a.size
        var ans=0
        for (i in 0..<sz){
            ans+= abs(a[i]-b[i])
        }
        return ans
    }

    fun part2(input: List<String>): Int {
        val a= ArrayList<Int>()
        val b= mutableMapOf<Int,Int>()
        var lineNo=0
        while (lineNo<input.size) {
            val line= input[lineNo].split("   ").map { it.toInt() }
            a.add(line[0])
            if(b.containsKey(line[1])) {
                b[line[1]]=b[line[1]]!!.inc()
            }
            else {
                b[line[1]] = 1
            }
            lineNo++
        }
        var ans=0
        val sz=a.size
        for (i in 0..<sz){
            if(b.containsKey(a[i])){
                ans+=(a[i]* b[a[i]]!!)
            }
        }
        return ans
    }

    // Read the input from the `src/input.txt` file.
    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}
