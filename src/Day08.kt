import java.awt.geom.Point2D

fun main() {
    var ump=HashMap<Char,ArrayList<Pair<Int,Int>>>()

    fun isValid(p:Pair<Int,Int>,h:Int,w:Int)=
        !(p.first<0||p.second<0||p.first>=h||p.second>=w)

    fun part1(input: List<String>): Int {
        for (i in input.indices){
            for (j in input[i].indices){
                if (input[i][j].isLetterOrDigit()){
                    ump.getOrPut(input[i][j]){ArrayList()}.add(Pair(i,j))}
            }
        }
        var st=HashSet<Pair<Int,Int>>()

        for ((freq,occurence) in ump){
            if (occurence.size<2)continue
            for (i in 0..<occurence.size){
                for (j in i+1..<occurence.size){
                    var diff=occurence[i].first-occurence[j].first to occurence[i].second-occurence[j].second
                    var ni=occurence[i].first+diff.first to occurence[i].second+diff.second
                    if(isValid(ni,input.size,input[0].length)){
                        st.add(ni)
                    }
                    diff=occurence[j].first-occurence[i].first to occurence[j].second-occurence[i].second
                    ni=occurence[j].first+diff.first to occurence[j].second+diff.second
                    if(isValid(ni,input.size,input[0].length)){
                        st.add(ni)
                    }
                }
            }
        }
        return st.size
    }

    fun part2(input: List<String>): Int {
        var st=HashSet<Pair<Int,Int>>()

        for ((freq,occurence) in ump){
            if (occurence.size<2)continue
            for (i in 0..<occurence.size){
                st.add(occurence[i])
                for (j in i+1..<occurence.size){
                    var diff=occurence[i].first-occurence[j].first to occurence[i].second-occurence[j].second
                    var ni=occurence[i].first+diff.first to occurence[i].second+diff.second
                    while (true){
                        if(isValid(ni,input.size,input[0].length)){
                            st.add(ni)
                        }
                        else break
                        ni=ni.first+diff.first to ni.second+diff.second
                    }
                    diff=occurence[j].first-occurence[i].first to occurence[j].second-occurence[i].second
                    ni=occurence[j].first+diff.first to occurence[j].second+diff.second
                    while (true){
                        if(isValid(ni,input.size,input[0].length)){
                            st.add(ni)
                        }
                        else break
                        ni=ni.first+diff.first to ni.second+diff.second
                    }
                }
            }
        }
        return st.size
    }

    // Read the input from the `src/input.txt` file.
    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}