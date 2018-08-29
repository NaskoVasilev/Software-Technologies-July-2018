const Sequelize = require('sequelize');

module.exports = function (sequelize) {
    const Project=sequelize.define('Project', {
        title: {type: Sequelize.TEXT},
        description: {type: Sequelize.TEXT},
        budget: {type: Sequelize.INTEGER}
    }, {
        timestamps: false
    });

    return Project;
};