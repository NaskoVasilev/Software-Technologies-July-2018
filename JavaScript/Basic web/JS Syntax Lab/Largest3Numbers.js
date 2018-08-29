function largestThreeNumbers(arr) {
    let nums=arr.map(Number).sort((a,b)=>b-a);

    for (let i=0;i<Math.min(3,nums.length);i++){
        console.log(nums[i]);
    }
}

largestThreeNumbers(['5','15','25','10','200']);