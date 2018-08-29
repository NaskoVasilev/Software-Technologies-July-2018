function findSign(input) {
    let nums = input.map(Number);

    if (nums.includes(0)) {
        console.log("Positive");
    }
    let countOfNegativeNums = nums.filter(x=>x<0).length;
    if (countOfNegativeNums % 2 == 0) {
        console.log("Positive");
    }
    else {
        console.log("Negative");
    }
}

findSign(["3","5","-5"]);