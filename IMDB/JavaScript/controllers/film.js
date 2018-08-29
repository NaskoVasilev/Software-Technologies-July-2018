const Film = require('../models').Film;

module.exports = {
	index: (req, res) => {
        Film.findAll()
            .then((films) => {
                res.render('film/index', {"films": films});
            })
	},

	createGet: (req, res) => {
        res.render('film/create')
	},

	createPost: (req, res) => {
        let body = req.body;
        console.log(req.body)

        Film.create(body)
            .then(() => {
                    res.redirect("/")
                }
            )
	},

	editGet: (req, res) => {
        let id = req.params.id;

        Film.findById(id)
            .then(film => {
                res.render("film/edit", film.dataValues)
            })
	},

	editPost: (req, res) => {
        let id = req.params.id;
        let body = req.body;

        Film.findById(id)
            .then((film) => {
                film.updateAttributes(body)
                    .then(() => {
                        res.redirect('/')
                    })
            })
	},

	deleteGet: (req, res) => {
        let id = req.params.id;

        Film.findById(id)
            .then(film => {
                res.render("film/delete", film.dataValues)
            })
	},

	deletePost: (req, res) => {
        let id = req.params.id;

        Film.findById(id)
            .then(film => {
                film.destroy()
                    .then(() => {
                        res.redirect('/')
                    })
            })
	}
};