function numberInReversedOrder(arr) {
    let nums=arr.map(Number);

    for (let i = nums.length-1; i>=0; i--) {
        console.log(nums[i]);
    }
}
numberInReversedOrder(['10','20','30'])