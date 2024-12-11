fun main() {
    val arr = ArrayList<List<Int>>()

    fun <T> Iterable<T>.withoutItemAt(index: Int): List<T> =
        take(index) + drop(index + 1)


    fun ch1(list: List<Int>): Boolean{
        for (i in 1..<list.size){
            if(list[i]-list[i-1]>3||list[i]-list[i-1]<1)return false
        }
        return true
    }
    fun ch2(list: List<Int>): Boolean{
        for (i in 1..<list.size){
            if(list[i-1]-list[i]>3||list[i-1]-list[i]<1)return false
        }
        return true
    }
    fun part1(input: List<String>): Int {
        for (line in input){
            arr.add(line.split(" ").map{it.toInt()})
        }

        var ans=0
        for (list in arr){
            if(ch1(list)||ch2(list))ans++
        }
        return ans
    }

    fun part2(input: List<String>): Int {
        var ans=0
        for (list in arr){
            for (i in list.indices){
                if(ch1(list.withoutItemAt(i))||ch2(list.withoutItemAt(i))){
                    ans++
                    break
                }
            }
        }
        return ans
    }

    // Read the input from the `src/input.txt` file.
    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}