const Project = require('../models').Project;

module.exports = {
    index: (req, res) => {
        Project.findAll()
            .then(projects=>{
                res.render('project/index.hbs',{"projects":projects})
            })
    },
    createGet: (req, res) => {
        res.render('project/create.hbs')
    },
    createPost: (req, res) => {
        let args=req.body.project;

        Project.create(args)
            .then(()=>{
                res.redirect('/')
            })
    },
    editGet: (req, res) => {
        let id=req.params.id;

        Project.findById(id)
            .then(project=>{
                res.render('project/edit.hbs',{"project":project})
            })

    },

    editPost: (req, res) => {
        let args=req.body.project;
        let id =req.params.id;

        Project.findById(id)
            .then(project=>{
                project.updateAttributes(args)
                    .then(()=>{
                        res.redirect('/');
                    })
            })
    },

    deleteGet: (req, res) => {
        let id=req.params.id;

        Project.findById(id)
            .then(project=>{
                res.render('project/delete.hbs',{"project":project})
            })


    },
    deletePost: (req, res) => {
        let id=req.params.id;

        Project.findById(id)
            .then(project=>{
                project.destroy()
                    .then(()=>{
                        res.redirect('/')
                    })
            })

    }

};