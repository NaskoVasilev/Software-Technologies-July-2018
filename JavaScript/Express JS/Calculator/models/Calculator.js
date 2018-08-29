function Calculator(leftOperand, operator, rightOperand) {
    this.leftOperand = leftOperand;
    this.operator = operator;
    this.rightOperand = rightOperand;

    this.calculateResult = function () {
        let result = 0;
        switch (this.operator) {
            case '+':
                result = this.leftOperand + this.rightOperand;
                break;
            case '-':
                result = this.leftOperand - this.rightOperand;
                break;
            case '*':
                result = this.leftOperand * this.rightOperand;
                break;
            case '/':
                if (this.rightOperand == 0) {
                    result = 'Cannot divide by zero';
                    return result;
                }
                else {
                    result = this.leftOperand / this.rightOperand;
                }
                break;
        }
        if (Number(result) % 1 !== 0){
            result = result.toFixed(5);
        }
        return result;

    }
}

module.exports = Calculator;