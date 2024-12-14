fun main() {
    var area=0
    var diam=0
    var ans2=0
    val di=listOf(-1, 0, 1, 0)
    val dj= listOf(0, 1, 0, -1)
    val visited= mutableSetOf<Pair<Int,Int>>()
    val sides= List<HashMap<Int,MutableList<Int>>>(4, {HashMap()})
    fun isValid(i:Int, j:Int,input:List<String>,c:Char ):Boolean{
        return (i>=0 && j>=0 && i<input.size && j<input[i].length&&input[i][j]==c)
    }

    fun dfs(i:Int, j:Int, input:List<String> ){
        if(visited.contains(Pair(i,j)))return
        visited.add(Pair(i,j))
        area++
        for (id in di.indices){
            if(isValid(i+di[id],j+dj[id],input,input[i][j])){
                dfs(i+di[id],j+dj[id],input)
            }
            else {
                diam++
                if(id%2==1){
                    if (sides[id].containsKey(j)) sides[id][j]?.add(i)
                    else sides[id][j]= mutableListOf(i)
                }
                else {
                    if (sides[id].containsKey(i)) sides[id][i]?.add(j)
                    else sides[id][i]= mutableListOf(j)
                }
            }
        }
    }

    fun part1(input: List<String>): Int {
        var ans=0
        for (i in input.indices){
            for (j in input[i].indices){
                if(!visited.contains(Pair(i,j))){
                    sides.forEach { it.clear() }
                    area=0
                    diam=0
                    dfs(i,j,input)
                    ans+=area*diam
                    for (mp in sides){
                        var cnt=0
                        for (it in mp){
                            var prev=-2
                            it.value.sort()
                            for (it2 in it.value){
                                if(it2!=prev+1)cnt++
                                prev=it2
                            }
                        }
                        ans2+=area*cnt
                    }
                }
            }
        }
        return ans
    }

    fun part2(input: List<String>): Int {
        return ans2
    }

    // Read the input from the `src/input.txt` file.
    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}