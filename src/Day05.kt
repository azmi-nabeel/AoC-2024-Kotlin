fun main() {
    fun part1(input: List<String>): Int {
        var start=false
        val rules= HashSet<Pair<Int,Int>>()
        var ans=0
        for (line in input){
            if (line.length<3){
                start=true
            }
            else {
                if(!start){
                    val rule=line.split("|").map { it.toInt()}
                    rules.add(Pair(rule[0],rule[1]))
                }
                else{
                    val arr=line.split(",").map { it.toInt()}
                    val sz=arr.size
                    var isValid=true
                    for (i in 0..<sz){
                        for (j in i+1..<sz){
                            if (rules.contains(Pair(arr[j],arr[i]))){
                                isValid=false
                                break
                            }
                        }
                        if(!isValid)break
                    }
                    if (isValid)ans+=arr[sz/2]
                }
            }
        }
        return ans
    }

    fun part2(input: List<String>): Int {
        var start=false
        val rules= HashSet<Pair<Int,Int>>()
        var ans=0
        for (line in input){
            if (line.length<3){
                start=true
            }
            else {
                if(!start){
                    val rule=line.split("|").map { it.toInt()}
                    rules.add(Pair(rule[0],rule[1]))
                }
                else{
                    val arr= line.split(",").map { it.toInt()}.toMutableList()
                    val sz=arr.size
                    var isValid=true
                    for (i in 0..<sz){
                        for (j in i+1..<sz){
                            if (rules.contains(Pair(arr[j],arr[i]))){
                                val temp=arr[i]
                                arr[i]=arr[j]
                                arr[j]=temp
                                isValid=false
                            }
                        }

                    }
                    if (!isValid){
                        ans+=arr[(sz/2)]
                        //println(arr)
                    }
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