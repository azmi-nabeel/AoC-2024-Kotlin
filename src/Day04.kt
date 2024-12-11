fun main() {
    val word="XMAS"
    val sz=4
    var m=0
    var n=0
    var ans=0

    val grad= listOf(
        -1,
        0,
        1
    )

    fun isValid(i:Int,j:Int):Boolean{
        return (i >= 0 && j >= 0 && i < m && j < n)
    }

    fun dfs(input: List<String>,i:Int,j:Int,a:Int,b:Int,id:Int){
        if (id >= sz)
            return
        if (id == sz - 1)
        {
            if (input[i][j] == word[id])
            {
                ans++
                return
            }
        }
        if (input[i][j] == word[id])
        {
            if (isValid(i + a, j + b))
                dfs(input,i + a, j + b, a, b, id + 1)
        }
    }

    fun part1(input: List<String>): Int {
        ans=0
        for (i in 0..<m){
            for(j in 0..<n){
                if (input[i][j]=='X'){
                    for (a in grad){
                        for(b in grad){
                            if (a==0&&b==0)continue
                            dfs(input,i,j,a,b,0)
                        }
                    }
                }
            }
        }
        return ans
    }

    fun part2(input: List<String>): Int {
        ans=0
        for (i in 1..<m-1){
            for (j in 1..<n-1){
                var flag1=false
                var flag2=false
                if (input[i][j]=='A') {
                    flag1=(input[i - 1][j - 1] == 'M' && input[i + 1][j + 1] == 'S') || ((input[i - 1][j - 1] == 'S' && input[i + 1][j + 1] == 'M'));
                    flag2=(input[i - 1][j + 1] == 'M' && input[i + 1][j - 1] == 'S') || ((input[i - 1][j + 1] == 'S' && input[i + 1][j - 1] == 'M'));
                }
                if (flag1 and flag2)ans++
            }
        }
        return ans
    }

    // Read the input from the `src/input.txt` file.
    val input = readInput("input")
    m=input.size
    n=input[0].length
    part1(input).println()
    part2(input).println()
}