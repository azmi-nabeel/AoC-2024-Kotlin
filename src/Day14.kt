//import java.util.Scanner
import kotlin.math.abs

const val height=103
const val width=101

fun main() {
    var npx:Int
    var npy:Int
    val regex = """-?\d+""".toRegex()
    fun part1(input: List<String>): Int {
        val arr = MutableList(height, { MutableList(width, {0}) })
        for(line in input){
            val (px,py,vx,vy) = regex.findAll(line).map { it.value.toInt() }.toList()
            if(vx>0){
                npx=(px+(100*vx))%width
            }
            else{
                val temp=width-px
                npx=(temp+(100*abs(vx)))%width
                npx=(width-npx)%width
            }
            if(vy>0){
                npy=(py+(100*vy))%height
            }
            else{
                val temp=height-py
                npy=(temp+(100*abs(vy)))%height
                npy=(height-npy)%height
            }
            arr[npy][npx]+=1
        }
        val ans=MutableList(4,{0})
        for (i in 0..<height){
            for (j in 0..<width){
                val h=height/2
                val w=width/2
                if(i<h && j<w){
                    ans[0]+=arr[i][j]
                }
                else if(i>h && j<w){
                    ans[1]+=arr[i][j]
                }
                else if(i<h && j>w){
                    ans[2]+=arr[i][j]
                }
                else if(i>h && j>w){
                    ans[3]+=arr[i][j]
                }
            }
            //println()
        }
        //println("$ans[0] $ans[1] $ans[2] $ans[3] ")
        return ans[0]*ans[1]*ans[2]*ans[3]
    }

    fun printGrid(grid: MutableList<MutableList<Int>>){
        for(i in 0..<height){
            for(j in 0..<width){
                if (grid[i][j]==0) print('.')
                else print(grid[i][j])
            }
            println()
        }
    }

    fun part2(input: List<String>): Int {
        val grid = MutableList(height, { MutableList(width, {0}) })
        val pos=MutableList(500, {Pair(0,0)})
        val vel=MutableList(500, {Pair(0,0)})
        var ans=0
        for(i in input.indices){
            val (px,py,vx,vy) = regex.findAll(input[i]).map { it.value.toInt() }.toList()
            pos[i]=Pair(px,py)
            vel[i]=Pair(vx,vy)
            grid[py][px]+=1
        }
        printGrid(grid)
        println(ans)
        println()
        while(ans<10000){
//            val c=Scanner(System.`in`)
//            println("Press Enter to continue")
//            var inp=c.nextLine()
//            if(!inp.isEmpty())break;
            ans++
            for(i in pos.indices){
                val (px,py)=pos[i]
                val (vx,vy)=vel[i]
                grid[py][px]-=1

                if(vx>0){
                    npx=(px+(vx))%width
                }
                else{
                    val temp=width-px
                    npx=(temp+(abs(vx)))%width
                    npx=(width-npx)%width
                }
                if(vy>0){
                    npy=(py+(vy))%height
                }
                else{
                    val temp=height-py
                    npy=(temp+(abs(vy)))%height
                    npy=(height-npy)%height
                }
                pos[i]=Pair(npx,npy)
                grid[npy][npx]+=1
            }
            var flag=true
            for (i in 0..<height){
                for (j in 0..<width){
                    if (grid[i][j]>1) {
                        flag = false
                        break
                    }
                }
                if(!flag)break
            }
            if(flag){
                printGrid(grid)
                println(ans)
                println()
            }
        }
        return ans
    }

    // Read the input from the `src/input.txt` file.
    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}