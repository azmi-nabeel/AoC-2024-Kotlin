fun main() {
    var score=HashSet<Pair<Int,Int>>()
    var rating=HashSet<String>()

    fun isValid(i:Int,j:Int,input:List<String>): Boolean{
        return (i>=0 && j>=0 && i<input.size && j<input[0].length)
    }

    fun solve(i:Int,j:Int,input:List<String>,stx:String,sty:String){
        var nstx =stx+ i.toString()
        var nsty =sty+ j.toString()
        if (input[i][j]=='9'){
            score.add(Pair(i,j))
            rating.add(nstx+nsty)
            return
        }
        if (isValid(i+1,j,input)&&input[i+1][j]==input[i][j]+1){solve(i+1,j,input,nstx,nsty)}
        if (isValid(i-1,j,input)&&input[i-1][j]==input[i][j]+1){solve(i-1,j,input,nstx,nsty)}
        if (isValid(i,j+1,input)&&input[i][j+1]==input[i][j]+1){solve(i,j+1,input,nstx,nsty)}
        if (isValid(i,j-1,input)&&input[i][j-1]==input[i][j]+1){solve(i,j-1,input,nstx,nsty)}
    }
    fun part1(input:List<String>): Int {
        var ans=0
        for (i in input.indices){
            for (j in input[i].indices){
                if (input[i][j]=='0')solve(i,j,input,"","")
                ans+=score.size
                score.clear()
            }
        }
        return ans
    }

    fun part2(): Int {
        return rating.size
    }

    // Read the input from the `src/input.txt` file.
    val input = readInput("input")
    part1(input).println()
    part2().println()
}