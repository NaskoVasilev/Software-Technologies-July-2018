const Sequelize = require('sequelize');

module.exports = function(sequelize){
    const Film=sequelize.define('Film',{
        name:{
            type:Sequelize.TEXT,
            allowNull:false
        },
        genre:{
            type:Sequelize.TEXT,
            allowNull:false
        },
        director:{
            type:Sequelize.TEXT,
            allowNull:false
        },
        year:{
            type:Sequelize.INTEGER,
            allowNull:false
        },
    },{
        timestamps:false
    })
    return Film;
};