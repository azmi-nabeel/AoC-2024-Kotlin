fun main() {
    var flag=false
    fun check1(tst:Long,nums:List<Long>,id:Int,sum:Long,sz:Int){
        if (flag)return
        if (id==sz-1){
            flag=((sum*nums[id]==tst)||(sum+nums[id]==tst))
            return
        }
        if (sum>tst)return
        check1(tst,nums,id+1,sum*nums[id],sz)
        check1(tst,nums,id+1,sum+nums[id],sz)
    }
    fun check2(tst:Long,nums:List<Long>,id:Int,sum:Long,sz:Int){
        if (flag)return
        if (sum>tst)return
        val curr = (sum.toString()+nums[id].toString()).toLong()
        if (id==sz-1){
            flag=((sum*nums[id]==tst)||(sum+nums[id]==tst)||curr==tst)
            return
        }
        check2(tst,nums,id+1,sum*nums[id],sz)
        check2(tst,nums,id+1,sum+nums[id],sz)
        check2(tst,nums,id+1,curr,sz)
    }
    fun part1(input: List<String>): Long {
        var ans=0L
        for (line in input){
            val tst=line.split(": ").first().toLong()
            val nums=line.split(": ").last().split(" ").map { it.toLong() }
            flag=false
            check1(tst,nums,1,nums[0],sz=nums.size)
            if(flag)ans+=tst
        }
        return ans
    }

    fun part2(input: List<String>): Long {
        var ans=0L
        for (line in input){
            val tst=line.split(": ").first().toLong()
            val nums=line.split(": ").last().split(" ").map { it.toLong() }
            flag=false
            check2(tst,nums,1,nums[0],sz=nums.size)
            if(flag)ans+=tst
        }
        return ans
    }

    // Read the input from the `src/input.txt` file.
    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}