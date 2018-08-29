function operation(nums) {
    let firstNum = Number(nums[0]);
    let secondNum = Number(nums[1]);

    if (secondNum >= firstNum) {
        return firstNum * secondNum;
    }
    else {
        return firstNum / secondNum;
    }
}

console.log(operation(["2", "3"]));