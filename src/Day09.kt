import java.util.Collections.swap
import kotlin.math.abs

fun main() {
    val dsk=ArrayList<Long>()
    var dsk1=ArrayList<Long>()
    fun part1(input: List<String>): Long {
        var id=0L

        for (i in input[0].indices){
            val n=input[0][i].toString().toLong()
            if (i%2==1){
                for (j in 0..<n)dsk.add(-1L)
            }
            else{
                for (j in 0..<n)dsk.add(id)
                id++
            }
        }
        dsk1=dsk.clone() as ArrayList<Long>
        var l=0;var r=dsk.size-1;var ans=0L
        while (l<r){
            if (dsk[l]==-1L){
                while (r>l&&dsk[r]==-1L)r--
                if (l>=r)break
                swap(dsk,l,r)
            }
            if (dsk[l]!=-1L){
                ans+=dsk[l]*l.toLong()
            }
            l++
        }
        return ans
    }

    fun part2(input: List<String>): Long {
        val sz=dsk1.size
        val spaces=HashSet<Pair<Int,Int>>()
        var sum=0
        for (i in 0..<sz){
            if (dsk1[i]!=-1L){
                if (sum<0)spaces.add(Pair(i+sum, abs(sum)))
                sum=0
            }
            else{
                sum+=dsk1[i].toInt()
            }
        }
        if (sum<0)spaces.add(Pair(sz+sum, abs(sum)))

        var numId=-2L;var cnt=0;var i=sz-1

        while(i>=0){
            if (dsk1[i]==numId){
                cnt++
            }
            else{
                if (numId>=0){
                    var p=Pair(-1,-1)
                    for (it in spaces) {
                        //println(it)
                        if (it.second>=cnt){
                            p=it
                            spaces.remove(it)
                            if (p.second>cnt){
                                spaces.add(Pair(p.first+cnt,p.second-cnt))
                            }
                            break
                        }
                    }
                    if (p.first!=-1 && p.first<i){
                        for (j in 0..<cnt){
                            dsk1[p.first+j]=dsk1[i+j+1]
                            dsk1[i+j+1]=(-1L)
                        }
                    }
                }
                if (dsk1[i]!=-1L){
                    numId=dsk1[i]
                    cnt=1
                }
                else{
                    numId=-2
                    cnt=0
                }
            }
            i -= 1
        }
        var ans=0L
        for (z in 0..<sz){
            //print("$dsk1[i] ")
            if (dsk1[z]!=-1L){
                ans+=dsk1[z]*z.toLong()
            }
        }
        return ans
    }

    // Read the input from the `src/input.txt` file.
    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}