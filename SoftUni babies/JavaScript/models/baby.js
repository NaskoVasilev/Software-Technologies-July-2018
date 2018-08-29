const Sequelize = require('sequelize');

module.exports = function (sequelize) {
    let Baby = sequelize.define('Baby', {
        name: Sequelize.STRING,
        gender:Sequelize.STRING,
        birthday:Sequelize.STRING
    },{
        timestamps:false
    });

    return Baby;
};