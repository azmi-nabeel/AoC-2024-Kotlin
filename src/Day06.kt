fun main() {
    var m:Int
    var n:Int
    var ans=0

    var dir= mutableListOf<MutableList<Int>>()

    val di= listOf(-1,0,1,0)
    val dj= listOf(0,1,0,-1)

    var startI=-1
    var startJ=-1

    fun part1(input: List<String>): Int {
        m=input.size
        n=input[0].length
        for (i in input.indices){
            if (input[i].contains('^')){
                startI=i
                startJ=input[i].indexOf('^')
                break
            }
        }
        var posX=startI
        var posY=startJ
        var id=0

        val vis= mutableSetOf(Pair(posX,posY))
        while(posX in 0..<m && posY in 0..<n){
            vis.add(Pair(posX,posY))
            val nX=posX+di[id]
            val nY=posY+dj[id]
            if(nX in 0..<m && nY in 0..<n){
                if(input[nX][nY]=='#'){
                    id=(id+1)%4
                }
                else {
                    posX=nX
                    posY=nY
                }
            }
            else break
        }
        return vis.size
    }

    fun isValid(input:List<String>,i:Int,j:Int,id:Int): Boolean{
        val ni=i+di[id]
        val nj=j+di[id]
        if(ni<0||ni>=input.size||nj<0||nj>=input[0].length)
            return true
        if (input[ni][nj]=='#')
            return false
        return true
    }
    fun simulate(input: MutableList<String>,i:Int,j:Int,id:Int): Boolean{
        var i=i
        var j=j
        var id=id
        while(i>=0&&i<input.size&&j>=0&&j<input[0].length){
            if(isValid(input,i,j,id)){
                if (input[i][j]=='X' && dir[i][j]==id)
                    return true
                input[i] = input[i].toCharArray().apply { this[j] = 'X' }.joinToString("")
                dir[i][j]=id
                i+=di[id]
                j+=dj[id]
            }
            else id=(id+1)%4
        }
        return false
    }
    fun part2(input: List<String>): Int {
        val copy=input
        for (i in 0..<input.size){
            for (j in 0..<input[0].length){
                if (input[i][j]=='X'){
                    val temp=copy.toMutableList()
                    temp[i] = temp[i].toCharArray().apply { this[j] = '#' }.joinToString("")
                    if (simulate(temp,startI,startJ,0))ans++
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